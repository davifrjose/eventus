package com.davifrjose.eventus.exceptions;

public class OrganizerFoundException extends RuntimeException {
  public OrganizerFoundException()
  {
    super("Este organizador já existe");
  }
}
