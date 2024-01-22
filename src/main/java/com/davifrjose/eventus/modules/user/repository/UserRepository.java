package com.davifrjose.eventus.modules.user.repository;



import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.davifrjose.eventus.modules.user.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  UserDetails findByEmail(String email);
  
  
}
