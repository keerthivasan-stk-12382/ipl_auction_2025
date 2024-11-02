package com.ipl.auction.repository;

import com.ipl.auction.entity.player_entity.Player;
import com.ipl.auction.enums.PlayerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    boolean existsByName(String name);

    Player findByName(String name);

    List<Player> findByCategory(PlayerCategory category);

    List<Player> findByNameContaining(String search);
}
