package com.ipl.auction.service.interfaces;

public interface UserServiceInterface {

    void registerUser(String username, String rawPassword);
    boolean verifyPassword(String username, String rawPassword);
}
