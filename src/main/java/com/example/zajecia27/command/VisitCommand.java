package com.example.zajecia27.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitCommand {
//   dodac walidacja
    private LocalDate date;
    private long doctorId;
    private long patientId;
}
