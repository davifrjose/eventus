package com.davifrjose.eventus.modules.event.entites.event.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davifrjose.eventus.modules.event.entites.event.EventEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;


public interface EventRepository extends JpaRepository<EventEntity, UUID> {
  Optional<EventEntity> findByDescription(String description);
  List<EventEntity> findByNameContainingIgnoreCase(String filter);
  Optional<List<EventEntity>> findByOrganizerEntity(Optional<OrganizerEntity> organizerEntity);
}
