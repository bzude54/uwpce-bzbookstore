package edu.uwpce.bzude;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BZCartController {
	
    private static final Logger logger = LoggerFactory.getLogger(BZCartController.class);
	
	private BZCartManager cartManager;
	private BZBookManager bookManager;
	private Map<String, BZCartItem> cart;
//	private BZCartItem cartItem;
	
	@Resource(name="simpleCartManager")
	public void setCartManager(BZCartManager cartmanager) {
		this.cartManager = cartmanager;
	}
	
	@Resource(name="simpleBookManager")
	public void setBookManager(BZBookManager bookManager) {
		this.bookManager = bookManager;
	}

	
	@RequestMapping(value = "/addcart/{bookid}", method = RequestMethod.GET)
	public String addItemToCart(HttpSession session, @PathVariable("bookid") String bookid){
 
		System.out.println("addItemTOCart bookid: " + bookid);
		BZCartItem cartItem = cartManager.getCartItem(bookid);;
		if ((cartManager.getCart() != null) && (cartItem != null)){
			System.out.println("getCart is not null!");
    		cartItem.setCartItemQty(cartItem.getCartItemQty() + 1);
    	} else {
			System.out.println("getCart or cartItem IS null!");
			cartItem = new BZSimpleCartItem(bookManager.getSingleBook(bookid));
			System.out.println("cartItem has an id: " + cartItem.getCartItemBook().getISBN());

    		cartManager.setCartItem(cartItem);
    	}

		
		logger.info("cartItem - " + cartItem.toString());
		
		session.setAttribute("addcartitem", cartItem);
    	
    	return "redirect:/confirmcartadd";
    }
	
	@RequestMapping(value = "/confirmcartadd", method = RequestMethod.GET)
	public ModelAndView showConfirmation(HttpSession session) {
		
		BZCartItem cartItem = (BZCartItem) session.getAttribute("addcartitem");
		return new ModelAndView("bzconfirmcartadd", "itemBookTitle", cartItem.getCartItemBook().getTitle());
		
	}
	
	@RequestMapping(value = "/bzcart", method = RequestMethod.GET)
	public ModelAndView showCart(HttpSession session) {
		return new ModelAndView("bzcart", "cartItems", cartManager.getCart().values());
	}
	
	@RequestMapping(value = "/bzcart", method = RequestMethod.POST)
	public String updateCart(HttpSession session, @ModelAttribute BZSimpleCartManager cartItems ) {
		return "redirect:/bzcart";
	}


}
