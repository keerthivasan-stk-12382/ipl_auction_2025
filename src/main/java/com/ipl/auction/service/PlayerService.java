package com.ipl.auction.service;

import com.ipl.auction.entity.player_entity.PlayerExtn;
import com.ipl.auction.entity.player_entity.PlayerTeamMapping;
import com.ipl.auction.enums.PlayerStatus;
import com.ipl.auction.repository.PlayerRepository;
import com.ipl.auction.entity.player_entity.Player;
import com.ipl.auction.enums.PlayerCategory;
import com.ipl.auction.exceptions.PlayerException;
import com.ipl.auction.service.interfaces.PlayersServiceInterface;
import com.ipl.auction.utils.PlayerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements PlayersServiceInterface {

    @Autowired
    PlayerRepository playerRepository;

    public long getPlayerCount(){
        return  playerRepository.count();
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public List<Player> getAllPlayers(String sortField, boolean isAscending){
      return  playerRepository.findAll(isAscending ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
    }

    public List<Player> searchPlayer(String search){
        Player player = new Player();
        player.setName(search);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("playerId", "fullName", "isCappedPlayer", "country", "category", "playerExtn", "playerTeamMapping")
                .withMatcher("name", match -> match.contains().ignoreCase());
        return playerRepository.findAll(Example.of(player, matcher));
    }

    public List<Player> getPlayersByCategory(PlayerCategory category){
        return playerRepository.findByCategory(category);
    }

    @Override
    public List<Player> getPlayerByStatus(List<Player> players, PlayerStatus playerStatus) {

        return players.stream()
                .filter(player -> PlayerUtils.getStatusFiler().test(player, playerStatus))
                .toList();
    }

    public Player addOrUpdatePlayer(Player player){

        if(playerRepository.existsByName(player.getName())){
            Player existingPlayer  = playerRepository.findByName(player.getName());
            player.setPlayerId(existingPlayer.getPlayerId());

            PlayerExtn existingPlayerExtn = existingPlayer.getPlayerExtn();
            if (existingPlayerExtn != null) {
                player.getPlayerExtn().setPlayerId(existingPlayerExtn.getPlayerId());
            }

            PlayerTeamMapping existingPlayerTeamMapping = existingPlayer.getPlayerTeamMapping();
            if (existingPlayerTeamMapping != null) {
                player.getPlayerTeamMapping().setPlayerId(existingPlayerTeamMapping.getPlayerId());
            }
        }

        return playerRepository.save(player);
    }


    public void deletePlayer(int id) throws PlayerException {
        Player player = playerRepository.findById(id).orElseThrow( () -> PlayerException.PLAYER_NOT_FOUND);
        playerRepository.delete(player);
    }

}
