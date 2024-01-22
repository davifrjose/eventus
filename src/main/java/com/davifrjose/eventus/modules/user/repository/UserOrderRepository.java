package com.davifrjose.eventus.modules.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davifrjose.eventus.modules.user.UserEntity;
import com.davifrjose.eventus.modules.user.UserOrderEntity;





public interface UserOrderRepository extends JpaRepository<UserOrderEntity, UUID> {
  UserOrderEntity findByUserEntity(Optional<UserEntity> userEntity);
}
