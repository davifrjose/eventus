package com.davifrjose.eventus.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davifrjose.eventus.modules.user.UserEntity;
import com.davifrjose.eventus.modules.user.dto.AuthUserRequestDTO;
import com.davifrjose.eventus.modules.user.dto.AuthUserResponseDTO;
import com.davifrjose.eventus.modules.user.dto.CreateUserResponseDTO;
import com.davifrjose.eventus.modules.user.useCases.AuthUserUseCase;
import com.davifrjose.eventus.modules.user.useCases.CreateUserUseCase;
import com.davifrjose.eventus.providers.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "User auth", description = "User auth")
public class AuthUserController {
  
  @Autowired
  AuthUserUseCase authUserUseCase;

   @Autowired
  CreateUserUseCase createUserUseCase;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  TokenService tokenService;
  
   @PostMapping("/login")
   @Operation(summary = "User login", description = "This function is responsible for login an user")
   @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
        @Content(
          array = @ArraySchema(schema = @Schema(implementation = AuthUserResponseDTO.class))
        )
      }),
      @ApiResponse(responseCode = "400", description = "User with does not exists")
    } 
    )
  public ResponseEntity<Object> auth(@RequestBody @Valid AuthUserRequestDTO authUserRequestDTO){
    var userPassword = new UsernamePasswordAuthenticationToken(authUserRequestDTO.email(), authUserRequestDTO.password());
    var auth = this.authenticationManager.authenticate(userPassword);

    var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
    
    return ResponseEntity.ok(new AuthUserResponseDTO(token));
    
  }

  @PostMapping("/register")
  @Operation(summary = "User registering", description = "This function is responsible for registering an user")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(
        array = @ArraySchema(schema = @Schema(implementation = CreateUserResponseDTO.class))
      )
    }),
    @ApiResponse(responseCode = "400", description = "User already exists")
  } 
  )
  public ResponseEntity<Object> create(@Valid @RequestBody CreateUserResponseDTO userEntityDTO)  
  {
    try {
      var userEntity = UserEntity.builder()
        .email(userEntityDTO.email())
        .password(userEntityDTO.password())
        .role(userEntityDTO.role())
        .build();
      var result =  this.createUserUseCase.execute(userEntity);
      return ResponseEntity.ok().body("User created successfully" + " " + userEntity.getEmail() + " " + userEntity.getRole() );
    } catch (Exception e) {
      return  ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
