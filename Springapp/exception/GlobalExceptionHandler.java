package com.examly.springapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExceedsTeamBudgetException.class)
    public ResponseEntity<?> m1(ExceedsTeamBudgetException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(PlayerAlreadyAssignedException.class)
    public ResponseEntity<?> m2(PlayerAlreadyAssignedException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
