package com.example.zajecia27.config;

import com.example.zajecia27.domain.Doctor;
import com.example.zajecia27.domain.Patient;
import com.example.zajecia27.domain.Visit;
import com.example.zajecia27.repository.DoctorRepository;
import com.example.zajecia27.repository.PatientRepository;
import com.example.zajecia27.repository.VisitRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class AppConfig {

//    public AppConfig(DoctorRepository doctorRepository, PatientRepository patientRepository, VisitRepository visitRepository, PasswordEncoder passwordEncoder) {
//        doctorRepository.save(new Doctor(2L, "Konrad", "internist", "konrad123", passwordEncoder.encode( "konrad123"), "ROLE_ADMIN", true));
//        patientRepository.save(new Patient(1L, "Natalia", "Natalia", "natalia123", "", passwordEncoder.encode( "natalia123"), "ROLE_USER", true));
//        visitRepository.save(new Visit(1L, LocalDate.of(2023,01,10), 1L, 1L));
//
//    }

    @Bean
    public ModelMapper modelMapper(Set<Converter> converters){
        ModelMapper modelMapper = new ModelMapper();
        converters.forEach(modelMapper::addConverter);
        return modelMapper;
    }

}
