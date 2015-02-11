package edu.uwpce.bzude;

public interface BZCartItem {
	
	
	BZBook getCartItemBook();	

	void setCartItemBook(BZBook book);
	
	int getCartItemQty();

	void setCartItemQty(int qty);
	
	void setCartItemTotalPrice();

	double getCartItemTotalPrice();


}
