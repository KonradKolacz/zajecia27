package com.example.zajecia27.service;

import com.example.zajecia27.domain.Visit;
import com.example.zajecia27.exception.VisitNotFoundException;
import com.example.zajecia27.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public Visit findById(Long id){
        return visitRepository.findById(id).orElseThrow(()->new VisitNotFoundException(id));
    }

    public List<Visit> findAll(){
        return visitRepository.findAll();
    }

    public Visit add(Visit visit){
        return visitRepository.save(visit);
    }

    public void deleteById(long id){
        visitRepository.deleteById(id);
    }

    public List<Visit> findAllVisitsForPatientById(long patientId){
        return visitRepository.findByPatientId(patientId);
    }

    public List<Visit> findAllVisitsForDoctorById(long doctorId){
        return visitRepository.findByDoctorId(doctorId);
    }

    public boolean hasVisit(long doctorId, LocalDate visitDate){
        return findAllVisitsForDoctorById(doctorId).stream().anyMatch(visit-> visit.getDate()==visitDate);
    }
}
