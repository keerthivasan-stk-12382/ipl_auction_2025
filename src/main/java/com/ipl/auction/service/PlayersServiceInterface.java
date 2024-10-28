package com.ipl.auction.service;

import com.ipl.auction.entity.Player;
import com.ipl.auction.enums.PlayerCategory;
import com.ipl.auction.exceptions.PlayerException;

import java.util.List;

public interface PlayersServiceInterface {

    List<Player> getAllPlayers();
    List<Player> getPlayersByCategory(PlayerCategory category);

    Player addPlayer(Player player);
    void deletePlayer(long id) throws PlayerException;


}
