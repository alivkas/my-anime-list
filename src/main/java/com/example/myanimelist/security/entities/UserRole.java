package com.example.myanimelist.security.entities;

import com.example.myanimelist.api.entities.base.BasicEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "public")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRole extends BasicEntity implements GrantedAuthority {

    @Column(name = "role")
    String role;

    @ManyToMany(mappedBy = "authorities")
    Set<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return role;
    }
}
