package com.davifrjose.eventus.modules.event.entites.organizer.controllers;



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
import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.entities.OrganizerStoryEntity;
import com.davifrjose.eventus.modules.event.entites.organizer.repository.OrganizerRepository;
import com.davifrjose.eventus.modules.event.entites.organizer.useCases.CreateOrganizerStoryUseCase;
import com.davifrjose.eventus.modules.event.entites.organizer.useCases.CreateOrganizerUseCase;
import com.davifrjose.eventus.modules.event.entites.organizer.useCases.ListAllOrganizerStoryUseCase;
import com.davifrjose.eventus.modules.event.entites.organizer.useCases.ListAllOrganizersUseCase;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/organizer")
@Tag(name = "Organizer", description = "Login as ADMIN")
public class OrganizerController {

  @Autowired
  private CreateOrganizerUseCase createOrganizerUseCase;

  @Autowired
  private ListAllOrganizersUseCase findAllOrganizersUseCase;

  @Autowired
  private CreateOrganizerStoryUseCase createOrganizerStoryUseCase;
  
  @Autowired
  private OrganizerRepository organizerRepository;

  @Autowired
  private ListAllOrganizerStoryUseCase listAllOrganizerStoryUseCase;


  @PostMapping("/")
  @Operation(summary = "Organizer registering", description = "This function is responsible for registering an organizer")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(
        array = @ArraySchema(schema = @Schema(implementation = OrganizerEntity.class))
      )
    }),
    @ApiResponse(responseCode = "400", description = "Organizer already exists")
  } 
  )
  public ResponseEntity<Object> create(@Valid @RequestBody OrganizerEntity organizerEntity) {
      try {
        var result = this.createOrganizerUseCase.execute(organizerEntity);
        return ResponseEntity.ok().body(result);
      } catch (Exception e) {
        // TODO: handle exception
        return ResponseEntity.badRequest().body(e);
      }
   
  }

  @GetMapping("/")
  @Operation(summary = "List Organizers ", description = "This function is responsible for listening organizers")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> findOrganizer() {
    try {
      var result = this.findAllOrganizersUseCase.listAllOrganizersUseCase();
       return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // TODO: handle exception
       return ResponseEntity.badRequest().body(e);
    }
  }

  @GetMapping("/filter/")
  @Operation(summary = "List Organizers by filter ", description = "This function is responsible for listening organizers by filter")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> findOrganizerByFilter(@RequestParam String filter) {
    try {
      var result = this.findAllOrganizersUseCase.listAllOrganizersByFilter(filter);
       return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // TODO: handle exception
       return ResponseEntity.badRequest().body(e);
    }
  }

  @PostMapping("/story/")
  @Operation(summary = "Organizer story registering", description = "This function is responsible for registering an organizer story")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(
        array = @ArraySchema(schema = @Schema(implementation = OrganizerStoryEntity.class))
      )
    }),
    @ApiResponse(responseCode = "400", description = "Organizer story already exists")
  } 
  )
  public ResponseEntity<Object> createStory(@Valid @RequestBody OrganizerStoryEntity organizerStoryEntity) {
 try {
        var result = this.createOrganizerStoryUseCase.execute(organizerStoryEntity);
        return ResponseEntity.ok().body(result);
      } catch (Exception e) {
        // TODO: handle exception
        return ResponseEntity.badRequest().body(e);
      }
    }
  
  @GetMapping("/story/{organizerName}")
  @Operation(summary = "List Stories by organizer ", description = "This function is responsible for listening stories by organizer")
   @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> findStoriesByOrganizer(@PathVariable String organizerName) {

    try {
      var organizer = this.organizerRepository.findByName(organizerName);
      if(organizer == null) throw new OrganizerNotFoundException();
      var result = this.listAllOrganizerStoryUseCase.listAllStoriesByOrgananizer(organizer);
       return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // TODO: handle exception
       return ResponseEntity.badRequest().body(e);
    }
  }


}
