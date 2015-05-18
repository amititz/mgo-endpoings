package com.mgo.amititz.endpoints.service;

import com.mgo.amititz.endpoints.dto.UserDto;
import com.mgo.amititz.endpoints.entity.UserEntity;

public interface IUserDtoConverter {

	UserDto convertUserEntity(UserEntity userEntity);
	UserEntity convertUserDto(UserDto userDto);

}