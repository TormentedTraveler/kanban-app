package com.esen.java_kanban_rework.utils;
import com.esen.java_kanban_rework.entity.User;
import com.esen.java_kanban_rework.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {
    private final UserRepository userRepository;

    public BootstrapData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void createUsers() {
        User user1 = User.builder()
                .email("test@mail.com")
                .firstname("test")
                .lastname("test")
                .password("123456")
                .build();
        User user2 = User.builder()
                .email("test2@mail.com")
                .firstname("test2")
                .lastname("test2")
                .password("123456")
                .build();

        userRepository.saveAll(List.of(user1, user2));
        return;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            createUsers();
            System.out.println(userRepository.findAll());
        }
    }
}
