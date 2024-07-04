package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
public class DataInitializer {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User adminUser = new User("admin", passwordEncoder.encode("admin"));
        adminUser.setFirstName("Admin");
        adminUser.setLastName("User");
        adminUser.setAge(30);
        adminUser.setEmail("admin@example.com");
        adminUser.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
        userRepository.save(adminUser);

        User userUser1 = new User("user1", passwordEncoder.encode("user1"));
        userUser1.setFirstName("John");
        userUser1.setLastName("Doe");
        userUser1.setAge(25);
        userUser1.setEmail("john.doe@example.com");
        userUser1.setRoles(Collections.singleton(userRole));
        userRepository.save(userUser1);

        User userUser2 = new User("user2", passwordEncoder.encode("user2"));
        userUser2.setFirstName("Jane");
        userUser2.setLastName("Smith");
        userUser2.setAge(28);
        userUser2.setEmail("jane.smith@example.com");
        userUser2.setRoles(Collections.singleton(userRole));
        userRepository.save(userUser2);


    }
}