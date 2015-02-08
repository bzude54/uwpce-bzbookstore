package edu.uwpce.bzude;

import java.util.Map;

public interface BZCartManager {
	
	public Map<String, BZCartItem> getCart();
	
	public void setCart(Map<String, BZCartItem> cartItems);
	
	public void setCartItem(BZCartItem cartItem);
	
	public BZCartItem getCartItem(String itemId);
	
	public void setItemQty(String itemId, int quantity);
	
	public int getItemQty(String itemId);
	
	public double getCartSubtotal();
	
	public double getCartTax();
	
	public int getCartQty();
	
	public double getCartShippingCost();
	
	

}
