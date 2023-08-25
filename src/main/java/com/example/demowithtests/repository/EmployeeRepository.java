package com.example.demowithtests.repository;

import com.example.demowithtests.domain.employee.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
//@Component
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    List<Employee> findEmployeeByIsDeletedNull();

    @Query(value = " select e from Employee e where e.country=:country")
    List<Employee> findEmployeeByCountry(String country);

    @Query(value = "select e from Employee e join Address a where a.city=:city")
    List<Employee> findEmployeeByAddresses(String city);

    @Query(value = "SELECT * FROM users WHERE id IN (" +
            "    SELECT employee_id FROM addresses WHERE country =:country AND address_has_active = false" +
            ");"
//            "where  addresses.country=:country"
            , nativeQuery = true)
    List<Employee> findEmployeeWhoChangedCountry(String country);

    @Query(value =
            "select count(*) from users_workplaces where workplaces_id=:workplaceID"
            , nativeQuery = true)
    Integer checkFreeSittingsInWorkplace(Integer workplaceID);

    Employee findEmployeeByEmail(String email);

    List<Employee> findEmployeeByNameContaining(String name);

    @Query(value = "select e from Employee e join e.addresses where lower(e.name) like lower(concat('%', :name, '%')) ")
    List<Employee> findEmployeeByNameJPQL(@Param("name") String name);

    @Query(value = "select e from Employee e where lower(e.name) like lower(concat('%', :name, '%'))")
    @EntityGraph(attributePaths = {"addresses"})
    List<Employee> findEmployeeByNameJPQLAndEntityGraph(@Param("name") String name);

    @Query(value = "select u.* from users u left join addresses a on u.id=a.employee_id where lower(u.name) like lower(concat('%', :name, '%'))", nativeQuery = true)
    List<Employee> findEmployeeByNameNativeSQL(@Param("name") String name);


}
