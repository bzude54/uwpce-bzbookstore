package edu.uwpce.bzbookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZSimpleCartItem implements BZCartItem {
	
    private static final Logger logger = LoggerFactory.getLogger(BZSimpleCartItem.class);
	
	private String cartItemId;
	private int cartItemQty;
	private double cartItemTotalPrice;
	private BZBook cartItemBook;
	
	public BZSimpleCartItem(){
		this.cartItemQty = 0;
		this.cartItemTotalPrice = 0.0;
		this.cartItemBook = new BZBook();
		this.cartItemId = null;
	}

	public BZSimpleCartItem(BZBook book) {
		this.cartItemQty = 1;
		this.cartItemBook = book;
		this.cartItemTotalPrice = book.getPrice();
		this.cartItemId = book.getISBN();
	}

	@Override
	public String getCartItemId() {
		return cartItemId;
	}

	@Override
	public void setCartItemId(String itemId) {
			this.cartItemId = itemId;
			logger.info("in setCartItemId setting id to: " + this.cartItemId);
	}

	@Override
	public int getCartItemQty() {
		return cartItemQty;
	}

	@Override
	public void setCartItemQty(int qty) {
		this.cartItemQty = qty;
	}
	
	@Override
	public double getCartItemTotalPrice() {
		this.cartItemTotalPrice = this.cartItemQty * cartItemBook.getPrice();	
		return cartItemTotalPrice;
	}

	@Override
	public void setCartItemTotalPrice() {
		this.cartItemTotalPrice = this.cartItemQty * cartItemBook.getPrice();
	}

	@Override
	public BZBook getCartItemBook() {
		return cartItemBook;
	}

	@Override
	public void setCartItemBook(BZBook book) {
		logger.info("in setCartItemBook with book: " + book.getTitle());
		this.cartItemBook = book;
		this.cartItemId = book.getISBN();
	}


	@Override
	public void incrementCartItemQty() {
		this.cartItemQty += 1;
	}


}
