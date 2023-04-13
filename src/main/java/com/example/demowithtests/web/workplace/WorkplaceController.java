package com.example.demowithtests.web.workplace;

import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface WorkplaceController {
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @PostMapping("/workplace")
    @ResponseStatus(HttpStatus.CREATED)
    WorkplaceResponseDto saveWorkplace(@RequestBody @Valid WorkplaceRequestDto workplaceRequestDto, BindingResult result);

    @Operation(summary = "This is endpoint to get all employees.", description = "Create request to get all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data successfully get."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    @GetMapping(value = "/workplace/{id}")
    @ResponseStatus(HttpStatus.OK)
    WorkplaceResponseDto getWorkplaceById(@PathVariable Integer id);
}
