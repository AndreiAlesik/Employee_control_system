package com.example.demowithtests.service.passport;


import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.domain.passport.Registration;

import java.util.List;
import java.util.UUID;

public interface PassportService {

    Passport create(Passport passport);

    List<Passport> getAll();

    Passport getById(Integer id);

    Passport updateById(Integer id, Passport passport);

    Passport findByRegistrationId(UUID id);

    Passport addRegistration(Integer id, Registration registration);

    List<Passport> deactivateRegistrations(String country);

    void fillPassports();

}