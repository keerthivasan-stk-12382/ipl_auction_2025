package com.ipl.auction.controller;

import com.ipl.auction.dto.ESPNPlayer;
import com.ipl.auction.entity.player_entity.Player;
import com.ipl.auction.integration.EspnCricInfoClient;
import com.ipl.auction.integration.EspnCricInfoUtil;
import com.ipl.auction.service.interfaces.PlayersExtnServiceInterface;
import com.ipl.auction.service.interfaces.PlayersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal")
public class IntegrationsController {

    @Autowired
    private EspnCricInfoClient espnCricInfoClient;

    @Autowired
    private PlayersServiceInterface playersService;

    @RequestMapping("/{year}")
    public String getPlayersJson(@PathVariable int year) {
        List<ESPNPlayer> playerList = espnCricInfoClient.getIplPlayersList(year);
        EspnCricInfoUtil.processPlayerList(year,playerList);
        return "Players list fetched successfully";
    }

    @RequestMapping("/images")
    public String downloadPlayerImages() {
        List<Player> playerList = playersService.getAllPlayers();
        playerList.forEach(player -> {
            espnCricInfoClient.downloadPlayerImages(player);
        });
        return "Players list updated successfully";
    }


}
