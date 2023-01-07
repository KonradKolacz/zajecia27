package com.example.zajecia27.service;

import com.example.zajecia27.domain.Doctor;
import com.example.zajecia27.exception.DoctorNotFoundException;
import com.example.zajecia27.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public Doctor findById(Long id){
        return doctorRepository.findById(id).orElseThrow(()-> new DoctorNotFoundException(id));
    }

    public Doctor add(Doctor doctor){
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctor.setRole("ROLE_ADMIN");
        return doctorRepository.saveAndFlush(doctor);
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public void deleteById(long id){
        doctorRepository.deleteById(id);
    }

    public Doctor update(long id, Doctor doctor){
        Doctor doctorToUpdate = findById(id);
        doctorToUpdate.setName(doctor.getName());
        doctorToUpdate.setSpecialization(doctor.getSpecialization());
        return doctorRepository.saveAndFlush(doctorToUpdate);
    }

}
