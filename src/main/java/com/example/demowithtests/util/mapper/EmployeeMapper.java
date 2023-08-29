package com.example.demowithtests.util.mapper;

import com.example.demowithtests.domain.employee.Address;
import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.dto.employee.AddressDto;
import com.example.demowithtests.dto.employee.EmployeeCreateDto;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeReadDto employeeToEmployeeReadDto(Employee employee);
    List<EmployeeReadDto> employeeToEmployeeReadDto(List<Employee> employeeList);

    EmployeeDto employeeToEmployeeDto (Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);


    Set<AddressDto> addressToAddressDto(Set<Address> address);
    Address addressDtoToAddress(AddressDto address);

    Employee fromCreateDto(EmployeeCreateDto employeeCreateDto);
}
