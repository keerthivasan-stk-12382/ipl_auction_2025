package com.ipl.auction.repository;

import com.ipl.auction.entity.User;
import com.ipl.auction.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByUsernameAndRole(String username, UserRoles role);

    int countByRole(UserRoles role);
}
