package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

public interface BZCart {

	List<BZCartItem> getCartItems();
	
	void setCartItems(List<BZCartItem> cart);
	
	BZCartItem getSingleCartItem(String itemId);
	
	void setSingleCartItem(BZCartItem cartItem);
		
	void setCartItemQty(String itemId, int qty);
	
	public double getCartSubtotal();
		
	public int getCartQty();

	int getCartItemQty(String itemId);
	
	int getCartId();
	
	void setCartId(int cartId);
	
	void setCartTax(int taxrate);
	
	void setCartShippingCost();
	
	double getCartTax();
	
	double getCartShippingCost();
	
	double getCartTotal();


	
}
