package org.acme.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.acme.models.user;
import org.acme.repository.user_repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@ApplicationScoped
public class user_service {

    @Inject
    private user_repository user_repository;
    @Inject
    Validator validator;

    public List<String> validateUser(user user) {
        Set<ConstraintViolation<user>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return errorMessages;
        }
        return null;
    }

    public void create_user(user user) {
       /*  if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");

        } */
        user_repository.persist(user);
    }

}
