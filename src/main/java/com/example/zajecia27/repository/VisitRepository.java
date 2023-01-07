package com.example.zajecia27.repository;

import com.example.zajecia27.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    public List<Visit> findByPatientId(long patientId);
    public List<Visit> findByDoctorId(long doctorId);
}
