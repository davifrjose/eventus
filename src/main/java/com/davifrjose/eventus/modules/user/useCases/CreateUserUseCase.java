package com.davifrjose.eventus.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.davifrjose.eventus.exceptions.UserFoundException;

import com.davifrjose.eventus.modules.user.UserEntity;
import com.davifrjose.eventus.modules.user.repository.UserRepository;

@Service
public class CreateUserUseCase {
  @Autowired
  private UserRepository userRepository;

  
  public UserEntity execute (UserEntity userEntity)
  {
   if(this.userRepository.findByEmail(userEntity.getEmail() )!= null) throw new UserFoundException();

   String encyptedPassword = new BCryptPasswordEncoder().encode(userEntity.getPassword());

   var user = UserEntity.builder()
    .email(userEntity.getEmail())
    .password(encyptedPassword)
    .role(userEntity.getRole())
    .build();

    return this.userRepository.save(user);

  } 
}
