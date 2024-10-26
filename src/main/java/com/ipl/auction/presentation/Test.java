package com.ipl.auction.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @RequestMapping("/")
    public String test() {
        return "IPL Auction 2025";
    }
}
