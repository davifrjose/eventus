package com.davifrjose.eventus.modules.user.useCases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.modules.user.repository.UserRepository;



@Service
public class AuthUserUseCase implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Override

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    
    // TODO Auto-generated method stub
    return this.userRepository.findByEmail(email);
  }
  
  

  }

