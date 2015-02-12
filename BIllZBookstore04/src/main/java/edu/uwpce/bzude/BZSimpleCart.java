package edu.uwpce.bzude;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZSimpleCart implements BZCart {
	
    private static final Logger logger = LoggerFactory.getLogger(BZSimpleCart.class);
    
    private static final int STATE_TAX_PERCENT = 8;
	private static final double SHIPPING_COST_UNDER_THRESHOLD = 5.00;
	private static final double SHIPPING_COST_OVER_THRESHOLD = 15.00;
	private static final int SHIPPING_QTY_THRESHOLD = 5;
    
    List<BZCartItem> cart;
	int cartId;

	public BZSimpleCart(){
		cart = new ArrayList<BZCartItem>();

	}
	
	public BZSimpleCart(int userId) {
		cart = new ArrayList<BZCartItem>();
		this.cartId = userId;
	}
	
	@Override
	public List<BZCartItem> getCart() {
		return cart;
	}

	@Override
	public void setCart(List<BZCartItem> cart) {
		this.cart = cart;
	}

	@Override
	public int getCartId() {
		return cartId;
	}

	@Override
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	@Override
	public BZCartItem getSingleCartItem(String itemId) {
		BZCartItem cartItem = null;
		if (itemId != null) {
			for (BZCartItem item : cart) {
				if (itemId.equals(item.getCartItemBook().getISBN())) {
					cartItem = item;
				}
			}
		}
		return cartItem;
	}

	@Override
	public void setSingleCartItem(BZCartItem item) {
		if (item != null) {
			System.out.println("adding BZCartItem to BZCart!");
			cart.add(item);
			System.out.println("added: " + item.getCartItemBook().getISBN());
		}
	}

	@Override
	public int getCartItemQty(String itemId) {
		int itemQty = 0;
		if (itemId != null) {
			for (BZCartItem item : cart) {
				if (itemId.equals(item.getCartItemBook().getISBN())) {
					itemQty = item.getCartItemQty();
				}
			}
		}
		return itemQty;
	}

	@Override
	public void setCartItemQty(int qty) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getCartSubtotal() {
		double cartSubTotal = 0.0;
		for (BZCartItem item : cart) {
			cartSubTotal += item.getCartItemTotalPrice();			
		}		
		return cartSubTotal;
	}

	@Override
	public int getCartQty() {
		int cartQty = 0;
		for (BZCartItem item : cart) {
			cartQty += item.getCartItemQty();			
		}		
		return cartQty;
	}

	@Override
	public double getCartTax() {
		double cartTax = 0.0;
		double cartSub = this.getCartSubtotal();
		cartTax = cartSub * STATE_TAX_PERCENT / 100;
		return cartTax;
	}

	@Override
	public double getCartShippingCost() {
		double shippingCost = 0.0;
		if (this.getCartQty() >= SHIPPING_QTY_THRESHOLD) {
				shippingCost = SHIPPING_COST_OVER_THRESHOLD;
			} else {
				shippingCost = SHIPPING_COST_UNDER_THRESHOLD;
			}
		return shippingCost;
	}
	
	
	@Override
	public void setCartTax(int taxrate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCartShippingCost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getCartTotal() {
		double cartTotal = this.getCartSubtotal() + this.getCartTax() + this.getCartShippingCost();
		return cartTotal;
	}



}
