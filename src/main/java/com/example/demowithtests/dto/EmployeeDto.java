package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeDto {

    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    @Schema(description = "Country of an employee.", example = "Poland", required = true)
    public String country;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String email;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public Set<PhotoDto> photos = new HashSet<>();
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public Set<AddressDto> addresses = new HashSet<>();


    public EmployeeDto() {

    }
    //public Date creationTime = Date.from(Instant.now());
}
