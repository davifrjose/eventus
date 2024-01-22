package com.davifrjose.eventus.modules.event.entites.ticket.dto;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequestDTO {
  
  private UUID event_id;
  private UUID ticket_category_id;
}
