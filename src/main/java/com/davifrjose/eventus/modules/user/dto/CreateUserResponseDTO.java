package com.davifrjose.eventus.modules.user.dto;

import com.davifrjose.eventus.modules.user.UserRole;

public record CreateUserResponseDTO (String email, String password, UserRole role) {
  
}
