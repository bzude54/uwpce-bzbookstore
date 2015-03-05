package edu.uwpce.bzbookstore;

import java.io.Serializable;
import java.util.Date;

public interface BZCreditCard extends Serializable {
	
	String getCardNumber();
	
	void setCardNumber(String cardnumber);
	
	Date getCardExpiration();
	
	void setCardExpiration(Date expiration);
	
	String getCardType();
	
	void setCardType(String cardtype);
	
	String getCardOwnerName();
	
	void setCardOwnerName(String name);
	
	String getCardCode();
	
	void setCardCode(String code);

	int getCardOwnerId();

	void setCardOwnerId(int cardOwnerId);	
	
	String getCardVendor();
	
	void setCardVendor(String vendor);

}
