package com.example.zajecia27.repository;

import com.example.zajecia27.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    boolean existsByUsername(String username);

    boolean existsByPassword(String password);

    Optional<Patient> findByUsername(String username);
    Optional<Patient> findByVerificationCode(String verificationCode);
}
