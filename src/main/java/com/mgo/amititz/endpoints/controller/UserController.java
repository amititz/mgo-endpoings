package com.mgo.amititz.endpoints.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgo.amititz.endpoints.dto.UserGroupDto;
import com.mgo.amititz.endpoints.service.IUserService;
import com.mgo.amititz.endpoints.service.InvalidFieldException;

@RestController
public class UserController {

	private IUserService userService;
	
	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/endpoints/1.0/users" , method=RequestMethod.GET)
	public ResponseEntity<?> getUsers(
			@RequestParam(value="filterField", required=false) String filterField, 
			@RequestParam(value="filterValue", required=false) String filterValue, 
			@RequestParam(value="groupBy", required=false, defaultValue="") String groupByField,
			@RequestParam(value="page", required=false, defaultValue="") String page,
			@RequestParam(value="pageSize", required=false, defaultValue="") String pageSize) {

		Collection<UserGroupDto> result;
		try {
			if (page != null && !page.isEmpty() && pageSize != null && !pageSize.isEmpty()) {
				result = this.userService.findUsers(filterField, filterValue, groupByField, Integer.parseInt(page), Integer.parseInt(pageSize));
			} else {
				result = this.userService.findUsers(filterField, filterValue, groupByField);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			String message = String.format("Error processing request: %s", e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
}
