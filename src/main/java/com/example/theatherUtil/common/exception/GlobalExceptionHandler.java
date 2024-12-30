package com.example.theatherUtil.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> expectError(BaseException error){
      return ResponseEntity.badRequest().body(new ExceptionResponse(error));
    }
  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> expectError(Exception error){
    return ResponseEntity.internalServerError().body(new ExceptionResponse(error));
  }
}
