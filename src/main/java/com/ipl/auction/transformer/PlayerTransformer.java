package com.ipl.auction.transformer;

import com.ipl.auction.dto.ESPNPlayer;
import com.ipl.auction.entity.player_entity.Player;
import com.ipl.auction.enums.Country;
import com.ipl.auction.enums.PlayerCategory;

public class PlayerTransformer {

    private PlayerTransformer(){

    }

    public static Player getPlayerFromESPNPlayer(ESPNPlayer espnPlayer) {
        Player player = new Player();
        player.setName(espnPlayer.getName());
        player.setFullName(espnPlayer.getFullName());
        player.setCappedPlayer(espnPlayer.isCapped());
        player.setCountry(Country.getCountry(espnPlayer.getCountry()));
        player.setCategory(PlayerCategory.getCategory(espnPlayer.getRole()));

        return player;
    }
}
