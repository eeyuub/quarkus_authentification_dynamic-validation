package org.acme.models;

import org.acme.enums.roles;
import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonProperty;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.quarkus.elytron.security.common.BcryptUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper=true)
public class user extends PanacheEntity{
    private String firstname;
    private String lastname;
    @Column(unique = true)
    @NotBlank(message="username may not be blank")
    private String username;
    private roles role;
    @Column(unique = true)
    private String email;
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void hashPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password);
    }

    public boolean checkPassword(String password) {
        return BcryptUtil.matches(password, this.password);
    }
}
