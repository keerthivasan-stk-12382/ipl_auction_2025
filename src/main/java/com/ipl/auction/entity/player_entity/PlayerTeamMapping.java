package com.ipl.auction.entity.player_entity;

import com.ipl.auction.entity.Team;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlayerTeamMapping {

    @Id
    @Column(name = "player_id")
    int playerId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // Getters and setters
}
