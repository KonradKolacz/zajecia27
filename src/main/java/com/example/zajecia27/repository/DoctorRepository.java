package com.example.zajecia27.repository;

import com.example.zajecia27.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    boolean existsByUsername(String username);

    boolean existsByPassword(String password);


    Optional<Doctor> findByUsername(String username);
}
