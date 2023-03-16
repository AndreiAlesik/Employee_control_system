package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Builder
public class AddressDto {
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public Long id;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public Boolean addressHasActive = Boolean.TRUE;

    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String country;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String city;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String street;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    //todo: dfhgjkdfhg Jira - 5544
    public Date date = Date.from(Instant.now());
}

