package edu.uwpce.bzbookstore;

import java.util.Map;

public interface BZCartManager {
	
	Map<Integer, BZCart> getCarts();
	
	void setCarts(Map<Integer, BZCart> cart);
	
	void setSingleCart(BZCart cart);
	
	BZCart getSingleCart(int cartId);
			
	double getCartTax(int cartId);
		
	double getCartShippingCost(int cartId);
	

}
