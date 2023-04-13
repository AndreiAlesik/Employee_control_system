package com.example.demowithtests.dto.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@ToString
@Getter
@Setter
public class PassportResponseDto {

    @Schema(description = "ID of the passport.", example = "21020")
    public Integer id;
    @Schema(description = "Serial number of an employee in the passport.", example = "UI-HFKS-138539J-FJS2")
    public final UUID serialNumber = UUID.randomUUID();
    @Schema(description = "First name of an employee in the passport.", example = "Weak")
    public String firstName;
    @Schema(description = "Second name of an employee in the passport.", example = "John")
    public String secondName;
    @Schema(description = "Birth date of an employee in the passport", example = "12.12.2012")
    public LocalDate birthDate;
    @Schema(description = "Registrations of an employee in the passport.", example = "registrations")
    public Set<RegistrationDto> registrations = new HashSet<>();

}