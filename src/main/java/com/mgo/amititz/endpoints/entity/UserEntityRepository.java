package com.mgo.amititz.endpoints.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmailAddress(String emailAddress);
	UserEntity findByUserName(String userName);

	List<UserEntity> findByLastName(String lastName);
	List<UserEntity> findByFirstName(String firstName);
	List<UserEntity> findByState(String state);
	List<UserEntity> findByCity(String city); 
	
	Page<UserEntity> findByFirstName(String city, Pageable pageable);
	Page<UserEntity> findByLastName(String city, Pageable pageable);
	Page<UserEntity> findByState(String state, Pageable pageable);
	Page<UserEntity> findByCity(String city, Pageable pageable);
}
