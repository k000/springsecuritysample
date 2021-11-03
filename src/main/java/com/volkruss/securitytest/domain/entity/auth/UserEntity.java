package com.volkruss.securitytest.domain.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Proxy(lazy=false)
@Table(name = "m_user")
public class UserEntity {
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

}
