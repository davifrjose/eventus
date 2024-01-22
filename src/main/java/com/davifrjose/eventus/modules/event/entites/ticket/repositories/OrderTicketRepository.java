package com.davifrjose.eventus.modules.event.entites.ticket.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davifrjose.eventus.modules.event.entites.ticket.entities.OrderTicketEntity;
import com.davifrjose.eventus.modules.event.entites.ticket.entities.TicketEntity;

public interface OrderTicketRepository extends JpaRepository<OrderTicketEntity, UUID> {
  Optional<List<TicketEntity>> findByUserOrderId(UUID userOrderId);
}
