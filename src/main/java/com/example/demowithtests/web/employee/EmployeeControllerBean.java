package com.example.demowithtests.web.employee;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.service.employee.EmployeeService;
import com.example.demowithtests.util.mapper.EmployeeMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class EmployeeControllerBean implements EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;


    @Override
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("Controller ==> saveEmployee() - start: employeeDto = {}", employeeDto);
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        EmployeeDto dto = employeeMapper.employeeToEmployeeDto(employeeService.create(employee));
        log.info("Controller ==> saveEmployee() - end: employeeReadDto = {}", dto);
        return dto;
    }

    @Override
    public void savePersistence(Employee employee) {
        employeeService.save(employee);
    }


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> getAllUsers() {
        log.info("Controller ==> getAllUsers() - start: ");
        List<Employee> employees = employeeService.getAll();
        List<EmployeeReadDto> employeesReadDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesReadDto.add(
                    employeeMapper.employeeToEmployeeReadDto(employee)
            );
        }

        log.info("Controller ==> getAllUsers() - end: ");
        return employeesReadDto;
    }


    @Override
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        log.info("Controller ==> getEmployeeById() - start: id = {}", id);
        EmployeeReadDto employeeReadDtoDto = employeeMapper.employeeToEmployeeReadDto(
                employeeService.getById(id)
        );
        log.info("Controller ==> getEmployeeById() - end: employeeReadDto = {}", employeeReadDtoDto);
        return employeeReadDtoDto;
    }


    //Обновление юзера
    @SneakyThrows
    public EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto) {
        log.info("Controller ==> refreshEmployee() - start: id = {}, employeeDto = {}", id, employeeDto);
        Integer parseId = Integer.parseInt(id);
        EmployeeReadDto employeeReadDto = employeeMapper.employeeToEmployeeReadDto(
                employeeService.updateById(parseId, employeeMapper.employeeDtoToEmployee(employeeDto)
                )
        );
        log.info("Controller ==> refreshEmployee() - end: id = {}, employeeReadDto = {}", id, employeeReadDto);
        return employeeReadDto;
    }

    //Удаление по id
    @Override
    public void removeEmployeeById(@PathVariable String id) {
        log.info("Controller ==> removeEmployeeById() - start: id = {}", id);
        Integer parseId = Integer.parseInt(id);
        employeeService.removeById(parseId);
        log.info("Controller ==> removeEmployeeById() - end: ");

    }

    //Удаление всех юзеров

    public void removeAllUsers() {
        log.info("Controller ==> removeAllUsers() - start: ");
        employeeService.removeAll();
        log.info("Controller ==> removeAllUsers() - end: ");
    }


    //@PatchMapping("/replaceNull")

    public void replaceNull() {
        log.info("Controller ==> replaceNull() - start: ");
        employeeService.processor();
        log.info("Controller ==> replaceNull() - end: ");
    }


    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text) {
        log.info("Controller ==> sendEmailByCountry() - start: country = {}, text = {}", country, text);
        employeeService.sendEmailByCountry(country, text);
        log.info("Controller ==> sendEmailByCountry() - end: ");
    }


    public void sendEmailByCity(@RequestParam String city, @RequestParam String text) {
        log.info("Controller ==> sendEmailByCity() - start: city = {}, text = {}", city);
        employeeService.sendEmailByCountry(city, text);
        log.info("Controller ==> sendEmailByCity() - end: ");
    }

    @Override
    public EmployeeReadDto addWorkplace(Integer employeeId, Integer workplaceId) {
        log.info("Controller ==> addWorkplace() - start: employeeId = {}, workplaceId = {}", employeeId, workplaceId);

        var employeeReadDto = employeeMapper.employeeToEmployeeReadDto(employeeService.addWorkplace(employeeId, workplaceId));
        log.info("Controller ==> addWorkplace() - end: employeeReadDto = {}", employeeReadDto);
        return employeeReadDto;
    }

    @Override
    public void detachingEmployee(Integer id) {
        employeeService.detachEmployee(id);
    }

    @GetMapping("/metricsForEmployee")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> metrics(@RequestParam String country) {
        log.info("Controller ==> metrics() - start: country = {}", country);
        List<Employee> statistics = employeeService.metricsForEmployee(country);
        log.info("Controller ==> metrics() - end: statistics = {}", country);
        return statistics;
    }

    @Override
    public void removeEntityManager(Integer id) {
        employeeService.removeEntityManagerEmployee(id);
    }
}
