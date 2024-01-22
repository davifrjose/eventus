package com.davifrjose.eventus.modules.event.entites.organizer.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

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

@Entity(name = "organizer_story")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizerStoryEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String content;

  @ManyToOne
  @JoinColumn(name = "organizer_id", insertable = false, updatable = false)
  private OrganizerEntity organizerEntity;

  @Column(name = "organizer_id", nullable = false)
  private UUID organizer_id;
  
  @CreationTimestamp
  private LocalDateTime createdAT;
}
