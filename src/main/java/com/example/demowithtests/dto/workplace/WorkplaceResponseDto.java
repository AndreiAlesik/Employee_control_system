package com.example.demowithtests.dto.workplace;

import com.example.demowithtests.domain.employee.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
public class WorkplaceResponseDto {

    @Schema(description = "First name of an employee in the passport.", example = "Weak")
    public String name;
    @Schema(description = "First name of an employee in the passport.", example = "Weak")
    public String address;
//    @Schema(description = "First name of an employee in the passport.", example = "Weak")
//    public Set<Employee> employees = new HashSet<>();
}
