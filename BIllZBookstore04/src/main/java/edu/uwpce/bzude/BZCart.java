package edu.uwpce.bzude;

import java.util.List;
import java.util.Map;

public interface BZCart {

	List<BZCartItem> getCart();
	
	void setCart(List<BZCartItem> cart);
	
	BZCartItem getSingleCartItem(String itemId);
	
	void setSingleCartItem(BZCartItem item);
		
	void setCartItemQty(int qty);
	
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
