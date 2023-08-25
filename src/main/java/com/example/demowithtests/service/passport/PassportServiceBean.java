package com.example.demowithtests.service.passport;

import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.domain.passport.Registration;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class PassportServiceBean implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public Passport create(Passport passport) {
        log.debug("Service ==> create() - start: passport = {}", passport);
        passport.setIsFree(Boolean.TRUE);
        Passport savedPassport = passportRepository.save(passport);
        log.debug("Service ==> create() - end: passport = {}", savedPassport);
        return savedPassport;
    }

    @Override
    public List<Passport> getAll() {
        log.debug("Service ==> getAll() - start: ");
        List<Passport> passports = passportRepository.findAll();
        log.debug("Service ==> getAll() - end: passports = {}", passports);
        return passports;
    }

    @Override
    public Passport getById(Integer id) {
        log.debug("Service ==> getById() - start: id = {}", id);
        Passport passport = passportRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        log.debug("Service ==> getById() - start: passport = {}", passport);
        return passport;
    }

    @Override
    public Passport updateById(Integer id, Passport passport) {
        log.debug("Service ==> updateById() - start: id = {}, passport = {}", id, passport);
        Passport entity = passportRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        entity.setFirstName(passport.getFirstName());
        entity.setSecondName(passport.getSecondName());
        Passport updatePassport = passportRepository.save(entity);
        log.debug("Service ==> updateById() - end: passport = {}", updatePassport);
        return updatePassport;
    }

    @Override
    public Passport findByRegistrationId(UUID id) {
        log.debug("Service ==> findByRegistrationId() - start: id = {}", id);
        Passport passport = passportRepository.findPassportByRegistrationId(id);
        log.debug("Service ==> findByRegistrationId() - end: passport = {}", passport);
        return passport;
    }

    @Override
    public Passport addRegistration(Integer id, Registration registration) {
        log.debug("Service ==> addRegistration() - start: id = {}, registration = {}", id, registration);
        Passport passport = passportRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Set<Registration> registrationSet = passport.getRegistrations();
        registrationSet.add(registration);
        passport.setRegistrations(registrationSet);
        log.debug("Service ==> addRegistration() - end: passport = {}", passport);
        return passportRepository.save(passport);
    }

    @Override
    public List<Passport> deactivateRegistrations(String country) {
        log.debug("Service ==> deactivateRegistrations() - start: country = {}", country);
        List<Passport> passports = passportRepository.findPassportsWithCountry(country);
        for (Passport passport : passports) {
            passport.getRegistrations()
                    .forEach(registration -> registration.setAddressHasActive(Boolean.FALSE));
        }
        log.debug("Service ==> deactivateRegistrations() - start: passports = {}", passports);
        return passportRepository.saveAll(passports);
    }

    @Override
    public void fillPassports() {
        log.debug("Service ==> fillPassports() = start: ");
        for (int i = 0; i<=5; i++)
            create(new Passport());
        log.debug("Service ==> fillPassports() = end: ");
    }


}
