package com.example.theatherUtil.common.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {
  private String message;
  public ExceptionResponse(Exception error) {
    message=error.getMessage();
  }
}
