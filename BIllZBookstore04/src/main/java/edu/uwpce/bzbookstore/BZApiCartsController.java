package edu.uwpce.bzbookstore;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uwpce.bzbookstore.BZApiMessage.MsgType;

@RestController
@RequestMapping("/api/users/{userid}")
public class BZApiCartsController {
    
    @Autowired
    private BZBooksManager booksManager;
	
	@Autowired
    private BZCartsManager cartsManager;
    
    @RequestMapping(value="/carts", method=RequestMethod.GET)
    public BZCart getCart(@PathVariable("userid") int userid) {
        return cartsManager.getCart(userid);
    }

   
    @RequestMapping(value="/carts/{itemid}", method=RequestMethod.GET)
    public Object getCartItem(@PathVariable("userid") int userid, @PathVariable("itemid") String itemid) {
        BZCartItem cartitem = cartsManager.getCart(userid).getSingleCartItem(itemid);
        if (cartitem != null) {
            return cartitem;
        } else {
            return new BZApiMessage(MsgType.ERROR, "Cart item with item id: " + itemid + " does not exist.");
        }
    }

    
    @RequestMapping(value="/carts", method=RequestMethod.POST)
    public Object createCartItem(@RequestBody BZCartItem cartitem, @PathVariable("userid") int userid, HttpServletResponse response) {
        if (cartsManager.getCart(userid).getSingleCartItem(cartitem.getCartItemId()) != null) {
            return new BZApiMessage(MsgType.ERROR, "Cart item with id: " + cartitem.getCartItemId() + " already exists.");
        }
        cartsManager.getCart(userid).setSingleCartItem(cartitem);;
        response.setStatus(HttpServletResponse.SC_CREATED);
        return cartsManager.getCart(userid).getSingleCartItem(cartitem.getCartItemId());
    }
    
    
    @RequestMapping(value="/carts/{itemid}", method=RequestMethod.PUT)
    public BZCart updateCartItem(@RequestBody BZCartItem cartitem, @PathVariable("userid") int userid) {
    	cartsManager.updateCart(cart);
    	return cartsManager.getCart(cart.getCartId());
    }
    
    
    @RequestMapping(value="/carts/{cartid}", method=RequestMethod.DELETE)
    public Object deleteCart(@PathVariable("cartid") int cartid, HttpServletResponse response) {
    	if (cartsManager.deleteCart(cartid)) {
    		return new BZApiMessage(MsgType.INFO, "Cart with id: " + cartid + " has been deleted");
       	} else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new BZApiMessage(MsgType.ERROR, "Cart with id: " + cartid + " was not found.");
    	}
    }
    
    
}
