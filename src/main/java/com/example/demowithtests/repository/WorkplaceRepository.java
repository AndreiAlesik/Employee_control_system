package com.example.demowithtests.repository;

import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.domain.passport.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Integer> {

}
