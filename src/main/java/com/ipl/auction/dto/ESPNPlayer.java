package com.ipl.auction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ESPNPlayer {

    int id;
    String fullName;
    String name;
    String dob;

    String iplTeam;
    String basePrice;
    String country;
    boolean isCapped;

    String role;
    String battingStyle;    // longBattingStyles
    String bowlingStyle;    // longBowlingStyles

    String imageUrl;

    public String[] toCsv() {
        return new String[]{String.valueOf(id), name, fullName, dob, iplTeam, country, String.valueOf(isCapped), role, basePrice, battingStyle, bowlingStyle, imageUrl};
    }

    public static String[] getCsvHeaders() {
        return new String[]{"id", "Name", "Full Name", "DOB", "IPL Team", "Country", "isCapped", "Role", "basePrice", "Batting Style", "Bowling Style", "ImageUrl"};
    }
}
