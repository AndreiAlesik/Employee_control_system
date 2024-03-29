package com.example.demowithtests.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    public Integer id;

    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    @Schema(description = "Country of an employee.", example = "Poland", required = true)
    public String country;
    @Schema(description = "Email of an employee.", example = "smth@gmail.com", required = true)
    public String email;

    public String password;
    @Schema(description = "Set of photos of an employee.", example = "photos", required = true)
    public Set<PhotoDto> photos = new HashSet<>();
    @Schema(description = "Set of addresses of an employee.", example = "addresses", required = true)
    public Set<AddressDto> addresses = new HashSet<>();

}
