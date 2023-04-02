package com.example.demowithtests.web.passport;

import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.dto.passport.RegistrationDto;
import com.example.demowithtests.service.passport.PassportService;
import com.example.demowithtests.util.mapper.EmployeeMapper;
import com.example.demowithtests.util.mapper.PassportMapper;
import com.example.demowithtests.util.mapper.RegistrationMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassportControllerBean implements PassportController {

    private final PassportService passportService;
    private final PassportMapper passportMapper;
    private final RegistrationMapper registrationMapper;



    @Override
    public PassportResponseDto savePassport(PassportRequestDto requestDto) {
        log.info("Controller ==> savePassport() - start: requestDto = {}", requestDto);
        Passport passport = passportMapper.fromRequestDto(requestDto);
        PassportResponseDto responseDto = passportMapper.toResponseDto(passportService.create(passport));
        log.info("Controller ==> saveEmployee() - end: responseDto = {}", responseDto);
        return responseDto;
    }

    @Override
    public List<PassportResponseDto> getAllPassports() {
        log.info("Controller ==> getAllPassports() - start: ");
        List<Passport> passports = passportService.getAll();
        List<PassportResponseDto> responseDtoList = new ArrayList<>();
        for (Passport passport : passports) {
            responseDtoList.add(passportMapper.toResponseDto(passport));
        }
        log.info("Controller ==> getAllPassports() - end: ");
        return responseDtoList;
    }

    @Override
    public PassportResponseDto getPassportById(Integer id) {
        log.info("Controller ==> getPassportById() - start: id = {} ", id);
        Passport passport = passportService.getById(id);
        PassportResponseDto responseDto = passportMapper.toResponseDto(passport);
        log.info("Controller ==> getPassportById() - end: responseDto = {} ", responseDto);
        return responseDto;
    }

    @Override
    public PassportResponseDto refreshPassport(Integer id, PassportRequestDto requestDto) {
        log.info("Controller ==> refreshPassport() - start: id = {} ", id);
        PassportResponseDto responseDto = passportMapper
                .toResponseDto(passportService
                        .updateById(id, passportMapper
                                .fromRequestDto(requestDto)));
        log.info("Controller ==> refreshPassport() - end: responseDto = {} ", responseDto);
        return responseDto;
    }

    @Override
    public PassportResponseDto findPassportByRegistration(UUID id) {
        log.info("Controller ==> findPassportByRegistration() - start: id = {} ", id);
        PassportResponseDto responseDto = passportMapper
                .toResponseDto(passportService
                        .findByRegistrationId(id));
        log.info("Controller ==> refreshPassport() - end: responseDto = {} ", responseDto);
        return responseDto;
    }

    @Override
    public PassportResponseDto addRegistrationToPassport(Integer id, RegistrationDto registrationDto) {
        log.info("Controller ==> addRegistrationToPassport() - start: id = {} ", id);
        PassportResponseDto responseDto = passportMapper
                .toResponseDto(passportService
                        .addRegistration(id, registrationMapper
                                .fromRegistrationDto(registrationDto)));
        log.info("Controller ==> addRegistrationToPassport() - end: responseDto = {} ", responseDto);
        return responseDto;
    }

    @Override
    public List<PassportResponseDto> deactivateRegistrations(String country) {
        log.info("Controller ==> deactivateRegistrations() - start: country = {} ", country);
        List<PassportResponseDto> responseDtoList = new ArrayList<>();
        List<Passport> passports = passportService.deactivateRegistrations(country);
        for (Passport passport: passports) {
            responseDtoList.add(passportMapper.toResponseDto(passport));
        }
        log.info("Controller ==> deactivateRegistrations() - end: ");
        return responseDtoList;
    }

}

