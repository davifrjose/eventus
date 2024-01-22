package com.davifrjose.eventus.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException()
  {
    super("Este usuário não existe");
  }
}
