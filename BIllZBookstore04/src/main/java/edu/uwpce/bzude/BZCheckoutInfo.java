package edu.uwpce.bzude;

public class BZCheckoutInfo {
	
	private BZSimpleCart cart;
	private BZUserInfo userInfo;
	
	public BZCheckoutInfo(){
		this.cart = new BZSimpleCart();
		this.userInfo = new BZUserInfo();
	}
	
	public BZCheckoutInfo(BZSimpleCart bzcart, BZUserInfo bzuserinfo) {
		this.userInfo = bzuserinfo;
		this.cart = bzcart;
	}

	
	public BZSimpleCart getCart() {
		return cart;
	}

	public void setCart(BZSimpleCart cart) {
		this.cart = cart;
	}

	public BZUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(BZUserInfo userinfo) {
		this.userInfo = userinfo;
	}

	public int getCartId() {
		return cart.getCartId();
	}

	public double getCartTotalPrice() {
		return cart.getCartTotal();
	}

	public String getUserStreetAddress() {
		return userInfo.getShippingStreetAddress();
	}
	
	public void setUserStreetAddress(String streetaddr)	{
		this.userInfo.setShippingStreetAddress(streetaddr);
	}

	public String getUserCity() {
		return userInfo.getShippingCity();
	}
	
	public void setUserCity(String city)	{
		this.userInfo.setShippingCity(city);
	}


	public String getUserState() {
		return userInfo.getShippingState();
	}
	
	public void setUserState(String state)	{
		this.userInfo.setShippingState(state);
	}


	public String getUserZip() {
		return userInfo.getShippingZip();
	}
	
	public void setUserZip(String zip)	{
		this.userInfo.setShippingZip(zip);
	}


	public String getUserCreditCard() {
		return userInfo.getCreditCard1();
	}
	
	public void setUserCreditCard(String card)	{
		this.userInfo.setCreditCard1(card);
	}




}
