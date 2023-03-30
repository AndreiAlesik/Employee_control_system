package com.example.demowithtests.service.passport;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.AccessException;
import com.example.demowithtests.util.ResourceNotFoundException;
import com.example.demowithtests.util.WrongArgumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class PassportServiceBean implements PassportService{
    private final PassportRepository passportRepository;


    @Override
    public Passport create(Passport passport) {
        return passportRepository.save(passport);
    }

    @Override
    public List<Passport> getAll() {
        return passportRepository.findAll();
    }

    @Override
    public Passport getById(Integer id) {
        return passportRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Override
    public Passport updateById(Integer id, Passport plane) {
        try {
            return passportRepository.findById(id)
                    .map(entity -> {
                        entity.setFirstName(plane.getFirstName());
                        entity.setSecondName(plane.getSecondName());
                        return passportRepository.save(entity);
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
        } catch (IllegalArgumentException e) {
            throw new WrongArgumentException();
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }
}
