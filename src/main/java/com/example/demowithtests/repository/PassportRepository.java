package com.example.demowithtests.repository;

import com.example.demowithtests.domain.passport.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer> {

    @Query(value = "select * from passports join registrations on registrations.passport_id=passports.id where registrations.registration_id=:id", nativeQuery = true)
    Passport findPassportByRegistrationId(UUID id);

    @Query(value = "select * from passports join registrations on registrations.passport_id=passports.id where registrations.country=:country", nativeQuery = true)
    List<Passport> findPassportsWithCountry(String country);

    @Query(value = "select count(*) from passports where is_free=true", nativeQuery = true)
    Integer getQuantityFreePassports();

}
