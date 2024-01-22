package com.davifrjose.eventus.modules.event.entites.organizer.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.exceptions.OrganizerFoundException;
import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.repository.OrganizerRepository;

@Service
public class CreateOrganizerUseCase {
  
   @Autowired
   private OrganizerRepository organizerRepository;

   public OrganizerEntity execute(OrganizerEntity organizerEntity){
    this.organizerRepository
      .findByName(organizerEntity.getName())
      .ifPresent((organizer) -> {
        throw new OrganizerFoundException();
      });

      return this.organizerRepository.save(organizerEntity);
   }
}
