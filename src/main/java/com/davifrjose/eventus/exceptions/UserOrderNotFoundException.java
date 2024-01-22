package com.davifrjose.eventus.exceptions;

public class UserOrderNotFoundException extends RuntimeException {
  public UserOrderNotFoundException()
  {
    super("Esta ordem de usuário não existe");
  }
}
