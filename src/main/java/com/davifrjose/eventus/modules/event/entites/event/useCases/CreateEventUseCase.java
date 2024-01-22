package com.davifrjose.eventus.modules.event.entites.event.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.davifrjose.eventus.exceptions.OrganizerNotFoundException;
import com.davifrjose.eventus.modules.event.entites.event.EventEntity;
import com.davifrjose.eventus.modules.event.entites.event.repositories.EventRepository;
import com.davifrjose.eventus.modules.event.entites.organizer.repository.OrganizerRepository;

@Service
public class CreateEventUseCase {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private OrganizerRepository organizerRepository;

  public EventEntity execute(EventEntity eventEntity){
    this.organizerRepository
      .findById(eventEntity.getOrganizer_id())
      .orElseThrow(()-> {
        throw new OrganizerNotFoundException();
      });

      return this.eventRepository.save(eventEntity);
}
}