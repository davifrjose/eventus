package com.davifrjose.eventus.exceptions;

public class OrganizerNotFoundException extends RuntimeException {
  public OrganizerNotFoundException()
  {
    super("Este organizador não existe");
  }
}
