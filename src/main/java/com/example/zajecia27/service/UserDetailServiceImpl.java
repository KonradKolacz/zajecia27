package com.example.zajecia27.service;

import com.example.zajecia27.domain.Doctor;
import com.example.zajecia27.repository.DoctorRepository;
import com.example.zajecia27.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Doctor> byDoctorUsername = doctorRepository.findByUsername(username);
        if (byDoctorUsername.isPresent()){
            return byDoctorUsername.get();
        }
        return patientRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
