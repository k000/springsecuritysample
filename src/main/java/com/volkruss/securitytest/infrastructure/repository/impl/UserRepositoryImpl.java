package com.volkruss.securitytest.infrastructure.repository.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.volkruss.securitytest.domain.entity.auth.UserEntity;
import com.volkruss.securitytest.domain.repository.UserRepository;
import com.volkruss.securitytest.infrastructure.repository.jpa.UserRepositoryJpa;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private UserRepositoryJpa jpa;
		
	@Override
	public Optional<UserEntity> findByUserName(String username) {
		return jpa.findById(username);
	}

	@Override
	public boolean saveUser(String username, String password) {
		UserEntity entity = new UserEntity();
		entity.setUsername(username);
		entity.setPassword(password);
		UserEntity result = jpa.save(entity);
		if(Objects.isNull(result)) {
			return false;
		}
		return true;
	}
}
