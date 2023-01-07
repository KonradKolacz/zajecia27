package com.example.zajecia27.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientCommand {
//    dodac walidacja
    private String name;
    private String email;
    private String username;
    private String password;
}
