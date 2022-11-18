package com.example.workshopmongo.config;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User walter = new User(null, "Walter White", "walter@white.com");
        User jesse = new User(null, "Jesse Pinkman", "jesse@pinkman.com");
        User gus = new User(null, "Gus Fring", "gus@fring.com");

        List<User> users = Arrays.asList(walter, jesse, gus);

        userRepository.saveAll(users);

    }
}
