package com.example.demowithtests.service.employee;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.office.Workplace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    List<Employee> processor();

    List<Employee> sendEmailByCountry(String country, String text);

    List<Employee> sendEmailByCity(String city, String text);

    List<Employee> metricsForEmployee(String country);

    Employee addPassport(Integer employeeId);

    Employee addWorkplace(Integer employeeId, Integer workplaceId);

    Employee save(Employee employee);

    void detachEmployee(Integer id);

    void removeEntityManagerEmployee(Integer id);

    Employee findEmployee(Integer id);

    Employee mergeEmployee(Employee employee);

    Page<Employee> getAllWithPagination(Pageable pageable);
}
