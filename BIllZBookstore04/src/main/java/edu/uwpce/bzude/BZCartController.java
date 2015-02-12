package edu.uwpce.bzude;

import java.util.ArrayList;
import java.util.List;
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
	private BZCart bzcart = new BZSimpleCart();
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
		bzcart = cartManager.getSingleCart(userId);
		BZCartItem cartItem = new BZSimpleCartItem();
		if (bzcart != null) {
			if (bzcart.getSingleCartItem(bookid) != null){
				cartItem = bzcart.getSingleCartItem(bookid);
				cartItem.setCartItemQty(cartItem.getCartItemQty() + 1);
			} else {
				System.out.println("cartItem IS null!");
				cartItem = new BZSimpleCartItem(bookManager.getSingleBook(bookid));
				System.out.println("cartItem has an id: " + cartItem.getCartItemBook().getISBN());
	    		bzcart.setSingleCartItem(cartItem);
			}
		} else {
			System.out.println("getCart and cartItem IS null!");
			bzcart = new BZSimpleCart(userId);
			cartItem = new BZSimpleCartItem(bookManager.getSingleBook(bookid));
			System.out.println("cartItem has an id: " + cartItem.getCartItemBook().getISBN());
    		bzcart.setSingleCartItem(cartItem);
    		cartManager.setSingleCart(bzcart);
    	}

		logger.info("cartItem - " + cartItem.toString());
		System.out.println("added book to cart for: " + userId + " belonging to: " + bzcart.getCartId());		
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
		BZCart bzcart = cartManager.getSingleCart(userId);
		if (bzcart != null) {
			System.out.println("cartID in showcart is: " + bzcart.getCartId());
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
		session.setAttribute("bzcart", bzcart);
		
		return new ModelAndView("bzcart", "bzcart", bzcart);
	}
	
	@RequestMapping(value = "/bzcart", method = RequestMethod.POST)
	public ModelAndView updateCart(HttpSession session, @ModelAttribute BZSimpleCart bzcart ) {
		
		System.out.println("returning from cart");
//		BZCart checkcart = (BZCart) session.getAttribute("cart");
		System.out.println("cartID: " + bzcart.getCartId());
		for (BZCartItem item : bzcart.getCart()) {
			System.out.println("book ISBN in cart: " + item.getCartItemBook().getISBN() + " qty: " + item.getCartItemQty());
		}

		
		return new ModelAndView("bzcart", "cart", bzcart);
	}


}
