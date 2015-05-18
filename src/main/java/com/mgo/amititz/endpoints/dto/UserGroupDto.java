package com.mgo.amititz.endpoints.dto;

import java.util.ArrayList;
import java.util.List;


public class UserGroupDto {
	private String groupByFieldName;
	private String groupByFieldValue;
	private List<UserDto> users = new ArrayList<UserDto>();
	
	public UserGroupDto(String groupByFieldName, String groupByFieldValue) {
		super();
		this.groupByFieldName = groupByFieldName;
		this.groupByFieldValue = groupByFieldValue;
	}
	
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserGroup#getGroupByFieldName()
	 */
	public String getGroupByFieldName() {
		return groupByFieldName;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserGroup#getGroupByFieldValue()
	 */
	public String getGroupByFieldValue() {
		return groupByFieldValue;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserGroup#getUsers()
	 */
	public List<UserDto> getUsers() {
		return users;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserGroup#addUser(com.mgo.amititz.endpoints.service.UserDto)
	 */
	public void addUser(UserDto user) {
		this.users.add(user);
	}
	
}
