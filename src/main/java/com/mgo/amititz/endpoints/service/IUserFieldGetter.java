package com.mgo.amititz.endpoints.service;

import com.mgo.amititz.endpoints.dto.UserDto;

interface IUserFieldGetter {
	String getFieldValue(UserDto user);
}