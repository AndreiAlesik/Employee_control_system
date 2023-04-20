package com.example.demowithtests;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.service.employee.EmployeeServiceBean;
import com.example.demowithtests.util.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@Transactional
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceBean service;

    @Mock
    private EntityManager entityManager;

    @Test
    public void whenSaveEmployee_shouldReturnEmployee() {
        Employee employee = new Employee();
        employee.setName("Mark");

        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = service.create(employee);

        assertThat(created.getName()).isSameAs(employee.getName());
        verify(employeeRepository).save(employee);
    }

    @Test
    public void whenGivenId_shouldReturnEmployee_ifFound() {
        Employee employee = new Employee();
        employee.setId(88);

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        Employee expected = service.getById(employee.getId());

        assertThat(expected).isSameAs(employee);
        verify(employeeRepository).findById(employee.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_exception_when_employee_doesnt_exist() {
        Employee employee = new Employee();
        employee.setId(89);
        employee.setName("Mark");

        given(employeeRepository.findById(anyInt())).willReturn(Optional.empty());
        service.getById(employee.getId());
    }



}
