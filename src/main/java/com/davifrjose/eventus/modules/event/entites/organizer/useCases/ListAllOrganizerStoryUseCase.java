package com.davifrjose.eventus.modules.event.entites.organizer.useCases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerStoryEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.repository.OrganizerStoryRepository;

@Service
public class ListAllOrganizerStoryUseCase {

  @Autowired
  private OrganizerStoryRepository organizerStoryRepository;

  public Optional<List<OrganizerStoryEntity>> listAllStoriesByOrgananizer(Optional<OrganizerEntity> organizerEntity)
  {
    return this.organizerStoryRepository.findByOrganizerEntity(organizerEntity);
  }

}
