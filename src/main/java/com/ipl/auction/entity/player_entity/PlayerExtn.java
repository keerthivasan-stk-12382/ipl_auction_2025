package com.ipl.auction.entity.player_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
@lombok.Getter
@lombok.Setter
public class PlayerExtn {


    @Id
    @Column(name = "player_id")
    int playerId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @Column(name = "espn_id")
    int espnId;

    @Column(name = "date_of_birth")
    String dateOFBirth;

    @Column(name = "batting_style")
    String battingStyle;

    @Column(name = "bowling_style")
    String bowlingStyle;

    @Column(name = "image_path")
    String imagePath;

}
