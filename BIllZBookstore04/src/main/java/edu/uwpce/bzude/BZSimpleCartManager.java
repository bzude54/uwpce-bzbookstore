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
	public double getCartShippingCost(int cartId) {
		double shippingCost = 0.0;
		if ((carts != null) && (carts.containsKey(cartId))) {
			if (carts.get(cartId).getCartQty() >= SHIPPING_QTY_THRESHOLD) {
				shippingCost = SHIPPING_COST_OVER_THRESHOLD;
			} else {
				shippingCost = SHIPPING_COST_UNDER_THRESHOLD;
			}
		}
		return shippingCost;
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
	public BZCart getSingleCart(int cartId) {
		BZCart cart = null;
		if ((carts != null) && (carts.containsKey(cartId))) {
			cart = carts.get(cartId);
		}
		return cart;
	}

	@Override
	public double getCartTax(int cartId) {
		double cartTax = 0.0;
		double cartSub = this.getSingleCart(cartId).getCartSubtotal();
		cartTax = cartSub * STATE_TAX_PERCENT / 100;
		return cartTax;
	}

}
