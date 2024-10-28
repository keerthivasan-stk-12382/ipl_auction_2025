package com.ipl.auction.integration;

public class ESPNPaths {

    private ESPNPaths() {
    }

    private static final String CRIC_INFO_URL = "https://www.espncricinfo.com/";
    private static final String IPL_PLAYERS_URL = "ci/engine/match/players.html";
    private static final String PLAYER_INFO_URL = "http://core.espnuk.org/v2/sports/cricket/athletes/";



    public static String getIplPlayersListUrl(int year) {
        return CRIC_INFO_URL + IPL_PLAYERS_URL + "?year=" + year;
    }

    public static String getIplPlayerDetailsUrl(int playerId) {
        return PLAYER_INFO_URL + playerId;
    }

    public static String getIplPlayerDetailsHtml(int playerId) {
        return String.format("https://www.espncricinfo.com/ci/content/player/%s.html", playerId);
    }

}
