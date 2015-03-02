package edu.uwpce.bzbookstore;

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
    
    List<BZSimpleCartItem> cartItems;
	int cartId;

	public BZSimpleCart(){
		cartItems = new ArrayList<BZSimpleCartItem>();
	}
	
	public BZSimpleCart(int userId) {
		cartItems = new ArrayList<BZSimpleCartItem>();
		this.cartId = userId;
	}
	
	@Override
	public List<BZSimpleCartItem> getCartItems() {
		return cartItems;
	}

	@Override
	public void setCartItems(List<BZSimpleCartItem> cart) {
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
	public BZSimpleCartItem getSingleCartItem(String itemId) {
		logger.info("in getsinglecartitem itemId is: " + itemId);
		BZSimpleCartItem cartItem = null;
		if (itemId != null) {
			for (BZSimpleCartItem item : cartItems) {
				if (itemId.equals(item.getCartItemBook().getISBN())) {
					cartItem = item;
					logger.info("in getsinglecartitem found cartitem matching itemId: " + item.getCartItemBook().getISBN());
				}
			}
		}
		return cartItem;
	}

	@Override
	public void setSingleCartItem(BZSimpleCartItem item) {
		if (item != null) {
			logger.info("adding BZCartItem to BZCart!");
			cartItems.add(item);
			logger.info("added: " + item.getCartItemBook().getISBN());
		}
	}

	@Override
	public int getCartItemQty(String itemId) {
		int itemQty = 0;
		if (itemId != null) {
			for (BZCartItem item : cartItems) {
				if (itemId.equals(item.getCartItemBook().getISBN())) {
					itemQty = item.getCartItemQty();
				}
			}
		}
		return itemQty;
	}

	@Override
	public void setCartItemQty(String itemId, int qty) {
		BZSimpleCartItem cartItem = (BZSimpleCartItem) this.getSingleCartItem(itemId);
		if (qty == 0){
			cartItems.remove(cartItem);
		} else {
			cartItem.setCartItemQty(qty);
		}
	}

	@Override
	public double getCartSubtotal() {
		double cartSubTotal = 0.0;
		for (BZCartItem item : cartItems) {
			cartSubTotal += item.getCartItemTotalPrice();			
		}		
		return cartSubTotal;
	}

	@Override
	public int getCartQty() {
		int cartQty = 0;
		for (BZCartItem item : cartItems) {
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
