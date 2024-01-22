package com.davifrjose.eventus.modules.event.entites.event;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;

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

@Entity(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  private String name;
  private String description;
  private LocalDateTime dateTime;
  private String address;
  private String imageUrl;
  
  @ManyToOne
  @JoinColumn(name = "organizer_id", insertable = false, updatable = false)
  private OrganizerEntity organizerEntity;

  @Column(name = "organizer_id", nullable = false)
  private UUID organizer_id;


  @CreationTimestamp
  private LocalDateTime createdAT;
}
