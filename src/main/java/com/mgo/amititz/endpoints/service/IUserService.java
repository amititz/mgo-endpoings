package com.mgo.amititz.endpoints.service;

import java.util.Collection;

import com.mgo.amititz.endpoints.dto.UserDto;
import com.mgo.amititz.endpoints.dto.UserGroupDto;

public interface IUserService {

	Collection<UserDto> findAll();
	Collection<UserGroupDto> findUsers(String filterField, String filterValue, String groupingField) throws InvalidFieldException;
	public abstract UserDto findByUserName(String userName);
}