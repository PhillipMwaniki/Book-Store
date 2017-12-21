package com.bookstore.bookstore.services.Impl;

import com.bookstore.bookstore.domain.security.User;
import com.bookstore.bookstore.domain.security.UserRole;
import com.bookstore.bookstore.repositories.RoleRepository;
import com.bookstore.bookstore.repositories.UserRepository;
import com.bookstore.bookstore.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public User createUser(User user, Set<UserRole> userRoles) {

        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} already exists. Nothing will be done.", user.getUsername());
        } else {
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }
        return localUser;
    }
}
