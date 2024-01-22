package com.davifrjose.eventus.modules.event.entites.ticket.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davifrjose.eventus.exceptions.UserNotFoundException;
import com.davifrjose.eventus.modules.event.entites.ticket.dto.CreateTicketRequestDTO;
import com.davifrjose.eventus.modules.event.entites.ticket.entities.OrderTicketEntity;
import com.davifrjose.eventus.modules.event.entites.ticket.entities.TicketCategoryEntity;
import com.davifrjose.eventus.modules.event.entites.ticket.useCases.CreateOrderTicketUseCase;
import com.davifrjose.eventus.modules.event.entites.ticket.useCases.CreateTicketCategoryUseCase;
import com.davifrjose.eventus.modules.event.entites.ticket.useCases.CreateTicketUseCase;
import com.davifrjose.eventus.modules.event.entites.ticket.useCases.ListAllTickesByUserOrderUseCase;
import com.davifrjose.eventus.modules.event.entites.ticket.utils.QRCodeUtils;

import com.davifrjose.eventus.modules.user.repository.UserRepository;
import com.google.zxing.WriterException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/ticket")
public class TicketController {

  @Autowired
  private CreateTicketUseCase createTicketUseCase;

  @Autowired
  private CreateTicketCategoryUseCase createTicketCategoryUseCase;

  @Autowired
  private CreateOrderTicketUseCase createOrderTicketUseCase;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ListAllTickesByUserOrderUseCase listAllTickesByUserOrderUseCase;

  @Autowired
  private QRCodeUtils qrcodeUtils;


  @PostMapping("/category/")
  @Operation(summary = "Ticket category registering", description = "This function is responsible for ticket category an organizer")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> createTicketCategory(@Valid @RequestBody TicketCategoryEntity ticketCategoryEntity)
  {
    try {
      var result = this.createTicketCategoryUseCase.execute(ticketCategoryEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // TODO: handle exception
      return ResponseEntity.badRequest().body(e);
    }
  }
  
  @PostMapping( path = "/", produces = MediaType.IMAGE_JPEG_VALUE )
  public void createTicket(@Valid @RequestBody CreateTicketRequestDTO createTicketRequestDTO, HttpServletResponse response) throws WriterException, IOException {


    try {
      this.createTicketUseCase.execute(createTicketRequestDTO);
      qrcodeUtils.generateQr(response.getOutputStream());
       response.getOutputStream().flush();
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println(e);
    }

    }

    @GetMapping("/{userId}/")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> findTicketsByUser(@PathVariable String userId)
    {

      try {
        var  user = this.userRepository.findById(UUID.fromString(userId));
        if(user == null) throw new UserNotFoundException();
        var result = this.listAllTickesByUserOrderUseCase.listAllTicketsByUser(user);
        return ResponseEntity.ok().body(result);
      } catch (Exception e) {
        // TODO: handle exception
        return ResponseEntity.badRequest().body(e);
      }

    }

    @PostMapping("/order/")
    public ResponseEntity<Object> createOrderTicket(@Valid @RequestBody OrderTicketEntity orderTicketEntity)
    {
      try {
        var result = this.createOrderTicketUseCase.execute(orderTicketEntity.getUserOrderId(), orderTicketEntity.getTicketId());
        return ResponseEntity.ok().body(result);
      } catch (Exception e) {
        // TODO: handle exception
        return ResponseEntity.badRequest().body(e);
      }
    }
    
  }

