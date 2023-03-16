package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.Address;
import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.AddressDto;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    EmployeeReadDto employeeToEmployeeReadDto(Employee employee);
    List<EmployeeReadDto> employeeToEmployeeReadDto(List<Employee> employeeList);

    EmployeeDto employeeToEmployeeDto (Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);


    Set<AddressDto> addressToAddressDto(Set<Address> address);
    Address addressDtoToAddress(AddressDto address);
}
