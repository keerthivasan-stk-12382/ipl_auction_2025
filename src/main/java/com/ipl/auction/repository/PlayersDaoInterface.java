package com.ipl.auction.repository;

import com.ipl.auction.entity.Player;
import com.ipl.auction.enums.PlayerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayersDaoInterface extends JpaRepository<Player, Long> {

    Player findByName(String name);

    List<Player> findByCategory(PlayerCategory category);
}
