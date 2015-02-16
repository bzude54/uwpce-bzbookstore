package edu.uwpce.bzude;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZSimpleCartManager implements BZCartManager {

    private static final Logger logger = LoggerFactory.getLogger(BZSimpleCartManager.class);

    
    private static final int STATE_TAX_PERCENT = 8;
	private static final double SHIPPING_COST_UNDER_THRESHOLD = 5.00;
	private static final double SHIPPING_COST_OVER_THRESHOLD = 15.00;
	private static final int SHIPPING_QTY_THRESHOLD = 5;

	private Map<Integer, BZCart> carts;
	
	public BZSimpleCartManager() {
		carts = new ConcurrentHashMap<Integer, BZCart>();
	}

	@Override
	public Map<Integer, BZCart> getCarts() {
		return this.carts;
	}

	@Override
	public void setCarts(Map<Integer, BZCart> carts) {
		this.carts = carts;
	}

	@Override
	public void setSingleCart(BZCart cart) {
		if (cart != null) {
			carts.put(cart.getCartId(), cart);
			logger.info("Successfully added new cart to Cart Manager");
		} else {
			logger.error("The cart sent to the Cart Manager is null!");
		}
	}

	@Override
	public BZCart getSingleCart(int userId) {
		BZCart cart = carts.get(userId);
		if (cart == null) {
			cart = new BZSimpleCart(userId);
			carts.put(userId, cart);
		}
		return cart;
	}

	@Override
	public double getCartTax(int cartId) {
		double cartTax = 0.0;
		double cartSubTotal = 0.0;
		BZCart cart = carts.get(cartId);
		if (cart != null) {
			cartSubTotal = cart.getCartSubtotal();
			cartTax = cartSubTotal * STATE_TAX_PERCENT / 100;			
		} else {
			logger.error("There is no cart with this id!");
		}
		return cartTax;
	}
	
	@Override
	public double getCartShippingCost(int cartId) {
		double shippingCost = 0.0;
		BZCart cart = carts.get(cartId);
			if (cart.getCartQty() >= SHIPPING_QTY_THRESHOLD) {
				shippingCost = SHIPPING_COST_OVER_THRESHOLD;
			} else {
				if (cart.getCartQty() > 0) {
					shippingCost = SHIPPING_COST_UNDER_THRESHOLD;					
				} 
			}
		return shippingCost;
	}


}
