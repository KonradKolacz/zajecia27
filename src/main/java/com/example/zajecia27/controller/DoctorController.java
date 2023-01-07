package com.example.zajecia27.controller;

import com.example.zajecia27.command.DoctorCommand;
import com.example.zajecia27.domain.Doctor;
import com.example.zajecia27.service.DoctorService;
import com.example.zajecia27.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class DoctorController {

    private final DoctorService doctorService;
    private final ModelMapper modelMapper;
    private final VisitService visitService;

    @PostMapping
    public String add(@Valid DoctorCommand doctorCommand){
        doctorService.add(modelMapper.map(doctorCommand, Doctor.class));
        return "redirect:/admin/welcome";
    }

    @GetMapping("/welcome")
    @PreAuthorize("hasRole('ADMIN')")
    public String welcomePage(){
        return "welcomeDoctor";
    }

    @GetMapping("/doctors")
//    @Secured({"ADMIN"})
    public String findAll(Model model){
        model.addAttribute("doctors", doctorService.findAll());
        return "doctors";
    }

    @GetMapping("/registration_form")
//    @Secured({"ADMIN"})
    public String registrationForm(Model model){
        model.addAttribute(new DoctorCommand());
        return "registration_form";
    }

    @GetMapping("/visits/{id}")
    public String findVisits(@PathVariable("id") long id, Model model){
        model.addAttribute("visits",doctorService.findById(id).getVisits());
        return "doctorVisits";
    }
//    @GetMapping("/doctors")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String mainAdminDoctors(ModelMap model, @PageableDefault Pageable pageable){ //rownie dobrze moglbym tutaj uzyc Model? czy musi byc modelMap ze wzgledu ze jest przekazywany zbior wartosci?
//        model.addAttribute("doctors",doctorRepository.findAll(pageable));
//        return "AdminDoctorsView";
//    }

    @GetMapping("/women")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String women(){
        return "women";
    }
}
