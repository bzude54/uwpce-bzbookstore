package edu.uwpce.bzude;

public class BZSimpleCartItem implements BZCartItem {
	
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
			System.out.println("in setCartItemId setting id to: " + this.cartItemId);
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
		System.out.println("in setCartItemBook with book: " + book.getTitle());
		this.cartItemBook = book;
		this.cartItemId = book.getISBN();
	}


	@Override
	public void incrementCartItemQty() {
		this.cartItemQty += 1;
	}


}
