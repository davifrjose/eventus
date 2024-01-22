package com.davifrjose.eventus.modules.event.entites.ticket.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.davifrjose.eventus.modules.event.entites.event.EventEntity;
import com.davifrjose.eventus.modules.event.entites.ticket.utils.TicketAreaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ticket_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCategoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String description;

  private BigDecimal price;

  private TicketAreaEnum area;

  @ManyToOne
  @JoinColumn(name = "event_id", insertable = false, updatable = false)
  private EventEntity eventEntity;

  @Column(name = "event_id", nullable = false)
  private UUID event_id;

  @CreationTimestamp
  private LocalDateTime createdAT;

   


}
