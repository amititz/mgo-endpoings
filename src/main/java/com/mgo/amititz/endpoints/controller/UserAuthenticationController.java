package com.mgo.amititz.endpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mgo.amititz.endpoints.dto.UserAuthenticationInfoDto;
import com.mgo.amititz.endpoints.service.IUserAuthenticationService;

@RestController
public class UserAuthenticationController {
	
	private IUserAuthenticationService authService;
	
	@Autowired
	public UserAuthenticationController(IUserAuthenticationService userService) {
		this.authService = userService;
	}

	@RequestMapping(value = "/endpoints/1.0/authenticate" , method=RequestMethod.POST)
	public String authenticateUser(@RequestBody UserAuthenticationInfoDto authenticationInfo) {
		// TODO: use password has instead of clear-text, both here and when populating the DB.
		// in practice, Spring Security will probably be utilized, which will take care of
		// most of the mechanics of this.
		boolean result = this.authService.authenticate(authenticationInfo.getUserName(), authenticationInfo.getPassword());
		if (result) {
			return "Authentication successful";
		} else {
			return "Authentication failed";
		}
	}
		
}
