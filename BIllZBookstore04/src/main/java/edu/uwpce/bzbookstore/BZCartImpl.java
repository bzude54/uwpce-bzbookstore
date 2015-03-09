package edu.uwpce.bzbookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZCartImpl implements BZCart {
	
    private static final Logger logger = LoggerFactory.getLogger(BZCartImpl.class);
    
    private static final int STATE_TAX_PERCENT = 8;
	private static final double SHIPPING_COST_UNDER_THRESHOLD = 5.00;
	private static final double SHIPPING_COST_OVER_THRESHOLD = 15.00;
	private static final int SHIPPING_QTY_THRESHOLD = 5;
    
    Map<String, BZCartItem> cartItems;
	int cartId;

	public BZCartImpl(){
		cartItems = new ConcurrentHashMap<String, BZCartItem>();
	}
	
	public BZCartImpl(int userId) {
		cartItems = new ConcurrentHashMap<String, BZCartItem>();
		this.cartId = userId;
	}
	
	@Override
	public Map<String, BZCartItem> getCartItems() {
		return cartItems;
	}

	@Override
	public void setCartItems(Map<String, BZCartItem> cart) {
		this.cartItems = cart;
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
		logger.info("in getsinglecartitem itemId is: " + itemId);
		BZCartItem cartItem = cartItems.get(itemId);
		return cartItem;
	}

	@Override
	public void setSingleCartItem(BZCartItem cartitem) {
		if (cartItems.get(cartitem.getCartItemId()) == null) {
			logger.info("adding BZCartItem to BZCart!");
			cartItems.put(cartitem.getCartItemId(), cartitem);
			logger.info("added: " + cartItems.get(cartitem.getCartItemId()));
		} else {
			cartItems.get(cartitem.getCartItemId()).incrementCartItemQty();
		}
	}

	@Override
	public int getCartItemQty(String itemId) {
		return cartItems.get(itemId).getCartItemQty();
	}

	@Override
	public void setCartItemQty(String itemId, int qty) {
		if (qty == 0){
			cartItems.remove(itemId);
		} else {
			cartItems.get(itemId).setCartItemQty(qty);
		}
	}

	@Override
	public double getCartSubtotal() {
		double cartSubTotal = 0.0;
		for (BZCartItem item : cartItems.values()) {
			cartSubTotal += item.getCartItemTotalPrice();			
		}		
		return cartSubTotal;
	}

	@Override
	public int getCartQty() {
		int cartQty = 0;
		for (BZCartItem item : cartItems.values()) {
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
		logger.info("in get CartShippingCost cart qty is: " + this.getCartQty());
		if (this.getCartQty() > 0) {
			if (this.getCartQty() >= SHIPPING_QTY_THRESHOLD) {
				shippingCost = SHIPPING_COST_OVER_THRESHOLD;
			} else {
				shippingCost = SHIPPING_COST_UNDER_THRESHOLD;
			}
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
