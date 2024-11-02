package com.ipl.auction.service;

import com.ipl.auction.entity.User;
import com.ipl.auction.enums.UserRoles;
import com.ipl.auction.exceptions.UserException;
import com.ipl.auction.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuctionUserService implements UserDetailsService {

    @Autowired
    private Logger auctionServiceLogger;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuctionUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getRole())
                .build();
    }

    public long getUserCount() {
        return userRepository.count();
    }

    public User addUser(User user)  throws UserException {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw UserException.USER_ALREADY_EXISTS;
        }

        if(user.getRole() == UserRoles.SUPER_ADMIN && isSuperAdminExists()) {
            throw UserException.SUPER_ADMIN_ALREADY_EXISTS;
        }

        if(user.getRole() == null) {
            user.setRole(UserRoles.GUEST);
        }

        auctionServiceLogger.info("User '{}' created successfully with role '{}''", user.getUsername(), user.getRole());
        user.setPassword(encryptPassword(user.getPassword()));

        return userRepository.save(user);
    }

    public boolean verifyPassword(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public void changePassword(User user, String newPassword) throws UserException {
        if(!userRepository.existsByUsername(user.getUsername())) {
            throw UserException.USER_NOT_FOUND.setUser(user);
        }

        User updatedUser = userRepository.findByUsername(user.getUsername());
        updatedUser.setPassword(encryptPassword(newPassword));
        userRepository.save(user);
    }

    public void updateRole(User user, UserRoles role) throws UserException {
        if(!userRepository.existsByUsername(user.getUsername())) {
            throw UserException.USER_NOT_FOUND.setUser(user);
        }

        if(role == UserRoles.SUPER_ADMIN) {
            throw UserException.SUPER_ADMIN_ALREADY_EXISTS;
        }

        User updatedUser = userRepository.findByUsername(user.getUsername());
        updatedUser.setRole(role);
        userRepository.save(user);
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean isSuperAdminExists() {
        return userRepository.countByRole(UserRoles.SUPER_ADMIN) > 0;
    }
}
