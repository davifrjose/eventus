package com.davifrjose.eventus.modules.event.entites.ticket.useCases;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.exceptions.EventNotFoundException;
import com.davifrjose.eventus.exceptions.TicketCategoryNotFoundException;
import com.davifrjose.eventus.modules.event.entites.event.repositories.EventRepository;
import com.davifrjose.eventus.modules.event.entites.ticket.dto.CreateTicketRequestDTO;
import com.davifrjose.eventus.modules.event.entites.ticket.entities.TicketEntity;
import com.davifrjose.eventus.modules.event.entites.ticket.repositories.TicketCategoryRepository;
import com.davifrjose.eventus.modules.event.entites.ticket.repositories.TicketRepository;




@Service
public class CreateTicketUseCase {
  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private TicketCategoryRepository ticketCategoryRepository;

  public void execute(CreateTicketRequestDTO createTicketRequestDTO)
  {
    this.eventRepository
      .findById(createTicketRequestDTO.getEvent_id())
      .orElseThrow(()-> {
        throw new EventNotFoundException();
      });

    this.ticketCategoryRepository
      .findById(createTicketRequestDTO.getTicket_category_id())
      .orElseThrow(()-> {
        throw new TicketCategoryNotFoundException();
      });
      
      var ticket = TicketEntity.builder()
      .eventId(createTicketRequestDTO.getEvent_id())
      .ticketCategoryId(createTicketRequestDTO.getTicket_category_id())
      .build();

      ticket = this.ticketRepository.save(ticket);
      
      

 
  }
}
