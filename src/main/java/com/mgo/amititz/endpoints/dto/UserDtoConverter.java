package com.mgo.amititz.endpoints.dto;

import org.springframework.stereotype.Component;

import com.mgo.amititz.endpoints.entity.UserEntity;
import com.mgo.amititz.endpoints.service.IUserDtoConverter;

@Component
public class UserDtoConverter implements IUserDtoConverter {
	
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDtoConverter#convertUserEntity(com.mgo.amititz.endpoints.data.UserEntity)
	 */
	@Override
	public UserDto convertUserEntity(UserEntity userEntity) {
		UserDto ret = new UserDto();
		ret.setFirstName(userEntity.getFirstName());
		ret.setLastName(userEntity.getLastName());
		ret.setCity(userEntity.getCity());
		ret.setState(userEntity.getState());
		ret.setEmailAddress(userEntity.getEmailAddress());
		ret.setUserName(userEntity.getUserName());
		ret.setPassword(userEntity.getPassword());
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDtoConverter#convertUserDto(com.mgo.amititz.endpoints.service.UserDto)
	 */
	@Override
	public UserEntity convertUserDto(UserDto userDto) {
		UserEntity ret = new UserEntity();
		ret.setFirstName(userDto.getFirstName());
		ret.setLastName(userDto.getLastName());
		ret.setCity(userDto.getCity());
		ret.setState(userDto.getState());
		ret.setEmailAddress(userDto.getEmailAddress());
		ret.setUserName(userDto.getUserName());
		ret.setPassword(userDto.getPassword());
		return ret;
	}
}
