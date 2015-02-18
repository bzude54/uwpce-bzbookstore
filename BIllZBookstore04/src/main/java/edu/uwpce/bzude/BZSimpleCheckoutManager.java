package edu.uwpce.bzude;

public class BZSimpleCheckoutManager implements BZCheckoutManager {


	private BZCheckoutInfo checkoutinfo;
	
	

	@Override
	public BZCheckoutInfo getCheckoutinfo() {
		return checkoutinfo;
	}

	@Override
	public void setCheckoutinfo(BZCheckoutInfo checkoutinfo) {
		this.checkoutinfo = checkoutinfo;
	}

	@Override
	public boolean validShippingAddress() {
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
	public boolean validCreditCard() {
		boolean validCreditCard = false;
		if (!checkoutinfo.getUserCreditCard().isEmpty()) {
			validCreditCard = true;
		}
		return validCreditCard;
	}

}
