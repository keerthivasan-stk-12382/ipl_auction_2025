package com.ipl.auction.enums;

import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public enum Country {
    
    INDIA("India"),
    AUSTRALIA("Australia"),
    SOUTH_AFRICA("South Africa"),
    WEST_INDIES("West Indies"),
    NEW_ZEALAND("New Zealand"),
    ENGLAND("England"),
    SRI_LANKA("Sri Lanka"),
    BANGLADESH("Bangladesh"),
    AFGHANISTAN("Afghanistan"),
    IRELAND("Ireland"),
    ZIMBABWE("Zimbabwe"),
    CANADA("Canada"),
    NAMIBIA("Namibia"),
    NETHERLANDS("Netherlands"),
    WILL_BE_REPLACED_BY_COUNTRY_ENUM("Unknown");

   final String country;

    public static Country getCountry(String country) {
        return switch (country) {
            case "India" -> INDIA;
            case "Australia" -> AUSTRALIA;
            case "South Africa" -> SOUTH_AFRICA;
            case "West Indies" -> WEST_INDIES;
            case "New Zealand" -> NEW_ZEALAND;
            case "England" -> ENGLAND;
            case "Sri Lanka" -> SRI_LANKA;
            case "Bangladesh" -> BANGLADESH;
            case "Afghanistan" -> AFGHANISTAN;
            case "Ireland" -> IRELAND;
            case "Zimbabwe" -> ZIMBABWE;
            case "Canada" -> CANADA;
            case "Namibia" -> NAMIBIA;
            case "Netherlands" -> NETHERLANDS;
            default -> {
                LoggerFactory.getLogger(Country.class).warn("!!!!Country not found: {}", country);
                yield WILL_BE_REPLACED_BY_COUNTRY_ENUM;
            }
        };
    }

}
