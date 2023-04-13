package com.example.demowithtests.service.workplace;

import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.domain.passport.Passport;

import java.util.List;

public interface WorkplaceService {

    Workplace create(Workplace workplace);

//    List<Workplace> getAll();

    Workplace getById(Integer id);
}
