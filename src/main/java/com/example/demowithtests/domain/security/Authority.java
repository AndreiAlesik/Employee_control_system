package com.example.demowithtests.domain.security;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@Data
public class Authority {
    @EmbeddedId
    private AuthorityKey id;
}

@Embeddable
@Data
class AuthorityKey implements Serializable {
    private String authority;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User userAuth;
}