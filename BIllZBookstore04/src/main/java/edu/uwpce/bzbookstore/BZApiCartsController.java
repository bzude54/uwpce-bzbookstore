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
    private BZCartsManager cartsManager;
    
    @RequestMapping(value="/carts", method=RequestMethod.GET)
    public Map<Integer, BZCart> getAllCarts() {
        return cartsManager.getAllCarts();
    }

   
    @RequestMapping(value="/carts/{cartid}", method=RequestMethod.GET)
    public Object getCart(@PathVariable("userid") int userid) {
        BZCart cart = cartsManager.getCart(userid);
        if (cart != null) {
            return cart;
        } else {
            return new BZApiMessage(MsgType.ERROR, "Cart with cart id: " + userid + " does not exist.");
        }
    }

    
    @RequestMapping(value="/carts", method=RequestMethod.POST)
    public Object createCart(@RequestBody BZCart cart, HttpServletResponse response) {
        if (cartsManager.getCart(cart.getCartId()) != null) {
            return new BZApiMessage(MsgType.ERROR, "Cart with id: " + cart.getCartId() + " already exists.");
        }
        cartsManager.setCart(cart);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return cartsManager.getCart(cart.getCartId());
    }
    
    
    @RequestMapping(value="/carts/{cartid}", method=RequestMethod.PUT)
    public BZCart updateCart(@RequestBody BZCart cart) {
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
