package com.ipl.auction.integration;


import com.ipl.auction.dto.ESPNPlayer;
import com.ipl.auction.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class EspnCricInfoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EspnCricInfoClient.class);


    @Autowired
    private RestTemplate restTemplate;

    public List<ESPNPlayer> getIplPlayersList(int year) {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(ESPNPaths.getIplPlayersListUrl(year), String.class);
            JSONArray playerList = new JSONObject(responseEntity.getBody()).getJSONArray("results");

            List<ESPNPlayer> players = new ArrayList<>();
            for (int i = 0; i < playerList.length(); i++) {

                JSONObject playerObj = playerList.getJSONObject(i);

                int playerId = playerObj.getInt("id");
                String role = playerObj.getString("role");
                String team = playerObj.getString("abbreviation");
                String name = playerObj.getString("known_as");

                ESPNPlayer player = new ESPNPlayer();

                player.setId(playerId);
                player.setName(name);
                player.setIplTeam(team);
                player.setRole(role);
                getIplPlayerDetails(playerId, player);
                LOGGER.info("Player fetched: {}", name);
                players.add(player);
            }

            return players;

        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching player list from ESPN CricInfo: {1}", e);
            throw new RuntimeException("Error occurred while fetching player list from ESPN CricInfo");
        }
    }

    public ESPNPlayer getIplPlayerDetails(int playerId, ESPNPlayer player) {

        try {

            String path = ESPNPaths.getIplPlayerDetailsHtml(playerId);
            Document document = Jsoup.connect(path).get();

            Elements allElements = document.select("script");

            for (Element element : allElements) {
                if(element.attribute("id") != null) {
                    JSONObject playerJson = new JSONObject(element.data())
                            .getJSONObject("props")
                            .getJSONObject("appPageProps")
                            .getJSONObject("data")
                            .getJSONObject("player");

                    player.setFullName(playerJson.getString("name"));
                    JSONObject dob = playerJson.getJSONObject("dateOfBirth");
                    player.setDob(dob.get("year") + "-" + dob.get("month") + "-" + dob.get("date"));
                    player.setCountry(playerJson.getJSONObject("country").getString("name"));
                    player.setCapped(playerJson.get("intlCareerSpan") instanceof String);
                    player.setBattingStyle(playerJson.optJSONArray("longBattingStyles").optString(0));
                    player.setBowlingStyle(playerJson.getJSONArray("longBowlingStyles").optString(0));
                    player.setImageUrl(playerJson.optString("imageUrl"));
                }
            }

        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching player details from ESPN CricInfo: {1}", e);
            throw new RuntimeException("Error occurred while fetching player details from ESPN CricInfo");
        }

        return player;
    }

}
