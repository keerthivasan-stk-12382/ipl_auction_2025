package com.ipl.auction.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    @Bean
    public Logger auctionServiceLogger() {
        return LoggerFactory.getLogger("AuctionServiceLogger");
    }

    @Bean
    public Logger auctionControllerLogger() {
        return LoggerFactory.getLogger("AuctionControllerLogger");
    }

    @Bean
    public Logger auctionStartUpLogger() {
        return LoggerFactory.getLogger("AuctionStartUpLogger");
    }

    @Bean
    public Logger webAccessLogger() {
        return LoggerFactory.getLogger("WebAccessLogger");
    }

}