package edu.uwpce.bzude;

public interface BZCheckoutManager {
	
	BZCheckoutInfo getCheckoutinfo();

	void setCheckoutinfo(BZCheckoutInfo checkoutinfo);

	boolean validShippingAddress();
	
	boolean validCreditCard();

}
