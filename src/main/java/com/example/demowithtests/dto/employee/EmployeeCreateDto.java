package com.example.demowithtests.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmployeeCreateDto(
        String name,
        String country,
        String email)
{
}
