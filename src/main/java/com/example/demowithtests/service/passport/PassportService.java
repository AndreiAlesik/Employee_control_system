package com.example.demowithtests.service.passport;


import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.repository.PassportRepository;

import java.util.List;

public interface PassportService {

    Passport create(Passport passport);

    List<Passport> getAll();

    Passport getById(Integer id);

    Passport updateById(Integer id, Passport plane);


}
