package com.ipl.auction.service.interfaces;

import com.ipl.auction.entity.player_entity.Player;
import com.ipl.auction.enums.PlayerCategory;
import com.ipl.auction.enums.PlayerStatus;
import com.ipl.auction.exceptions.PlayerException;

import java.util.List;

public interface PlayersServiceInterface {

    long getPlayerCount();
    List<Player> getAllPlayers();
    List<Player> getAllPlayers(String sortField, boolean isAscending);
    List<Player> searchPlayer(String search);
    List<Player> getPlayersByCategory(PlayerCategory category);
    List<Player> getPlayerByStatus(List<Player> players, PlayerStatus playerStatus);

    Player addOrUpdatePlayer(Player player);
    void deletePlayer(int id) throws PlayerException;


}
