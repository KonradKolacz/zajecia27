package com.example.zajecia27.service;

import com.example.zajecia27.domain.Patient;
import com.example.zajecia27.exception.PatientNotFoundException;
import com.example.zajecia27.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;


    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }
    public Patient findByVerificationCode(String verificationCode) {
        return patientRepository.findByVerificationCode(verificationCode).orElseThrow();
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient update(long id, Patient patient) {
        Patient patientToUpdate = findById(id);
        patientToUpdate.setName(patient.getName());
        return patientRepository.saveAndFlush(patientToUpdate);
    }

    public Patient add(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole("ROLE_USER");
        patient.setEnabled(false);
        patient.setVerificationCode(UUID.randomUUID().toString());
        sendVerificationCode(patient);
        return patientRepository.saveAndFlush(patient);
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }



    public void sendVerificationCode(Patient patient) {
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company Doctors-Patients-App.";

        content = content.replace("[[name]]", patient.getName());

        String verifyURL = "http://localhost:8080/user/verify?code=" + patient.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        try {
            mailService.sendMail(patient.getEmail(), subject, content, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
