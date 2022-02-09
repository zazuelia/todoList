package com.joseelia.todoList.config;

import com.joseelia.todoList.model.Role;
import com.joseelia.todoList.model.User;
import com.joseelia.todoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration @RequiredArgsConstructor
public class UserConfig {

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService userService) {

        return args -> {

            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "user1", "User 1", "123", new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "user2", "User 2","123", new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "user3", "User 3","123", new ArrayList<>(), new ArrayList<>()));

            userService.addRoleToUser("user1", "ROLE_ADMIN");
            userService.addRoleToUser("user1", "ROLE_MANAGER");
            userService.addRoleToUser("user1", "ROLE_SUPER_ADMIN");

            userService.addRoleToUser("user2", "ROLE_ADMIN");
            userService.addRoleToUser("user2", "ROLE_MANAGER");
            userService.addRoleToUser("user2", "ROLE_SUPER_ADMIN");

            userService.addRoleToUser("user3", "ROLE_ADMIN");
            userService.addRoleToUser("user3", "ROLE_MANAGER");
            userService.addRoleToUser("user3", "ROLE_SUPER_ADMIN");


        };
    }
}
