package com.ipl.auction.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRoles {

    SUPER_ADMIN(1, "Super Admin"),
    ADMIN(2, "Admin"),
    USER(3, "User"),
    TEAM_OWNER(4, "Team Owner"),
    GUEST(5, "Guest");

    private final int id;
    private final String role;
}
