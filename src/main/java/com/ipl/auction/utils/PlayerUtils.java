package com.ipl.auction.utils;

import com.ipl.auction.entity.Player;

import static com.ipl.auction.enums.Country.India;

public class PlayerUtils {

    private PlayerUtils() {
    }

    public static boolean isPlayerNameValid(String playerName) {
        return playerName != null && !playerName.isEmpty();
    }

    public static boolean isOverseasPlayer(Player player) {
        return !player.getCountry().equals(India);
    }
}
