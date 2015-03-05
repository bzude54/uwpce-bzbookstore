package edu.uwpce.bzbookstore;

import java.io.Serializable;
import java.util.List;

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
	
    private List<BZAddress> addresses;
    
	private String phoneNumber1;
	private String phoneNumber2;

	private List<BZCreditCard> cards;
	
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
	
	public List<BZAddress> getAddresses() {
		return this.addresses;
	}
	
	public void setAddresses(List<BZAddress> addresses) {
		this.addresses = addresses;
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
	
	public List<BZCreditCard> getCards() {
		return cards;
	}

	public void setCards(List<BZCreditCard> cards) {
		this.cards = cards;
	}

}
