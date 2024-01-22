package com.davifrjose.eventus.modules.event.entites.ticket.useCases;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.modules.event.entites.ticket.entities.TicketEntity;
import com.davifrjose.eventus.modules.event.entites.ticket.repositories.OrderTicketRepository;
import com.davifrjose.eventus.modules.user.UserEntity;
import com.davifrjose.eventus.modules.user.repository.UserOrderRepository;

@Service
public class ListAllTickesByUserOrderUseCase {
  @Autowired
  private OrderTicketRepository orderTicketRepository;

  @Autowired
  private UserOrderRepository userOrderRepository;

    public Optional<List<TicketEntity>> listAllTicketsByUser(Optional<UserEntity> userEntity)
  {
    var userOrder = userOrderRepository.findByUserEntity(userEntity);

    return this.orderTicketRepository.findByUserOrderId(userOrder.getId());
  }
}
