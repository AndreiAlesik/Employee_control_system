package com.example.demowithtests.domain.passport;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registrations")
@Data
@Builder
@ToString
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID registrationId = UUID.randomUUID();

    private String country;
    private String city;
    private String street;
    private Date activatedUntil;

    private Boolean addressHasActive = Boolean.TRUE;
    private Date registrationDate = Date.from(Instant.now());

}