package com.example.theatherUtil.common.exception;

public class BaseException extends RuntimeException{
  public BaseException(String message) {
    super(message);
  }
  public BaseException(Exception e) {
    super(e.getMessage());
  }
}
