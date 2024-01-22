package com.davifrjose.eventus.modules.event.entites.ticket.utils.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class DecodedQrRequest {
  private MultipartFile qrCode;
}
