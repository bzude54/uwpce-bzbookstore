package edu.uwpce.bzbookstore;

import java.util.Map;

public interface BZCartsManager {
	
	Map<Integer, BZCart> getAllCarts();
	
	void setAllCarts(Map<Integer, BZCart> cart);
	
	void setCart(BZCart cart);
	
	BZCart getCart(int cartId);
	
	BZCart updateCart(BZCart cart);
	
	boolean deleteCart(int cartid);
			
	double getCartTax(int cartId);
		
	double getCartShippingCost(int cartId);
	

}
