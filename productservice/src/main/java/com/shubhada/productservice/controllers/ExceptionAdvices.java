package com.shubhada.productservice.controllers;

import com.shubhada.productservice.dtos.ErrorResponseDTO;
import com.shubhada.productservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(Exception exception){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
        errorResponseDTO.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
       // return new ResponseEntity<>("Phat Gaya",HttpStatus.OK); return ResponseEntity<String>
    }
}
