package edu.uwpce.bzude;

public interface BZCartItem {
	
	
	int getCartItemQty();

	void setCartItemQty(int qty);
	
	void setCartItemPriceTotal();
	
	BZBook getCartItemBook();	

	double getCartItemTotalPrice();

	void setCartItemBook(BZBook book);

}
