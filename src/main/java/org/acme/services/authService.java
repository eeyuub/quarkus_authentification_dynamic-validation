package org.acme.services;

import org.acme.models.user;
import org.acme.security.jwtSecurity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class authService {

    @Inject
    userService user_service;

    @Inject
    jwtSecurity jwt_security;

    public String login(user user) {

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("email may not be blank");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("password may not be blank");
        }

        user user_found = user_service.getUserByEmail(user.getEmail());

        if (user_found == null) {
            throw new IllegalArgumentException("user not found");
        }

        if (!user_found.checkPassword(user.getPassword())) {
            throw new IllegalArgumentException("Password is incorrect");
        }

        String token = jwt_security.generateToken(user_found);

        return token;
    }

}
