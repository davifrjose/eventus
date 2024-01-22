package com.davifrjose.eventus.modules.event.entites.organizer.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.exceptions.OrganizerNotFoundException;
import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerStoryEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.repository.OrganizerRepository;
import com.davifrjose.eventus.modules.event.entites.organizer.repository.OrganizerStoryRepository;

@Service
public class CreateOrganizerStoryUseCase {

  @Autowired
  private OrganizerStoryRepository organizerStoryRepository;

  @Autowired
  private OrganizerRepository organizerRepository ;

  public OrganizerStoryEntity execute ( OrganizerStoryEntity organizerStoryEntity) {
    this.organizerRepository.findById(organizerStoryEntity.getOrganizer_id())
      .orElseThrow(()-> {
        throw new OrganizerNotFoundException();
      });

      return this.organizerStoryRepository.save(organizerStoryEntity);
  }


}
