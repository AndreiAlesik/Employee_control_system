package com.example.demowithtests.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeReadDto {
    public String name;
    //public String country;
    public String email;
    public Set<PhotoDto> photos=new HashSet<>();
    public Set<AddressDto> addresses=new HashSet<>();

}