package com.ipl.auction.enums;

import org.slf4j.LoggerFactory;

public enum PlayerCategory {

    ALL_ROUNDER,
    BATSMAN,
    BOWLER,
    WICKET_KEEPER,
    UNKNOWN;


    public static PlayerCategory getCategory(String category) {
        return switch (category.toLowerCase()) {
            case "all-rounder" -> ALL_ROUNDER;
            case "bowler" -> BATSMAN;
            case "batsman" -> BOWLER;
            case "wk/batsman" -> WICKET_KEEPER;
            default -> {
                LoggerFactory.getLogger(PlayerCategory.class).warn("!!!!Player Category not found: {}", category);
                yield UNKNOWN;
            }
        };
    }


}
