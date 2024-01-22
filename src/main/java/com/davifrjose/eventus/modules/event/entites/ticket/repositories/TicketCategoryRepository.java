package com.davifrjose.eventus.modules.event.entites.ticket.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davifrjose.eventus.modules.event.entites.ticket.entities.TicketCategoryEntity;

public interface TicketCategoryRepository extends JpaRepository<TicketCategoryEntity, UUID> {
  
}
