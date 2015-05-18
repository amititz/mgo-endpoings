package com.mgo.amititz.endpoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgo.amititz.endpoints.dto.UserDto;

@Service
public class UserAuthenticationServiceImpl implements IUserAuthenticationService {

	private IUserService userService;
	
	@Autowired
	public UserAuthenticationServiceImpl(IUserService userService) {
		this.userService = userService;
	}


	@Override
	public boolean authenticate(String userName, String password) {
		
		UserDto userDto = this.userService.findByUserName(userName);
		return userDto != null && password != null && password.equals(userDto.getPassword());
	}

}
