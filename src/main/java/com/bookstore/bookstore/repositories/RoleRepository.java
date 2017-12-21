package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
