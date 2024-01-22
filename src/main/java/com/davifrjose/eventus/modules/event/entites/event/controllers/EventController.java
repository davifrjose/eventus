package com.davifrjose.eventus.modules.event.entites.event.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davifrjose.eventus.exceptions.OrganizerNotFoundException;
import com.davifrjose.eventus.modules.event.entites.event.EventEntity;
import com.davifrjose.eventus.modules.event.entites.event.useCases.CreateEventUseCase;
import com.davifrjose.eventus.modules.event.entites.event.useCases.ListAllEventsUseCase;
import com.davifrjose.eventus.modules.event.entites.organizer.repository.OrganizerRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/event")
@Tag(name = "Event", description = "Login as ADMIN")
public class EventController {
  
  @Autowired
  private CreateEventUseCase createEventUseCase;

  @Autowired
  private ListAllEventsUseCase listAllEventsUseCase;

  @Autowired
  private OrganizerRepository organizerRepository;

  @PostMapping("/")
  @Operation(summary = "Event registering", description = "This function is responsible for event an organizer")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> create(@Valid @RequestBody EventEntity eventEntity) {
      try {
        var result = this.createEventUseCase.execute(eventEntity);
        return ResponseEntity.ok().body(result);
      } catch (Exception e) {
        // TODO: handle exception
        return ResponseEntity.badRequest().body(e);
      }
  }
  
   @GetMapping("/")
   @Operation(summary = "List Events ", description = "This function is responsible for listening events")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> findEvent() {
    try {
      var result = this.listAllEventsUseCase.listAllEvents();
       return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // TODO: handle exception
       return ResponseEntity.badRequest().body(e);
    }
  }

   @GetMapping("/filter/")
   @Operation(summary = "List Events by filter ", description = "This function is responsible for listening events by filter")
   @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> findOrganizerByFilter(@RequestParam String filter) {
    try {
      var result = this.listAllEventsUseCase.listAllEventsByFilter(filter);
       return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // TODO: handle exception
       return ResponseEntity.badRequest().body(e);
    }
  }

   @GetMapping("/{organizerName}/")
   @Operation(summary = "List Events by organizer ", description = "This function is responsible for listening events by organizer")
   @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> findEventsByOrganizer(@PathVariable String organizerName) {

    try {
      var organizer = this.organizerRepository.findByName(organizerName);
      if(organizer == null) throw new OrganizerNotFoundException();
      var result = this.listAllEventsUseCase.listAllEventsByOrganizer(organizer);
       return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // TODO: handle exception
       return ResponseEntity.badRequest().body(e);
    }
  }
}
