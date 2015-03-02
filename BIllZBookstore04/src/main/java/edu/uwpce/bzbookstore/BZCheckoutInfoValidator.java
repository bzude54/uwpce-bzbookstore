package edu.uwpce.bzbookstore;

public interface BZCheckoutInfoValidator {
	
	boolean validate(BZCheckoutInfo checkoutinfo);
	
	boolean validShippingAddress(BZCheckoutInfo checkoutinfo);
	
	boolean validCreditCard(BZCheckoutInfo checkoutinfo);
	
	boolean validAge(BZCheckoutInfo checkoutinfo);
	
	boolean validGiftcard(BZCheckoutInfo checkoutinfo);
	
	
	

}
