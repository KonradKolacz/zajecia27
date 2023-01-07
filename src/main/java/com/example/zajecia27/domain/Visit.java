package com.example.zajecia27.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.annotation.After;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "data powinna byc z przyszlosci")
    private LocalDate date;
        @ManyToOne
//    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne
//    @JoinColumn(name = "patient_id")
    private Patient patient;
//    @Max(value = 2)
//    private long doctorId;
//    @Max(value = 2)
//    private long patientId;
}
