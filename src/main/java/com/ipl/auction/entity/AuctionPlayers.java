package com.ipl.auction.entity;


import com.ipl.auction.entity.player_entity.Player;
import com.ipl.auction.enums.AuctionSet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class AuctionPlayers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "base_price")
    private int basePrice;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "auction_set")
    private AuctionSet auctionSet;

    @Column(name = "sold_at")
    private int soldAt;

    @OneToOne
    @JoinColumn(name = "sold_to")
    private Team team;

}
