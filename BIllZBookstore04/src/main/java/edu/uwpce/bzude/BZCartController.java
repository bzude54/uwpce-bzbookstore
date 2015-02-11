package edu.uwpce.bzude;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BZCartController {
	
    private static final Logger logger = LoggerFactory.getLogger(BZCartController.class);
	
	private BZCartManager cartManager = new BZSimpleCartManager();;
	private BZBookManager bookManager;
	private BZCart cart = new BZSimpleCart();
//	private BZCartItem cartItem;
	
	
	@Resource(name="simpleBookManager")
	public void setBookManager(BZBookManager bookManager) {
		this.bookManager = bookManager;
	}

	
	@RequestMapping(value = "/addtocart/{bookid}", method = RequestMethod.GET)
	public String addItemToCart(HttpSession session, @PathVariable("bookid") String bookid){

		System.out.println("addItemTOCart bookid: " + bookid);
		int userId = (Integer) session.getAttribute("userId");
		System.out.println("userId is: " + userId);
		cart = cartManager.getSingleCart(userId);
		BZCartItem cartItem = new BZSimpleCartItem();
		if (cart != null) {
			if (cart.getSingleCartItem(bookid) != null){
				cartItem = cart.getSingleCartItem(bookid);
				cartItem.setCartItemQty(cartItem.getCartItemQty() + 1);
			} else {
				System.out.println("cartItem IS null!");
				cartItem = new BZSimpleCartItem(bookManager.getSingleBook(bookid));
				System.out.println("cartItem has an id: " + cartItem.getCartItemBook().getISBN());
	    		cart.setSingleCartItem(cartItem);
			}
		} else {
			System.out.println("getCart and cartItem IS null!");
			cart = new BZSimpleCart(userId);
			cartItem = new BZSimpleCartItem(bookManager.getSingleBook(bookid));
			System.out.println("cartItem has an id: " + cartItem.getCartItemBook().getISBN());
    		cart.setSingleCartItem(cartItem);
    		cartManager.setSingleCart(cart);
    	}

		logger.info("cartItem - " + cartItem.toString());
		System.out.println("added book to cart for: " + userId + " belonging to: " + cart.getCartId());		
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
		
		int userId = (Integer) session.getAttribute("userId");
		BZCart cart = cartManager.getSingleCart(userId);
		if (cart != null) {
			System.out.println("cartID in showcart is: " + cart.getCartId());
		}
		int cartQty = 0;
		String booktitle;
/*		for (Map.Entry<String, BZCartItem> entry : cart.getCart().entrySet()) {
			System.out.println("book ISBN in cart: " + entry.getValue().getCartItemBook().getISBN() + " qty: " + entry.getValue().getCartItemQty());
		}
*/
/*		model.addAttribute("cart", cart);
		model.addAttribute("cartMap", cart.getCart());
*/
		session.setAttribute("cart", cart);
		
		return new ModelAndView("bzcart", "cartinfo", cart);
	}
	
	@RequestMapping(value = "/bzcart", method = RequestMethod.POST)
	public ModelAndView updateCart(HttpSession session, @ModelAttribute("cartinfo") BZSimpleCart cart ) {
		
		System.out.println("returning from cart");
		BZCart checkcart = (BZCart) session.getAttribute("cart");
		System.out.println("cartID: " + cart.getCartId());
		for (Map.Entry<String, BZCartItem> entry : cart.getCart().entrySet()) {
			System.out.println("book ISBN in cart: " + entry.getValue().getCartItemBook().getISBN() + " qty: " + entry.getValue().getCartItemQty());
		}

		
		return new ModelAndView("bzcart", "cartinfo", cart);
	}


}
