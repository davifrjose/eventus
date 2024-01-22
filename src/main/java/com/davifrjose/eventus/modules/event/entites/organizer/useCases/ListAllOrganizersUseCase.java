package com.davifrjose.eventus.modules.event.entites.organizer.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.repository.OrganizerRepository;

@Service
public class ListAllOrganizersUseCase {
   @Autowired
   private OrganizerRepository organizerRepository;

   public List<OrganizerEntity> listAllOrganizersUseCase (){
    return this.organizerRepository.findAll();
   }

   public List<OrganizerEntity> listAllOrganizersByFilter(String filter) {
      return this.organizerRepository.findByNameContainingIgnoreCase(filter);
     }
}
