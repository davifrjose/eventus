package com.davifrjose.eventus.exceptions;

public class TicketNotFoundException extends RuntimeException {
  public TicketNotFoundException()
  {
    super("Esta  ticket  não existe");
  }
}
