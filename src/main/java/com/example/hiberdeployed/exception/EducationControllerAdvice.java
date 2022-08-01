package com.example.hiberdeployed.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class EducationControllerAdvice {
    @ExceptionHandler(StudentException.class)
    public ResponseEntity<?> handleStudentException(StudentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(new HttpHeaders()).body(ex.getMessage());
    }

//    @ExceptionHandler(NoResultException.class)
//    public ResponseEntity<?> handleNoResultException(NoResultException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(new HttpHeaders()).body(ex.getMessage() + " Not Found");
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(new HttpHeaders()).body("!");
    }
    @ExceptionHandler(DayException.class)
    public ResponseEntity<?> handleDayException(DayException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(new HttpHeaders()).body(ex.getMessage());
    }
}
