package com.davifrjose.eventus.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davifrjose.eventus.modules.user.UserOrderEntity;
import com.davifrjose.eventus.modules.user.useCases.CreateUserOrderUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "User Order", description = "User order")
public class UserOrderController {
  
  @Autowired
  private CreateUserOrderUseCase createUserOrderUseCase;

  @PostMapping("/order/")
  public ResponseEntity<Object> create(@Valid @RequestBody UserOrderEntity userOrderEntity)
  {
    try {
      var result = this.createUserOrderUseCase.execute(userOrderEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // TODO: handle exception
      return ResponseEntity.badRequest().body(e);
    }
  }
}
