package com.mgo.amititz.endpoints.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mgo.amititz.endpoints.dto.UserDto;
import com.mgo.amititz.endpoints.dto.UserGroupDto;
import com.mgo.amititz.endpoints.entity.UserEntity;

public interface IUserService {

	Collection<UserDto> findAll();
	Collection<UserGroupDto> findUsers(String filterField, String filterValue, String groupingField) throws InvalidFieldException;
	Collection<UserGroupDto> findUsers(String filterField, String filterValue, String groupingField, int pageNum, int pageSize) throws InvalidFieldException;
	UserDto findByUserName(String userName);
}