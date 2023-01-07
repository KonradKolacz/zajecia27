package com.example.zajecia27.controller;

import com.example.zajecia27.dto.ExceptionDto;
import com.example.zajecia27.dto.ValidationErrorDto;
import com.example.zajecia27.exception.DoctorNotFoundException;
import com.example.zajecia27.exception.PatientNotFoundException;
import com.example.zajecia27.exception.VisitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDto>> handleValidationErrors(MethodArgumentNotValidException e) {
        final List<ValidationErrorDto> errors = e.getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationErrorDto(fieldError.getDefaultMessage(), fieldError.getField()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ValidationErrorDto>> handleValidationErrors(ConstraintViolationException e) {
        final List<ValidationErrorDto> errors = e.getConstraintViolations()
                .stream()
                .map(fieldError -> new ValidationErrorDto(fieldError.getMessage(), fieldError.getPropertyPath().toString()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleDoctorNotFoundException(DoctorNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ExceptionDto> handlePatientNotFoundException(PatientNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VisitNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleVisitnotFoundException(VisitNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
