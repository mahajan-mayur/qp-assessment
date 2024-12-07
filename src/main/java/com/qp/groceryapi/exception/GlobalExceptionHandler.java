package com.qp.groceryapi.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler({EntityNotFoundException.class})
    ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e){
       return ResponseEntity.notFound().build();
   }

   @ExceptionHandler({BadRequestException.class})
    ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException e){
       return ResponseEntity.badRequest().body(
               ErrorResponse.builder()
                       .error(e.getMessage())
                       .status(HttpStatus.BAD_REQUEST.value())
                       .build());
   }

   @ExceptionHandler({ItemOutOfStockException.class})
   ResponseEntity<ErrorResponse> handleItemOutOfStockException(ItemOutOfStockException e){
       return ResponseEntity.badRequest().body(
               ErrorResponse.builder()
                       .error(String.format("requested item : %s is either out of stock or required quantity is not available", e.getItemId()))
                       .status(HttpStatus.BAD_REQUEST.value())
                       .build());
   }

    @ExceptionHandler({ItemNotFoundException.class})
    ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .error(String.format("requested item : %s Not Found", e.getItemId()))
                        .status(HttpStatus.NOT_FOUND.value())
                        .build());
    }
}
