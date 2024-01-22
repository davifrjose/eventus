package com.davifrjose.eventus.modules.event.entites.ticket.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.exceptions.TicketNotFoundException;
import com.davifrjose.eventus.exceptions.UserOrderNotFoundException;
import com.davifrjose.eventus.modules.event.entites.ticket.entities.OrderTicketEntity;
import com.davifrjose.eventus.modules.event.entites.ticket.repositories.OrderTicketRepository;
import com.davifrjose.eventus.modules.event.entites.ticket.repositories.TicketRepository;
import com.davifrjose.eventus.modules.user.repository.UserOrderRepository;

@Service
public class CreateOrderTicketUseCase {
  @Autowired
  private OrderTicketRepository orderTicketRepository;

  @Autowired
  private UserOrderRepository userOrderRepository;

  @Autowired
  private TicketRepository ticketRepository;

  public OrderTicketEntity execute(UUID userOrderId, UUID ticketId)
  {
    this.userOrderRepository.findById(userOrderId)
      .orElseThrow(()-> {
        throw new UserOrderNotFoundException();
      });

    this.ticketRepository.findById(ticketId)
      .orElseThrow(()-> {
        throw new TicketNotFoundException();
      });

    var orderTicket = OrderTicketEntity.builder()
      .userOrderId(userOrderId)
      .ticketId(ticketId)
      .build();

    orderTicket = this.orderTicketRepository.save(orderTicket);

    return orderTicket;
  }
}
