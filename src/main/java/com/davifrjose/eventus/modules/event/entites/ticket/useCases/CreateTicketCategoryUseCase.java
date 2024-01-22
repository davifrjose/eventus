package com.davifrjose.eventus.modules.event.entites.ticket.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.exceptions.EventNotFoundException;
import com.davifrjose.eventus.modules.event.entites.event.repositories.EventRepository;
import com.davifrjose.eventus.modules.event.entites.ticket.entities.TicketCategoryEntity;
import com.davifrjose.eventus.modules.event.entites.ticket.repositories.TicketCategoryRepository;
@Service
public class CreateTicketCategoryUseCase {

  @Autowired
  private TicketCategoryRepository ticketCategoryRepository;

   @Autowired
  private EventRepository eventRepository;
  
  public TicketCategoryEntity execute (TicketCategoryEntity ticketCategoryEntity)
  {
     this.eventRepository
      .findById(ticketCategoryEntity.getEvent_id())
      .orElseThrow(()-> {
        throw new EventNotFoundException();
      });

      return this.ticketCategoryRepository.save(ticketCategoryEntity);
  }
}
