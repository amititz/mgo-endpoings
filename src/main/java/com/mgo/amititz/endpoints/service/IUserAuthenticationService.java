package com.mgo.amititz.endpoints.service;

public interface IUserAuthenticationService {

	public boolean authenticate(String username, String password);
}
