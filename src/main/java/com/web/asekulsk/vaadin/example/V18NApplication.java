package com.web.asekulsk.vaadin.example;

import com.web.asekulsk.vaadin.example.spring.security.model.Role;
import com.web.asekulsk.vaadin.example.spring.security.model.User;
import com.web.asekulsk.vaadin.example.spring.security.repository.UserRepository;
import com.web.asekulsk.vaadin.example.spring.security.type.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * Spring boot Tipstar application.
 *
 * @author Andreas Sekulski
 */
@SpringBootApplication
public class V18NApplication implements CommandLineRunner {

    /**
     * User repository to create mockup user data.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Password encoder to encode raw passwords as hash value.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Main method to execute spring web application.
     *
     * @param args Arguments to use.
     */
    public static void main(String[] args) {
        SpringApplication.run(V18NApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(RoleType.values()).forEach(x -> {

            // Obtain roles for example ROLE_ADMIN split underscore
            // and take second value "ADMIN" and make value lower case "admin"
            String name = x.name().split("_")[1].toLowerCase();

            User user = new User();
            user.setUsername(name);
            user.setPassword(passwordEncoder.encode("1234"));

            Role role = new Role();
            role.setType(x);
            role.setUser(user);

            user.setRoles(Arrays.asList(role));
            userRepository.save(user);

        });
    }
}
