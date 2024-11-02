package com.ipl.auction.utils;

import com.ipl.auction.entity.player_entity.Player;
import com.ipl.auction.enums.PlayerStatus;

import java.util.function.BiPredicate;

import static com.ipl.auction.enums.Country.INDIA;

public class PlayerUtils {

    private PlayerUtils() {
    }

    public static boolean isPlayerNameValid(String playerName) {
        return playerName != null && !playerName.isEmpty();
    }

    public static boolean isOverseasPlayer(Player player) {
        return !player.getCountry().equals(INDIA);
    }

    public static BiPredicate<Player, PlayerStatus> getStatusFiler() {
        return (player,playerStatus) -> switch (playerStatus) {
            case CAPPED -> player.isCappedPlayer();
            case UNCAPPED -> !player.isCappedPlayer();
            case INDIAN -> player.getCountry() == INDIA;
            case OVERSEAS -> player.getCountry() != INDIA;
            case RELEASED -> true;
            case RETAINED -> false;
            case SOLD -> false;
            case UNSOLD -> true;
        };
    }
}
