package edu.uwpce.bzude;

public class BZCheckoutInfo {
	
	private BZCart cart;
	private BZUserInfo userinfo;
	
	public BZCheckoutInfo(){}
	
	public BZCheckoutInfo(BZCart bzcart, BZUserInfo bzuserinfo) {
		this.userinfo = bzuserinfo;
		this.cart = bzcart;
	}

	
	public BZUserInfo getUserInfo() {
		return userinfo;
	}

	public void setUserInfo(BZUserInfo bzuserinfo) {
		this.userinfo = bzuserinfo;
	}

	public int getCartid() {
		return cart.getCartId();
	}

	public double getCartTotalPrice() {
		return cart.getCartTotal();
	}

	public String getUserStreetAddress() {
		return userinfo.getShippingStreetAddress();
	}


	public String getUserCity() {
		return userinfo.getShippingCity();
	}

	public String getUserState() {
		return userinfo.getShippingState();
	}


	public String getUserZip() {
		return userinfo.getShippingZip();
	}


	public String getUserCreditCard() {
		return userinfo.getCreditCard1();
	}



}
