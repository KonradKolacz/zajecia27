package com.example.zajecia27.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorCommand {
//    Dodac walidacja
    private String name;
    private String specialization;
    private String username;
    private String password;
}
