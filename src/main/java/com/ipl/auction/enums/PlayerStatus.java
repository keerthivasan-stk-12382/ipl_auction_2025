package com.ipl.auction.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerStatus {

    INDIAN(1,"INDIAN",StatusType.ORIGIN),
    OVERSEAS(2,"OVERSEAS",StatusType.ORIGIN),

    UNCAPPED(11,"UNCAPPED",StatusType.INTERNATION_EXP),
    CAPPED(12,"CAPPED",StatusType.INTERNATION_EXP),

    RELEASED(21,"RELEASED",StatusType.RETENTION_STATUS),
    RETAINED(22,"RETAINED",StatusType.RETENTION_STATUS),

    SOLD(31,"SOLD",StatusType.AUCTION_STATUS),
    UNSOLD(32,"UNSOLD",StatusType.AUCTION_STATUS);

    final int statusCode;
    final String status;
    final StatusType type;

    @Override
    public String toString(){
        return status + "_PLAYER";
    }

    public enum StatusType {
        ORIGIN,
        INTERNATION_EXP,
        RETENTION_STATUS,
        AUCTION_STATUS;
    }
}
