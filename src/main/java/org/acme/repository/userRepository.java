package org.acme.repository;

import org.acme.models.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class userRepository implements PanacheRepository<user>{
    public user findByEmail(String email){
        return find("email", email).firstResult();
    }

    public user findByUsername(String username){
        return find("username", username).firstResult();
    }
}
