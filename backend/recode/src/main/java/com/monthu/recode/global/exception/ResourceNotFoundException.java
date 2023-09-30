package com.monthu.recode.global.exception;

public class ResourceNotFoundException extends BusinessException {

  public ResourceNotFoundException(String message) {
    super(404, message);
  }
  
}
