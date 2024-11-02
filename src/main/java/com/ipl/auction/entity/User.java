package com.ipl.auction.entity;

import com.ipl.auction.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "`user`")
@NoArgsConstructor
public class User {

    public User(String username, String password, UserRoles role) {
        this.role = role;
        this.password = password;
        this.username = username;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;

    @Column(name = "user_name", unique = true)
    String username;

    @Column
    String password;

    @Enumerated(EnumType.ORDINAL)
    UserRoles role;
}
