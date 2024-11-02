package com.ipl.auction.repository;

import com.ipl.auction.entity.player_entity.PlayerExtn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerExtnRepository extends JpaRepository<PlayerExtn, Integer> {

}
