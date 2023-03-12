package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;

import java.util.List;

public interface Service {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    List<Employee> processor();

    List<Employee> sendEmailByCountry(String country, String text);

    List<Employee> sendEmailByCity(String city, String text);

    List<Employee> sendEmailByCitySQL(String city, String text);

    void fillData(Integer quantities, Employee employee);

    void updateDataByID(Integer startID, Integer endID, String country);


    String patchById(Integer id, Employee employee);

    void rangeUpdate(Integer startID, Integer endID, Employee employee);

    void getGroupEmployeeAndUpdate(Integer startID, Integer endID, Employee newEmployee);
}
