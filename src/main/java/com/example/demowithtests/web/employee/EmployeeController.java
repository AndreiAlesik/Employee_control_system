package com.example.demowithtests.web.employee;

import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface EmployeeController {
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto);

    @Operation(summary = "This is endpoint to get all employees.", description = "Create request to get all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data successfully get."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto getEmployeeById(@PathVariable Integer id);

    @Operation(summary = "This is endpoint to refresh employee data.", description = "Create request to refresh employee data.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data successfully updated."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto);

    @Operation(summary = "This is endpoint to remove an employee.", description = "Create request to remove an employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The employee is successfully removed from database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already removed")})
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeEmployeeById(@PathVariable String id);

    @Operation(summary = "This is endpoint to remove all employee.", description = "Create request to remove all employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. All employee is successfully removed from database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee data already deleted")})
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeAllUsers();

    @Operation(summary = "This is endpoint replace null values of an employee.", description = "Create request null values of an employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. Null values successfully replaced."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    void replaceNull();

    @Operation(summary = "This is endpoint to send mail by country to employee.", description = "Create request to send mail by country employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Email successfully sent."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    void sendEmailByCountry(@RequestParam String country, @RequestParam String text);

    @Operation(summary = "This is endpoint to send mail by city to employee.", description = "Create request to send mail by city employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Email successfully sent."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    void sendEmailByCity(@RequestParam String city, @RequestParam String text);

    @Operation(summary = "This is endpoint to send mail by city to employee.", description = "Create request to send mail by city employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Email successfully sent."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    @PostMapping("/employee/{id}/workplace/{workplaceId}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto addWorkplace(@PathVariable("id") Integer id, @PathVariable("workplaceId") Integer workplaceId);
}
