package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.AccessException;
import com.example.demowithtests.util.ResourceNotFoundException;
import com.example.demowithtests.util.ResourceWasDeletedException;
import com.example.demowithtests.util.WrongArgumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        try {
            return repository.findAll();
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException();
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }

    @Override
    public Employee getById(Integer id) {
        if (id == null) {
            throw new WrongArgumentException();
        }
        var employee = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceNotFoundException::new);
        if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }
        return employee;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        try {
            return repository.findById(id)
                    .map(entity -> {
                        entity.setName(employee.getName());
                        entity.setEmail(employee.getEmail());
                        entity.setCountry(employee.getCountry());
                        return repository.save(entity);
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
        try {
            var employee = repository.findById(id)
                    // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                    .orElseThrow(ResourceWasDeletedException::new);
            employee.setIsDeleted(true);
            repository.save(employee);
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }

    @Override
    public void removeAll() {
        try {
            repository.deleteAll();
        } catch (DataAccessException e) {
            throw new AccessException();
        }


    }

    @Override
    public List<Employee> processor() {
        log.info("replace null  - start");
        List<Employee> replaceNull = repository.findEmployeeByIsDeletedNull();
        log.info("replace null after replace: " + replaceNull);
        for (Employee emp : replaceNull) {
            emp.setIsDeleted(Boolean.FALSE);
        }
        log.info("replaceNull = {} ", replaceNull);
        log.info("replace null  - end:");
        return repository.saveAll(replaceNull);
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        List<Employee> employees = repository.findEmployeeByCountry(country);
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCity(String city, String text) {
        List<Employee> employees = repository.findEmployeeByAddresses(city);
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCitySQL(String city, String text) {
        List<Employee> employees = repository.findEmployeeByAddressesSQL(city);
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public void fillData(Integer quantities, Employee employee) {
        for (int i = 0; i <= quantities; i++) {
            Employee newEmployee = new Employee(employee.getName(), employee.getCountry(), employee.getEmail(), employee.getIsDeleted());
            repository.save(newEmployee);
        }

    }

    @Override
    public void updateDataByID(Integer startID, Integer endID, String country) {
        List<Employee> oldList = repository.findEmployeeById(startID, endID);
        for (Employee tmp : oldList) {
            tmp.setCountry(country);
        }
        repository.saveAll(oldList);

    }


    @Override
    public void rangeUpdate(Integer startID, Integer endID, Employee employee) {
        for (int i = startID; i <= endID; i++)
            patchById(i, employee);
    }

    // @Transactional
    @Override
    public void getGroupEmployeeAndUpdate(Integer startID, Integer endID, Employee newEmployee) {
        List<Employee> employeeList = repository.findEmployeeById(startID, endID);
        List<Employee> updatedEmployees = employeeList.stream().map(
                entity -> {
                    if (entity.equals(newEmployee)) {
                        return null;
                    }
                    if (newEmployee.getName() != null && !newEmployee.getName().equals(entity.getName())) {
                        entity.setName(newEmployee.getName());
                    }
                    if (newEmployee.getEmail() != null && !newEmployee.getEmail().equals(entity.getEmail())) {
                        entity.setEmail(newEmployee.getEmail());
                    }
                    if (newEmployee.getCountry() != null && !newEmployee.getCountry().equals(entity.getCountry())) {
                        entity.setCountry(newEmployee.getCountry());
                    }
                    return entity;
                }
        ).filter(Objects::nonNull).collect(Collectors.toList());

        repository.saveAll(updatedEmployees);

    }


    @Override
    public String patchById(Integer id, Employee employee) {
        repository.findEmployeeByIdWithComparing(id).stream().map(
                entity -> {
                    if (entity.equals(employee))
                        return entity;
                    if (employee.getName() != entity.getName()) {
                        entity.setName(employee.getName());
                    }
                    if (employee.getEmail() != entity.getEmail())
                        entity.setEmail(employee.getEmail());
                    if (employee.getCountry() != entity.getCountry())
                        entity.setCountry(employee.getCountry());
                    return repository.save(entity);
                }
        );
        //System.out.println(oldEmployee);
        return "Data has successfully updated";
//        }
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


    private String countryGenerate(String countries) {
        Random random = new Random();
        List<String> countryList = List.of(countries.split(", "));
//        country.add("Poland");
//        country.add("Ukraine");
//        country.add("Germany");
//        country.add("Belarus");
//        country.add("Russia");
//        country.add("Denmark");
        System.out.println(countryList);
        return countryList.get(random.ints(0, countryList.size() - 1).findFirst().getAsInt());
//        return country.get(random.nextInt(0, country.size() - 1));
    }
}
