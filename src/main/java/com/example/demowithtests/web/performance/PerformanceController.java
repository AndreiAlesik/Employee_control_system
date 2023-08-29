package com.example.demowithtests.web.performance;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.dto.StatsObject;
import com.example.demowithtests.dto.employee.EmployeeCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PerformanceController {
    @GetMapping("/spring/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint get repository statistic.", description = "Testing repository method performance with spring data implementation.", tags = {"Employee"})
    StatsObject<List<Employee>> findEmployeeByName(@PathVariable("name") String name);

    @GetMapping("/jpql/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint get repository statistic.", description = "Testing repository method performance with jpql query.", tags = {"Employee"})
    StatsObject<List<Employee>> findEmployeeByNameJPQL(@PathVariable("name") String name);

    @GetMapping("/jpql-graph/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint get repository statistic.", description = "Testing repository method performance with jpql query and entity graph.", tags = {"Employee"})
    StatsObject<List<Employee>> findEmployeeByNameJPQLAndEntityGraph(@PathVariable("name") String name);

    @GetMapping("/native-sql/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint get repository statistic.", description = "Testing repository method performance with native sql.", tags = {"Employee"})
    StatsObject<List<Employee>> findEmployeeByNameNativeSQL(@PathVariable("name") String name);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/fill")
    String fillDataBase();

    @PutMapping("/update-email/{email}/{id}")
    void updateEmployeeEmailById(@PathVariable("email") String email, @PathVariable("id") Integer id);

    @DeleteMapping("/delete-by-email/{email}")
    void deleteEmployeeByEmail(@PathVariable("email") String email);

    @PostMapping("/create")
    void createEmployeeById(@RequestBody EmployeeCreateDto EmployeeCreateDto);
}
