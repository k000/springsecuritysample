package com.volkruss.securitytest.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volkruss.securitytest.domain.entity.auth.UserEntity;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, String>{

}
