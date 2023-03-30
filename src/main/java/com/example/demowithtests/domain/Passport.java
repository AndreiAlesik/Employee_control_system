package com.example.demowithtests.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "passport")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Passport {
    @Id
    private Integer id;
    private final UUID serialNumber=UUID.randomUUID();
    private String firstName;
    private String secondName;

    private LocalDate dateOfBirth;

}
