package com.davifrjose.eventus.modules.user.useCases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davifrjose.eventus.exceptions.UserNotFoundException;
import com.davifrjose.eventus.modules.user.UserOrderEntity;
import com.davifrjose.eventus.modules.user.repository.UserOrderRepository;
import com.davifrjose.eventus.modules.user.repository.UserRepository;

@Service
public class CreateUserOrderUseCase {
  @Autowired
  private UserOrderRepository userOrderRepository;

  @Autowired
  private UserRepository userRepository;

  public UserOrderEntity execute (UserOrderEntity userOrderEntity) {
    // validate if user exists
    this.userRepository.findById(userOrderEntity.getUser_Id())
      .orElseThrow(()-> {
        throw new UserNotFoundException();
      });

    return this.userOrderRepository.save(userOrderEntity);
  }
}
