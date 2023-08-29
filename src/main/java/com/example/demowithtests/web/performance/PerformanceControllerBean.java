package com.example.demowithtests.web.performance;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.dto.StatsObject;
import com.example.demowithtests.dto.employee.EmployeeCreateDto;
import com.example.demowithtests.service.employee.EmployeeService;
import com.example.demowithtests.util.mapper.EmployeeMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/performance", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Performance", description = "Performance API")
@Slf4j
public class PerformanceControllerBean implements PerformanceController{
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public PerformanceControllerBean(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public StatsObject<List<Employee>> findEmployeeByName(String name) {
        return employeeService.findEmployeeByName(name);
    }

    @Override
    public StatsObject<List<Employee>> findEmployeeByNameJPQL(String name) {
        return employeeService.findEmployeeByNameJPQL(name);
    }

    @Override
    public StatsObject<List<Employee>> findEmployeeByNameJPQLAndEntityGraph(String name) {
        return employeeService.findEmployeeByNameJPQLAndEntityGraph(name);
    }

    @Override
    public StatsObject<List<Employee>> findEmployeeByNameNativeSQL(String name) {
        return employeeService.findEmployeeByNameNativeSQL(name);
    }

    public String fillDataBase() {
        log.info("fillDataBase() LoaderController - start: ");
        employeeService.generateData();
        String count = "Amount clients: " + employeeService.count();
        log.info("fillDataBase() LoaderController - end: count = {}", count);
        return count;
    }

    @Override
    public void updateEmployeeEmailById(String email, Integer id) {
        employeeService.updateEmployeeEmailById(email, id);
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        employeeService.deleteEmployeeByEmail(email);
    }

    @Override
    public void createEmployeeById(EmployeeCreateDto employeeCreateDto) {
        var employee=employeeMapper.fromCreateDto(employeeCreateDto);
        employeeService.createEmployeeById(employee);
    }
}

