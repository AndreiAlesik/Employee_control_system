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
    @Schema(description = "Id of an employee.", example = "Billy", required = true)
    public Long id;
    @Schema(description = "Address status of an employee.", example = "False|True", required = true)
    public Boolean addressHasActive = Boolean.TRUE;

    @Schema(description = "Country of an employee.", example = "Ukraine", required = true)
    public String country;
    @Schema(description = "City of an employee.", example = "Kyiv", required = true)
    public String city;
    @Schema(description = "Street of an employee.", example = "Parkova", required = true)
    public String street;
    @Schema(description = "Date of registration.", example = "2023-10-10", required = true)
    //todo: dfhgjkdfhg Jira - 5544
    public Date date = Date.from(Instant.now());
}

