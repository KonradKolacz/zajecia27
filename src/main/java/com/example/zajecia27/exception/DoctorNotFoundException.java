package com.example.zajecia27.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends RuntimeException{
    private long id;

    public String getMessage(){
        return "Doctor with id: " + id + " not found";
    }
}
