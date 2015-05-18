package com.mgo.amititz.endpoints.dto;

import java.io.Serializable;



public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String userName;
	private String password;
	private String city;
	private String state;
	private String company;
	
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#getEmailAddress()
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#setEmailAddress(java.lang.String)
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#getUserName()
	 */
	public String getUserName() {
		return userName;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#setUserName(java.lang.String)
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#getPassword()
	 */
	public String getPassword() {
		return password;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#setPassword(java.lang.String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#getCity()
	 */
	public String getCity() {
		return city;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#setCity(java.lang.String)
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#getState()
	 */
	public String getState() {
		return state;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#setState(java.lang.String)
	 */
	public void setState(String state) {
		this.state = state;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#getCompany()
	 */
	public String getCompany() {
		return company;
	}
	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserDto#setCompany(java.lang.String)
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
}
