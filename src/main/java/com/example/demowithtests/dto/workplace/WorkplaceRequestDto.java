package com.example.demowithtests.dto.workplace;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;



@ToString
public class WorkplaceRequestDto {


    @NotNull
    @Schema(description = "Name of a workplace.", example = "name")
    public String name;

    @Schema(description = "Address of a workplace.", example = "address")
    public String address;

//    @NotNull
//    @Schema(description = "Start time of a reservation.", example = "2007-12-03T10:15:30")
//    private OffsetDateTime startTimeReservation;
//
//    @NotNull
//    @Schema(description = "End time of a reservation.", example = "2007-12-04T10:15:30")
//    private OffsetDateTime endTimeReservation;

    @Min(value = 1, message = "Available sitting places must be at least 1")
    @NotNull
    @Schema(description = "First name of an employee in the workplace.", example = "address",required = true)
    public Integer availableSittingPlaces;

//    @Schema(description = "First name of an employee in the passport.", example = "Weak")
//    public Set<Employee> employees = new HashSet<>();
}
