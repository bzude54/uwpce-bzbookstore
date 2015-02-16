package edu.uwpce.bzude;

public interface BZCartItem {
	
	
	BZBook getCartItemBook();	

	void setCartItemBook(BZBook book);
	
	String getCartItemId();
	
	void setCartItemId(String itemId);
	
	int getCartItemQty();

	void setCartItemQty(int qty);
	
	void setCartItemTotalPrice();

	double getCartItemTotalPrice();

	void incrementCartItemQty();


}
