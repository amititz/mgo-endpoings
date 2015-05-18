package com.mgo.amititz.endpoints.service;

import java.util.List;

import com.mgo.amititz.endpoints.entity.UserEntity;
import com.mgo.amititz.endpoints.entity.UserEntityRepository;

interface IUserRepositoryFindByFieldValue {
	List<UserEntity> find(UserEntityRepository repository, String fieldValue);
}