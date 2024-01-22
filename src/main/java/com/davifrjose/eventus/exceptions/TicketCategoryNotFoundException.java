package com.davifrjose.eventus.exceptions;

public class TicketCategoryNotFoundException extends RuntimeException {
  public TicketCategoryNotFoundException()
  {
    super("Esta categoria de ticket  não existe");
  }
}
