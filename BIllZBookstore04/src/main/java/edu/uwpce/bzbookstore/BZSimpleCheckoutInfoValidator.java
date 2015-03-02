package edu.uwpce.bzbookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZSimpleCheckoutInfoValidator implements BZCheckoutInfoValidator {
	
    private static final Logger logger = LoggerFactory.getLogger(BZSimpleCheckoutInfoValidator.class);


	@Override
	public boolean validate(BZCheckoutInfo checkoutinfo) {
		boolean validCheckoutInfo = false;
		validCheckoutInfo = validShippingAddress(checkoutinfo);
		validCheckoutInfo = validCreditCard(checkoutinfo);
		return validCheckoutInfo;
	}

	@Override
	public boolean validShippingAddress(BZCheckoutInfo checkoutinfo) {
		boolean validShipAddr = false;
		String street = checkoutinfo.getUserStreetAddress();
		String city = checkoutinfo.getUserCity();
		String state = checkoutinfo.getUserState();
		String zip = checkoutinfo.getUserZip();
		if (!street.isEmpty() && !city.isEmpty() && !state.isEmpty() && !zip.isEmpty()) {
			validShipAddr = true;			
		}
		return validShipAddr;
	}

	@Override
	public boolean validCreditCard(BZCheckoutInfo checkoutinfo) {
		boolean validCreditCard = false;
		if (!checkoutinfo.getUserCreditCard().isEmpty()) {
			validCreditCard = true;
		}
		return validCreditCard;
	}


	@Override
	public boolean validAge(BZCheckoutInfo checkoutinfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validGiftcard(BZCheckoutInfo checkoutinfo) {
		// TODO Auto-generated method stub
		return false;
	}


}
