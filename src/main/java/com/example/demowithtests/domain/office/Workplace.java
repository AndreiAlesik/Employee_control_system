package com.example.demowithtests.domain.office;


import com.example.demowithtests.domain.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workplaces")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private Boolean isActive=Boolean.TRUE;
    private OffsetDateTime startTimeReservation;
    private OffsetDateTime endTimeReservation;
    private Integer availableSittingPlaces;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "workplaces")
    @JsonIgnore
    private Set<Employee> employees=new HashSet<>();
}
