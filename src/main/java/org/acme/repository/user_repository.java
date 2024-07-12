package org.acme.repository;

import org.acme.models.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class user_repository implements PanacheRepository<user>{
    
}
