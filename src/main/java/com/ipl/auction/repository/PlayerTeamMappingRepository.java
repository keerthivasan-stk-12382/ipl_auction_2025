package com.ipl.auction.repository;

import com.ipl.auction.entity.player_entity.PlayerTeamMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTeamMappingRepository extends JpaRepository<PlayerTeamMapping, Integer> {

    public PlayerTeamMapping findByPlayerPlayerId(@Param("player_id") int playerId);
}
