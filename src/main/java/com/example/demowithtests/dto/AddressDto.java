package com.example.demowithtests.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Builder
public class AddressDto {
    public Long id;

    public Boolean addressHasActive = Boolean.TRUE;

    public String country;

    public String city;

    public String street;

    //todo: dfhgjkdfhg Jira - 5544
    public Date date = Date.from(Instant.now());
}

