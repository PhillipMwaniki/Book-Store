package com.bookstore.bookstore.services;

import com.bookstore.bookstore.domain.security.User;
import com.bookstore.bookstore.domain.security.UserRole;

import java.util.Set;

public interface UserService {

    User createUser(User user, Set<UserRole> userRoles);
}
