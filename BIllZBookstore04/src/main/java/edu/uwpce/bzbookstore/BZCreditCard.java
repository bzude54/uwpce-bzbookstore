package edu.uwpce.bzbookstore;

import java.util.Date;

public class BZCreditCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4626533148786538912L;
	
	private String cardNumber;
	private String cardExpirationDate;
	private String cardType;
	private String cardCode;
	private String cardOwnerName;
	private String cardVendor;
	private int cardOwnerId;
	
	
	public BZCreditCard(){};
	
	public BZCreditCard(int cardOwnerId, String cardNumber, String expiration, String cardType, String cardCode, String cardVendor, String cardOwnerName) {
		this.cardCode = cardCode;
		this.cardNumber = cardNumber;
		this.cardOwnerId = cardOwnerId;
		this.cardOwnerName = cardOwnerName;
		this.cardType = cardType;
		this.cardVendor = cardVendor;
		this.cardExpirationDate = expiration;
		
	}
		

	public int getCardOwnerId() {
		return cardOwnerId;
	}

	public void setCardOwnerId(int cardOwnerId) {
		this.cardOwnerId = cardOwnerId;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpirationDate() {
		return this.cardExpirationDate;
	}

	public void setCardExpirationDate(String expiration) {
		this.cardExpirationDate = expiration;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardOwnerName() {
		return this.cardOwnerName;
	}

	public void setCardOwnerName(String name) {
		this.cardOwnerName = name;
	}

	public String getCardCode() {
		return this.cardCode;
	}

	public void setCardCode(String code) {
		this.cardCode = code;
	}


	public String getCardVendor() {
		return this.cardVendor;
	}

	public void setCardVendor(String vendor) {
		this.cardVendor = vendor;
	}

}
