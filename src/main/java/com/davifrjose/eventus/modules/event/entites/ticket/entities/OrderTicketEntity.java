package com.davifrjose.eventus.modules.event.entites.ticket.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.davifrjose.eventus.modules.user.UserOrderEntity;

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

@Data
@Entity(name = "order_ticket")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTicketEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "user_order_id", insertable = false, updatable = false)
  private UserOrderEntity userOrderEntity;

  @Column(name = "user_order_id", nullable = false)
  private UUID userOrderId;


  @ManyToOne
  @JoinColumn(name = "ticket_id", insertable = false, updatable = false)
  private TicketEntity ticketEntity;

  @Column(name = "ticket_id", nullable = false)
  private UUID ticketId;

   @CreationTimestamp
  private LocalDateTime createdAT;

}
