package com.ipl.auction.repository;

import com.ipl.auction.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findByTeamName(String teamName);
    Team findByTeamCode(String teamCode);

}
