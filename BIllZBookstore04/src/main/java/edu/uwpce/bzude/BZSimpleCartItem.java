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
		this.setCartItemId(null);
	}

	public BZSimpleCartItem(BZBook book) {
		this.cartItemQty = 1;
		this.cartItemBook = book;
		this.cartItemTotalPrice = book.getPrice();
		this.setCartItemId(book.getISBN());
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	@Override
	public int getCartItemQty() {
		return cartItemQty;
	}

	@Override
	public void setCartItemQty(int qty) {
		this.cartItemQty = qty;
		this.cartItemTotalPrice = qty * cartItemBook.getPrice();
	}

	@Override
	public double getCartItemTotalPrice() {
		this.cartItemTotalPrice = this.cartItemQty * cartItemBook.getPrice();	
		return cartItemTotalPrice;
	}

	@Override
	public void setCartItemPriceTotal() {
		this.cartItemTotalPrice = this.cartItemQty * cartItemBook.getPrice();
	}

	@Override
	public BZBook getCartItemBook() {
		return cartItemBook;
	}

	@Override
	public void setCartItemBook(BZBook book) {
		this.cartItemBook = book;
	}


}
