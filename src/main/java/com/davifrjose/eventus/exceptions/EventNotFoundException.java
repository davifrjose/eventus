package com.davifrjose.eventus.exceptions;

public class EventNotFoundException extends RuntimeException {
  public EventNotFoundException()
  {
    super("Este Eventoa não existe");
  }
}
