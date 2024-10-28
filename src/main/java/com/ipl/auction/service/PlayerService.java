package com.ipl.auction.service;

import com.ipl.auction.repository.PlayersDaoInterface;
import com.ipl.auction.entity.Player;
import com.ipl.auction.enums.PlayerCategory;
import com.ipl.auction.exceptions.PlayerException;
import com.ipl.auction.utils.PlayerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ipl.auction.exceptions.PlayerException.PLAYER_NOT_FOUND;

@Service
public class PlayerService implements PlayersServiceInterface{

    @Autowired
    PlayersDaoInterface playersDao;

    public List<Player> getAllPlayers(){
        return playersDao.findAll();
    }

    public List<Player> getPlayersByCategory(PlayerCategory category){
        return playersDao.findByCategory(category);
    }

    public Player addPlayer(Player player){
        boolean isOverseasPlayer = PlayerUtils.isOverseasPlayer(player);
        player.setOverseasPlayer(isOverseasPlayer);

        return playersDao.save(player);
    }

    public void deletePlayer(long id) throws PlayerException {
        Player player = playersDao.findById(id).orElseThrow( () -> PlayerException.errorMessages.get(PLAYER_NOT_FOUND));
        playersDao.delete(player);
    }

}
