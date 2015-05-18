package com.mgo.amititz.endpoints.service;

import java.util.Collection;
import java.util.List;

import com.mgo.amititz.endpoints.dto.UserDto;
import com.mgo.amititz.endpoints.dto.UserGroupDto;

interface IUserGrouping {
	Collection<UserGroupDto> groupUsers(List<UserDto> users, String groupingField) throws InvalidFieldException;
}