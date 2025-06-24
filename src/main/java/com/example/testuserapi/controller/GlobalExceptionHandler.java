package com.example.testuserapi.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 404, когда не нашлась сущность (GET / DELETE по несуществующему id) */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(EntityNotFoundException ex) {
        return ex.getMessage();                // тело ответа: "User not found"
    }

    /** 404, когда deleteById бросил EmptyResult… */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmptyResult(EmptyResultDataAccessException ex) {
        return "User not found";
    }
}
