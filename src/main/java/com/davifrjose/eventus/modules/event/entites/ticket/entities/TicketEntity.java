package com.davifrjose.eventus.modules.event.entites.ticket.entities;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.davifrjose.eventus.modules.event.entites.event.EventEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "event_id", insertable = false, updatable = false)
  private EventEntity eventEntity;

  @Column(name = "event_id", nullable = false)
  private UUID eventId;

  @ManyToOne
  @JoinColumn(name = "ticket_category_id", insertable = false, updatable = false)
  private TicketCategoryEntity tickletCategoryEntity;

  @Column(name = "ticket_category_id", nullable = false)
  private UUID ticketCategoryId;

  private String qrCode;

  @CreationTimestamp
  private LocalDateTime createdAT;

  @PrePersist
    public void prePersist() {
        // Set qrCode based on ticket_id (id field)
        this.qrCode = "ticket_" + this.id + ".png";
    }
}
