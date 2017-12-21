package com.bookstore.bookstore;

import com.bookstore.bookstore.config.SecurityUtility;
import com.bookstore.bookstore.domain.security.Role;
import com.bookstore.bookstore.domain.security.User;
import com.bookstore.bookstore.domain.security.UserRole;
import com.bookstore.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("Phillip");
        user.setLastName("Nzuli");
        user.setUsername("pmn");
        user.setPassword(SecurityUtility.passwordEncoder().encode("pmn"));
        user.setEmail("mwanikiphillip@gmail.com");
        user.setEnabled(true);
        Set<UserRole> userRoles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        userRoles.add(new UserRole(user, role));

        userService.createUser(user, userRoles);
        userRoles.clear();

        User user1 = new User();
        user1.setFirstName("Admin");
        user1.setLastName("Boss");
        user1.setUsername("admin");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
        user1.setEmail("admin@gmail.com");
        user1.setEnabled(true);
        Set<UserRole> user1Roles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_ADMIN");
        user1Roles.add(new UserRole(user1, role1));

        userService.createUser(user1, user1Roles);
        user1Roles.clear();
    }
}
