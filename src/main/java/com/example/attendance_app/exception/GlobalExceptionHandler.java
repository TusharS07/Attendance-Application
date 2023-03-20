package com.example.attendance_app.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AtttendenceAppException.class)
    public String handleBookStoreException(AtttendenceAppException atttendenceAppException) {
        return atttendenceAppException.getMessage();
    }

}
