package edu.uwpce.bzude;

import java.util.List;
import java.util.Map;

public interface BZCart {

	List<BZSimpleCartItem> getCartItems();
	
	void setCartItems(List<BZSimpleCartItem> cart);
	
	BZSimpleCartItem getSingleCartItem(String itemId);
	
	void setSingleCartItem(BZSimpleCartItem cartItem);
		
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
