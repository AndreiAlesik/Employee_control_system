package com.example.demowithtests.service.employee;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.service.passport.PassportService;
import com.example.demowithtests.service.workplace.WorkplaceService;
import com.example.demowithtests.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class EmployeeServiceBean implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PassportRepository passportRepository;

    private final PassportService passportService;

    private final WorkplaceService workplaceService;

    @Override
    public Employee create(Employee employee) {
        log.debug("Service ==> create() - start: employee = {}", employee);
        employee.setIsDeleted(Boolean.FALSE);
        Employee employeeCreated = employeeRepository.save(employee);
        log.debug("Service ==> create() - end: employee = {}", employeeCreated);
        return employeeCreated;
    }

    @Override
    public List<Employee> getAll() {
        log.debug("Service ==> getAll() - start: ");
        try {
            List<Employee> allEmployees = employeeRepository.findAll();
            log.debug("Service ==> getAll() - end: ");
            return allEmployees;
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException();
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }

    @Override
    public Employee getById(Integer id) {
        log.debug("Service ==> getById() - start: id = {}", id);
        if (id == null) {
            throw new WrongArgumentException();
        }
        var employee = employeeRepository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceNotFoundException::new);
        if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }
        log.debug("Service ==> getById() - end: employee = {}", employee);
        return employee;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        log.debug("Service ==> updateById() - start: id = {}, employee = {}", id, employee);
        try {
            return employeeRepository.findById(id)
                    .map(entity -> {
                        entity.setName(employee.getName());
                        entity.setEmail(employee.getEmail());
                        entity.setCountry(employee.getCountry());
                        log.debug("Service ==> updateById() - end: employee = {}", entity);
                        return employeeRepository.save(entity);
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
        } catch (IllegalArgumentException e) {
            throw new WrongArgumentException();
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }

    @Override
    public void removeById(Integer id) {
        log.debug("Service ==> removeById() - start: id = {}", id);
        try {
            var employee = employeeRepository.findById(id)
                    // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                    .orElseThrow(ResourceWasDeletedException::new);
            employee.setIsDeleted(true);
            employeeRepository.save(employee);
            log.debug("Service ==> removeById() - end: deleted employee = {}", employee);
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }

    @Override
    public void removeAll() {
        log.debug("Service ==> removeAll() - start: ");
        try {
            employeeRepository.deleteAll();
            log.debug("Service ==> removeAll() - end: ");
        } catch (DataAccessException e) {
            throw new AccessException();
        }

    }

    @Override
    public List<Employee> processor() {
        log.debug("replace null  - start");
        List<Employee> replaceNull = employeeRepository.findEmployeeByIsDeletedNull();
        log.debug("replace null after replace: " + replaceNull);
        for (Employee emp : replaceNull) {
            emp.setIsDeleted(Boolean.FALSE);
        }
        log.debug("replaceNull = {} ", replaceNull);
        log.debug("replace null  - end:");
        return employeeRepository.saveAll(replaceNull);
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        log.debug("Service ==> sendEmailByCountry() - start: country = {}, text = {}", country, text);
        List<Employee> employees = employeeRepository.findEmployeeByCountry(country);
        mailSender(extracted(employees), text);
        log.debug("Service ==> sendEmailByCountry() - end: employees = {}", employees);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCity(String city, String text) {
        log.debug("Service ==> sendEmailByCity() - start: city = {}, text = {}", city, text);
        List<Employee> employees = employeeRepository.findEmployeeByAddresses(city);
        mailSender(extracted(employees), text);
        log.debug("Service ==> sendEmailByCity() - end: employees = {}", employees);
        return employees;
    }

    @Override
    public List<Employee> metricsForEmployee(String country) {

        log.debug("Service ==> metricsForEmployee() - start: country = {}", country);
        List<Employee> allEmployee = employeeRepository.findEmployeeWhoChangedCountry(country);
        //System.out.println(allEmployee);

//        List<Employee> employeesWithInactiveAddresses = allEmployee.stream()
//                .filter(employee -> employee.getAddresses().stream()
//                        .anyMatch(address -> address.getCountry().equals(country) && !address.getAddressHasActive()))
//                .collect(Collectors.toList());
        log.debug("Service ==> metricsForEmployee() - end: allEmployee = {}", allEmployee);
        return null;
    }


    @Override
    public Employee addPassport(Integer employeeId) {
        log.debug("Service ==> addPassport() - start: employeeId = {}", employeeId);
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(ResourceNotFoundException::new);
        checkIsFree();

        employee.setPassport(
                passportRepository.findAll().stream()
                        .filter(e -> (e.getIsFree())).findFirst().orElseThrow(ResourceNotFoundException::new)
        );
        employee.getPassport().setIsFree(Boolean.FALSE);
        employeeRepository.save(employee);
        log.debug("Service ==> addPassport() - end: employee = {}", employee);
        return employee;
    }

    @Override
    public Employee addWorkplace(Integer employeeId, Integer workplaceId) {
        log.debug("Service ==> addWorkplace() - start: employeeId = {}, workplaceId = {}", employeeId, workplaceId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(ResourceNotFoundException::new);
        Workplace workplace=workplaceService.getById(workplaceId);
        if(employeeRepository.checkFreeSittingsInWorkplace(workplaceId)<workplace.getAvailableSittingPlaces() &&
            workplace.getAvailableSittingPlaces()>0) {
            employee.getWorkplaces().add(workplace);
            workplace.setAvailableSittingPlaces(workplace.getAvailableSittingPlaces()-1);
            employeeRepository.save(employee);
        }
        else
            throw new ResourceHasNoDataException("No free place in this workplace");
        log.debug("Service ==> addWorkplace() - end: employee = {}", employee);
        return employee;
    }

    private void checkIsFree() {
        log.debug("Service ==> checkIsFree() - start: ");
        if (passportRepository.getQuantityFreePassports() <= 5) {
            passportService.fillPassports();
        }
        log.debug("Service ==> checkIsFree() - end: ");
    }


    private static List<String> extracted(List<Employee> employees) {
        List<String> emails = new ArrayList<>();
        //Arrays.asList();
        for (Employee emp : employees) {
            emails.add(emp.getEmail());
        }
        return emails;
    }



    public void mailSender(List<String> emails, String text) {
        log.info("Emails were successfully sent");
    }
}
