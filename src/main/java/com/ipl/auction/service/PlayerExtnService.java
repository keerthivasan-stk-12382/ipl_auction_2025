package com.ipl.auction.service;

import com.ipl.auction.repository.PlayerExtnRepository;
import com.ipl.auction.service.interfaces.PlayersExtnServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerExtnService implements PlayersExtnServiceInterface {

    @Autowired
    PlayerExtnRepository playerExtnRepository;


}
