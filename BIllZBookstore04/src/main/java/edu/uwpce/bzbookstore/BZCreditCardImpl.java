package edu.uwpce.bzbookstore;

import java.util.Date;

public class BZCreditCardImpl implements BZCreditCard {

	private String cardNumber;
	private Date cardExpirationDate;
	private String cardType;
	private String cardCode;
	private String cardOwnerName;
	private String cardVendor;
	private int cardOwnerId;
	
	
	public BZCreditCardImpl(){};
	
	public BZCreditCardImpl(int cardOwnerId, String cardNumber, Date expiration, String cardType, String cardCode, String cardVendor, String cardOwnerName) {
		this.cardCode = cardCode;
		this.cardNumber = cardNumber;
		this.cardOwnerId = cardOwnerId;
		this.cardOwnerName = cardOwnerName;
		this.cardType = cardType;
		this.cardVendor = cardVendor;
		this.cardExpirationDate = expiration;
		
	}
	
	
	@Override
	public int getCardOwnerId() {
		return cardOwnerId;
	}

	@Override
	public void setCardOwnerId(int cardOwnerId) {
		this.cardOwnerId = cardOwnerId;
	}

	@Override
	public String getCardNumber() {
		return this.cardNumber;
	}

	@Override
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public Date getCardExpiration() {
		return this.cardExpirationDate;
	}

	@Override
	public void setCardExpiration(Date expiration) {
		this.cardExpirationDate = expiration;
	}

	@Override
	public String getCardType() {
		return this.cardType;
	}

	@Override
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String getCardOwnerName() {
		return this.cardOwnerName;
	}

	@Override
	public void setCardOwnerName(String name) {
		this.cardOwnerName = name;
	}

	@Override
	public String getCardCode() {
		return this.cardCode;
	}

	@Override
	public void setCardCode(String code) {
		this.cardCode = code;
	}


	@Override
	public String getCardVendor() {
		return this.cardVendor;
	}

	@Override
	public void setCardVendor(String vendor) {
		this.cardVendor = vendor;
	}

}
