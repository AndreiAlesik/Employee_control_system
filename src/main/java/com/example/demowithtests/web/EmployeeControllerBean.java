package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.service.employee.Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.demowithtests.util.config.EntityMapper;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class EmployeeControllerBean implements EmployeeController {

    private final Service service;
    private final EntityMapper entityMapper;


    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("Controller ==> saveEmployee() - start: employeeDto = {}", employeeDto);
        Employee employee = entityMapper.employeeDtoToEmployee(employeeDto);
        EmployeeDto dto = entityMapper.employeeToEmployeeDto(service.create(employee));
        log.info("Controller ==> saveEmployee() - end: employeeReadDto = {}", dto);
        return dto;
    }


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> getAllUsers() {
        log.info("Controller ==> getAllUsers() - start: ");
        List<Employee> employees = service.getAll();
        List<EmployeeReadDto> employeesReadDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesReadDto.add(
                    entityMapper.employeeToEmployeeReadDto(employee)
            );
        }

        log.info("Controller ==> getAllUsers() - end: ");
        return employeesReadDto;
    }


    @Override
    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        log.info("Controller ==> getEmployeeById() - start: id = {}", id);
        EmployeeReadDto employeeReadDtoDto = entityMapper.employeeToEmployeeReadDto(
                service.getById(id)
        );
        log.info("Controller ==> getEmployeeById() - end: employeeReadDto = {}", employeeReadDtoDto);
        return employeeReadDtoDto;
    }


    //Обновление юзера
    @SneakyThrows
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto) {
        log.info("Controller ==> refreshEmployee() - start: id = {}, employeeDto = {}", id, employeeDto);
        Integer parseId = Integer.parseInt(id);
        EmployeeReadDto employeeReadDto = entityMapper.employeeToEmployeeReadDto(
                service.updateById(parseId, entityMapper.employeeDtoToEmployee(employeeDto)
                )
        );
        log.info("Controller ==> refreshEmployee() - end: id = {}, employeeReadDto = {}", id, employeeReadDto);
        return employeeReadDto;
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable String id) {
        log.info("Controller ==> removeEmployeeById() - start: id = {}", id);
        Integer parseId = Integer.parseInt(id);
        service.removeById(parseId);
        log.info("Controller ==> removeEmployeeById() - end: ");

    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        log.info("Controller ==> removeAllUsers() - start: ");
        service.removeAll();
        log.info("Controller ==> removeAllUsers() - end: ");
    }


    //@PatchMapping("/replaceNull")
    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull() {
        log.info("Controller ==> replaceNull() - start: ");
        service.processor();
        log.info("Controller ==> replaceNull() - end: ");
    }

    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text) {
        log.info("Controller ==> sendEmailByCountry() - start: country = {}, text = {}", country,text);
        service.sendEmailByCountry(country, text);
        log.info("Controller ==> sendEmailByCountry() - end: ");
    }

    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String city, @RequestParam String text) {
        log.info("Controller ==> sendEmailByCity() - start: city = {}, text = {}", city);
        service.sendEmailByCountry(city, text);
        log.info("Controller ==> sendEmailByCity() - end: ");
    }

    @GetMapping("/metricsForEmployee")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> metrics(@RequestParam String country) {
        log.info("Controller ==> metrics() - start: country = {}", country);
        List<Employee> statistics= service.metricsForEmployee(country);
        log.info("Controller ==> metrics() - end: statistics = {}", country);
        return statistics;
    }

}
