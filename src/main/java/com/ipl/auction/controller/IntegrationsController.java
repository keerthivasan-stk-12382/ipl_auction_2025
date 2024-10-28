package com.ipl.auction.controller;

import com.ipl.auction.dto.ESPNPlayer;
import com.ipl.auction.integration.EspnCricInfoClient;
import com.ipl.auction.integration.EspnCricInfoUtil;
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

    @RequestMapping("/{year}")
    public String getPlayersJson(@PathVariable int year) {
        List<ESPNPlayer> playerList = espnCricInfoClient.getIplPlayersList(year);
        EspnCricInfoUtil.processPlayerList(year,playerList);
        return "Players list fetched successfully";
    }

}
