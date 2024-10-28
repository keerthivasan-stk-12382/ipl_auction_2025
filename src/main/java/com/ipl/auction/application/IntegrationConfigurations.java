package com.ipl.auction.application;

import com.ipl.auction.integration.EspnCricInfoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IntegrationConfigurations {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public EspnCricInfoClient espnCricInfoClient() {
        return new EspnCricInfoClient();
    }
}
