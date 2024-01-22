package com.davifrjose.eventus.modules.event.entites.event.useCases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.modules.event.entites.event.EventEntity;
import com.davifrjose.eventus.modules.event.entites.event.repositories.EventRepository;
import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;

@Service
public class ListAllEventsUseCase {

  @Autowired
  private EventRepository eventRepository;

  public List<EventEntity> listAllEvents()
  {
    return this.eventRepository.findAll();
  }

  public List<EventEntity> listAllEventsByFilter(String filter)
  {
    return this.eventRepository.findByNameContainingIgnoreCase(filter);
  }

  public Optional<List<EventEntity>> listAllEventsByOrganizer(Optional<OrganizerEntity> organizerEntity)
  {
    return this.eventRepository.findByOrganizerEntity(organizerEntity);
  }


}
