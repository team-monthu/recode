package com.monthu.recode.global.exception;

public class AlreadyExistedMemberException extends BusinessException {

  public AlreadyExistedMemberException(String message) {
    super(409, message);
  }
}
