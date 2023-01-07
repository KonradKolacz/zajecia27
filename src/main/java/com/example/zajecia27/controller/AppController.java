package com.example.zajecia27.controller;

import com.example.zajecia27.domain.Doctor;
import com.example.zajecia27.domain.Patient;
import com.example.zajecia27.domain.Visit;
import com.example.zajecia27.service.DoctorService;
import com.example.zajecia27.service.PatientService;
import com.example.zajecia27.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController {

    @GetMapping
    @Secured({"ADMIN", "USER"})
    public String showAppDefaultView() {

        return "doctors-patients-app";
    }

}
