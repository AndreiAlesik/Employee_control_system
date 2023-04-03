package com.example.demowithtests.dto.workplace;

import com.example.demowithtests.domain.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@ToString
public class WorkplaceRequestDto {
    @Schema(description = "First name of an employee in the passport.", example = "Weak")
    public Integer id;
    @Schema(description = "First name of an employee in the passport.", example = "Weak")
    public String name;
    @Schema(description = "First name of an employee in the passport.", example = "Weak")
    public String address;
//    @Schema(description = "First name of an employee in the passport.", example = "Weak")
//    public Set<Employee> employees = new HashSet<>();
}
