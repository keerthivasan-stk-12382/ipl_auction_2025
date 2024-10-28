package com.ipl.auction.entity;

import com.ipl.auction.enums.Country;
import com.ipl.auction.enums.PlayerCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Player {

    @Id
    long id;

    @Column(nullable = false)
    String name;

    @Column(name = "is_capped", nullable = false)
    boolean isCappedPlayer;

    @Column(name = "is_overseas", nullable = false)
    boolean isOverseasPlayer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Country country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PlayerCategory category;

}
