package com.ipl.auction.entity.player_entity;

import com.ipl.auction.enums.Country;
import com.ipl.auction.enums.PlayerCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int playerId;

    @Column(nullable = false)
    String name;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "is_capped", nullable = false)
    boolean isCappedPlayer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Country country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PlayerCategory category;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private PlayerExtn playerExtn;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private PlayerTeamMapping playerTeamMapping;
}
