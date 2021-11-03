package com.volkruss.securitytest.domain.repository;

import java.util.Optional;

import com.volkruss.securitytest.domain.entity.auth.UserEntity;

public interface UserRepository {
	// 直接エンティティを返してしまっています
	Optional<UserEntity> findByUserName(String username);
}
