package edu.uwpce.bzbookstore;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZUserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1450741177243238042L;
	
    private static final Logger logger = LoggerFactory.getLogger(BZUserInfo.class);

	
	private int userId;
	
	private String firstName;
	private String lastName;

	@Size(min=4, max=16, message="User name must be 4 to 16 characters.")
	private String userName;
	
	@Size(min=8, max=8, message="Password must be 8 characters.")	
	private String password;
	
    @Pattern(regexp=".*@.*",
            message="must be a valid email address")
	private String emailAddress;
    
	private String mailingStreetAddress;
	private String mailingCity;
	private String mailingState;
	private String mailingZip;
	private String shippingStreetAddress;
	private String shippingCity;
	private String shippingState;
	private String shippingZip;
	private String phoneNumber1;
	private String phoneNumber2;

	@Size(min=16, max=16, message="Credit card number must be 16 digits with no spaces or hyphens.")
	@Pattern(regexp="\\d{16}")
	private String creditCard1;
	
	@Size(min=16, max=16, message="Credit card number must be 16 digits with no spaces or hyphens.")
	@Pattern(regexp="\\d{16}")	
	private String creditCard2;
	
	public BZUserInfo() {}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getMailingStreetAddress() {
		return mailingStreetAddress;
	}
	public void setMailingStreetAddress(String mailingStreetAddress) {
		this.mailingStreetAddress = mailingStreetAddress;
	}
	public String getMailingCity() {
		return mailingCity;
	}
	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}
	public String getMailingState() {
		return mailingState;
	}
	public void setMailingState(String mailingState) {
		this.mailingState = mailingState;
	}
	public String getMailingZip() {
		return mailingZip;
	}
	public void setMailingZip(String mailingZip) {
		this.mailingZip = mailingZip;
	}
	public String getShippingStreetAddress() {
		return shippingStreetAddress;
	}
	public void setShippingStreetAddress(String shippingStreetAddress) {
		this.shippingStreetAddress = shippingStreetAddress;
	}
	public String getShippingCity() {
		return shippingCity;
	}
	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}
	public String getShippingState() {
		return shippingState;
	}
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}
	public String getShippingZip() {
		return shippingZip;
	}
	public void setShippingZip(String shippingZip) {
		this.shippingZip = shippingZip;
	}
	public String getPhoneNumber1() {
		return phoneNumber1;
	}
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}
	public String getPhoneNumber2() {
		return phoneNumber2;
	}
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
	public String getCreditCard1() {
		return creditCard1;
	}
	public void setCreditCard1(String creditCard1) {
		this.creditCard1 = creditCard1;
	}
	public String getCreditCard2() {
		return creditCard2;
	}
	public void setCreditCard2(String creditCard2) {
		this.creditCard2 = creditCard2;
	}

}
