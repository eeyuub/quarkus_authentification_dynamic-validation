package org.acme.services;

import org.acme.enums.roles;
import org.acme.models.user;
import org.acme.repository.userRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class userService {

    @Inject
    private userRepository user_repository;

    public void createUser(user user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if(isUsernameExists(user.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }
        if(isEmailExists(user.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        user.setRole(roles.USER);
        user.hashPassword(user.getPassword());
        user_repository.persist(user);
    }

    public user getUserByEmail(String email) {
        return user_repository.findByEmail(email);
    }

    private boolean isUsernameExists(String username) {
        user user = user_repository.findByUsername(username);
        return user != null;
    }

    private boolean isEmailExists(String email) {
        user user = user_repository.findByEmail(email);
        return user != null;
    }

}
