package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.Address;
import com.example.demowithtests.domain.Employee;

import java.util.List;
import java.util.Set;

public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeReadDto employeeToEmployeeReadDto(Employee employee);
    List<EmployeeReadDto> employeeToEmployeeReadDto(List<Employee> employeeList);

    EmployeeDto employeeToEmployeeDto (Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);


    Set<AddressDto> addressToAddressDto(Set<Address> address);
    Address addressDtoToAddress(AddressDto address);
}
