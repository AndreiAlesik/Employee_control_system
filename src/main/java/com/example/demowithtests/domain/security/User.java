package com.example.demowithtests.domain.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    private Boolean enabled;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Authority> authority;
}