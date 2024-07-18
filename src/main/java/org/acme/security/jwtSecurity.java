package org.acme.security;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import org.acme.models.user;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class jwtSecurity {
    

    public String generateToken(user user) {
        
        String token = Jwt.issuer("https://localhost/issuer")
                .upn(user.getEmail())
                .groups(new HashSet<>(Arrays.asList("User")))
                .claim("username", user.getUsername())
                .claim("role", "admin")
                .claim("email", user.getEmail())
                .expiresIn(Duration.ofMinutes(1))
                .sign();
        return token;
    }

    
}
