package edu.uwpce.bzude;

import java.util.Map;

public class BZSimpleCartManager implements BZCartManager {
	
	private static final int STATE_TAX_PERCENT = 8;
	private static final double SHIPPING_COST_UNDER_THRESHOLD = 5.00;
	private static final double SHIPPING_COST_OVER_THRESHOLD = 15.00;
	private static final int SHIPPING_QTY_THRESHOLD = 5;
	
	private Map<String, BZCartItem> cart;
	private int cartQty;
	private double cartSubtotal;

	@Override
	public Map<String, BZCartItem> getCart() {
		return this.cart;
	}

	@Override
	public void setCart(Map<String, BZCartItem> cartItems) {
		this.cart = cartItems;
	}

	@Override
	public void setCartItem(BZCartItem cartItem) {
		if (cartItem != null && cartItem.getCartItemBook() != null) {
			this.cart.put(cartItem.getCartItemBook().getISBN(), cartItem);
		}
	}

	@Override
	public void setItemQty(String itemId, int quantity) {
		BZCartItem cartItem = cart.get(itemId);
		if (cartItem != null) {
			cartItem.setCartItemQty(quantity);
		}
	}

	@Override
	public double getCartSubtotal() {
		cartSubtotal = 0.0;
		for (Map.Entry<String, BZCartItem> entry : cart.entrySet()) {
			cartSubtotal += entry.getValue().getCartItemTotalPrice();
		}
		return cartSubtotal;

	}

	@Override
	public double getCartTax() {
		double tax = 0.0;
		tax = this.getCartSubtotal() * STATE_TAX_PERCENT / 100;
		return tax;
	}

	@Override
	public int getCartQty() {
		cartQty = 0;
		for (Map.Entry<String, BZCartItem> entry : cart.entrySet()) {
			cartQty += entry.getValue().getCartItemQty();
		}
		return cartQty;
	}

	@Override
	public double getCartShippingCost() {
		double shippingCost = 0.0;
		if (this.getCartQty() >= SHIPPING_QTY_THRESHOLD){
			shippingCost = SHIPPING_COST_OVER_THRESHOLD;
		} else {
			shippingCost = SHIPPING_COST_UNDER_THRESHOLD;			
		}
		return shippingCost;
	}

	@Override
	public BZCartItem getCartItem(String itemId) {
		return cart.get(itemId);
	}

	@Override
	public int getItemQty(String itemId) {		
		return cart.get(itemId).getCartItemQty();
	}

}
