package com.davifrjose.eventus.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException()
  {
    super("Este usuário já existe");
  }
}
