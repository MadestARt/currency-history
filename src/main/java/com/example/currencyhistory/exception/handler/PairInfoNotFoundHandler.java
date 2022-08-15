package com.example.currencyhistory.exception.handler;

import com.example.currencyhistory.exception.PairInfoNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PairInfoNotFoundHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundEx(PairInfoNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
