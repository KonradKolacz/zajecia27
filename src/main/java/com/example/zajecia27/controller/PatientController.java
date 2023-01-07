package com.example.zajecia27.controller;

import com.example.zajecia27.command.DoctorCommand;
import com.example.zajecia27.command.PatientCommand;
import com.example.zajecia27.command.VisitCommand;
import com.example.zajecia27.domain.Doctor;
import com.example.zajecia27.domain.Patient;
import com.example.zajecia27.domain.Visit;
import com.example.zajecia27.repository.PatientRepository;
import com.example.zajecia27.service.DoctorService;
import com.example.zajecia27.service.PatientService;
import com.example.zajecia27.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final VisitService visitService;
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @PostMapping("/register")
    public String add(@Valid PatientCommand patientCommand){
        patientService.add(modelMapper.map(patientCommand, Patient.class));
        return "redirect:/app";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("patientCommand", new PatientCommand());
        return "sign-up";
    }

    @GetMapping("/verify")
    public String verifyCode(@RequestParam String code) {
        Patient patientByCode = patientService.findByVerificationCode(code);
        patientByCode.setEnabled(true);
        patientService.save(patientByCode); // ta linijka juz chyba niepotrzbna, Hibernate automatycznie aktualizuje zmiany pobranej encji
        return "redirect:/app";
    }

    @PostMapping("/visit/add")
    public String addVisit(@Valid Visit visit, BindingResult bindingResult){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = (Patient)principal;
        visit.setPatient(patient);
//        visit.setPatientId(patient.getId());
        if (bindingResult.hasErrors()){
            return "errorBookForm";
        }

        visitService.add(visit);
        return "redirect:/user/welcome";
    }

    @GetMapping("/welcome")
//    @PreAuthorize("hasRole('ADMIN')")
    public String welcomePage(){
        return "welcomePatient";
    }


    @GetMapping("/visits/{id}")
    public String findVisits(@PathVariable("id") long id, Model model){
        model.addAttribute("visits",patientService.findById(id).getVisits());
        model.addAttribute("visit1", new Visit());
        return "patientVisits";
    }
    @GetMapping("/visits/book_form")
//    @Secured({"USER"})
    public String registrationForm(Model model){
        model.addAttribute(new Visit());
        model.addAttribute("doctors", doctorService.findAll());
        return "book-form";
    }
    @GetMapping("/visits/delete/{id}")
    public String deleteVisit(@PathVariable("id") long id){
        visitService.deleteById(id);
        return "deleteSuccessful";
    }
    @GetMapping("/delete2/{id}")
    public String deleteVisit2(@PathVariable("id") long id){
        visitService.deleteById(id);
        return "deleteSuccessful";
    }
}
