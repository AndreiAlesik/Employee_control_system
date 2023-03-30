package com.example.demowithtests.dto.passport;

import java.time.LocalDate;
import java.util.UUID;

public class ResponseDto {
    public Integer id;
    public final UUID serialNumber=UUID.randomUUID();
    public String firstName;
    public String secondName;

    public LocalDate dateOfBirth;
}
