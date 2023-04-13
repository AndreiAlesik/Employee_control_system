package com.example.demowithtests.web.passport;

import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.dto.passport.RegistrationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface PassportController {

    @Operation(summary = "This is endpoint to add a new passport.", description = "Create request to add a new passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new passport is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified password request not found."),
            @ApiResponse(responseCode = "409", description = "Password already exists.")})
    @PostMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    PassportResponseDto savePassport(@RequestBody PassportRequestDto requestDto);

    @Operation(summary = "This is endpoint to get all passports.", description = "Create request to get all passports.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. List of all passports was received."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @GetMapping("/passports")
    @ResponseStatus(HttpStatus.OK)
    List<PassportResponseDto> getAllPassports();

    @Operation(summary = "This is endpoint to get passport by ID.", description = "Create request to get passport by ID.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Passport was received."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Passport does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @GetMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto getPassportById(@PathVariable Integer id);

    @Operation(summary = "This is endpoint to refresh passport by ID & new body.", description = "Create request to refresh passport by ID and body.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Passport was refreshed."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Passport does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto refreshPassport(@PathVariable("id") Integer id, @RequestBody PassportRequestDto requestDto);

    @Operation(summary = "This is endpoint to find passport by registration ID.", description = "Create request to find passport by registration ID.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Passport was found."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Registration does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @GetMapping("/passports/reg/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto findPassportByRegistration(@PathVariable("id") UUID id);

    @Operation(summary = "This is endpoint to add registration to passport by ID.", description = "Create request to add registration.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Registration was added."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Passport does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/passports/add/{id}")
    PassportResponseDto addRegistrationToPassport(@PathVariable("id") Integer id, @RequestBody RegistrationDto registrationDto);

    @Operation(summary = "This is endpoint to deactivate registration of passport by country.", description = "Create request to deactivate registration.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Registration was deactivated."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Country does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PatchMapping("/passports/deactivate/reg/{country}")
    List<PassportResponseDto> deactivateRegistrations(@PathVariable("country") String country);



}
