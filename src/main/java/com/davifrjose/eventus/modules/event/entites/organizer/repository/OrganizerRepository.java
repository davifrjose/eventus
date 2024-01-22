package com.davifrjose.eventus.modules.event.entites.organizer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;

public interface OrganizerRepository extends JpaRepository<OrganizerEntity, UUID> {
  Optional<OrganizerEntity> findByName(String name);
  List<OrganizerEntity> findByNameContainingIgnoreCase(String filter);
}
