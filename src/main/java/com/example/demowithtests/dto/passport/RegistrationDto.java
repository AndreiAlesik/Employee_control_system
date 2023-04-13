package com.example.demowithtests.dto.passport;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class RegistrationDto {

    public Long id;
    public UUID registrationId = UUID.randomUUID();
    public String country;
    public String city;
    public String street;
    public Date activatedUntil;
    public Boolean addressHasActive = Boolean.TRUE;
    public Date registrationDate = Date.from(Instant.now());

}

