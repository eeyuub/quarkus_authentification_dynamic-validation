package org.acme.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper=true)
public class user extends PanacheEntity{
    private String firstname;
    private String lastname;
    @NotBlank(message="username may not be blank")
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public String getPassword() {
        return password;
    }

    private String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public boolean checkPassword(String password) {
        return BCrypt.verifyer().verify(password.toCharArray(), this.password).verified;
    }
}
