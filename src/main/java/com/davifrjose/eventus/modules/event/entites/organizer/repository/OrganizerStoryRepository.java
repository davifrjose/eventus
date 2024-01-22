package com.davifrjose.eventus.modules.event.entites.organizer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerStoryEntity;

public interface OrganizerStoryRepository extends JpaRepository<OrganizerStoryEntity, UUID> {
  Optional<List<OrganizerStoryEntity>> findByOrganizerEntity(Optional<OrganizerEntity> organizerEntity );
}
