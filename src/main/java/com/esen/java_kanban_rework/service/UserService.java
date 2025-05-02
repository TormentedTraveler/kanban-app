package com.esen.java_kanban_rework.service;

import com.esen.java_kanban_rework.entity.User;
import com.esen.java_kanban_rework.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email" + email));
    }
    public User getUserById (Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    public User loadOrCreateUserForOauth(OAuth2User oauthUser) {
        if (userRepository.findByEmail(oauthUser.getAttribute("email")).isEmpty()) {
            userRepository.save(User.builder()
                            .oauthId(oauthUser.getAttribute("id").toString())
                            .email(oauthUser.getAttribute("email"))
                            .firstname(oauthUser.getAttribute("login"))
                    .build());
        }

        return userRepository.findByEmail(oauthUser.getAttribute("email")).orElseThrow(() -> new UsernameNotFoundException("User not found with email" + oauthUser.getAttribute("email")));
    }
}
