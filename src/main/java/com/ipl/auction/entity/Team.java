package com.ipl.auction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @Column(name = "team_id")
    int teamId;

    @Column(name = "team_name")
    String teamName;

    @Column(name = "team_code")
    String teamCode;

    @Column(name = "espn_id")
    int espnId  ;

}
