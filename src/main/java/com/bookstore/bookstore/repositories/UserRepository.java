package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
