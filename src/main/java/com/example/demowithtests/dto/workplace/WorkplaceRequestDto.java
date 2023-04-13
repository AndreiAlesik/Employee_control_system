package com.example.demowithtests.dto.workplace;

import com.example.demowithtests.domain.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@ToString
public class WorkplaceRequestDto {
//    @Schema(description = "Id of a workplace.", example = "id")
//    public Integer id;

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
