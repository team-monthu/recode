package com.monthu.recode.global.exception;

public class AuthenticationFailException extends BusinessException {

  public AuthenticationFailException(String message) {
    super(401, message);
  }
}
