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

	private static final Logger logger = LoggerFactory
			.getLogger(BZCartController.class);

	private BZCartManager cartManager = new BZSimpleCartManager();;
	private BZBookManager bookManager;
	private BZCart bzcart = new BZSimpleCart();

	// private BZCartItem cartItem;

	@Resource(name = "simpleBookManager")
	public void setBookManager(BZBookManager bookManager) {
		this.bookManager = bookManager;
	}

	@RequestMapping(value = "/addtocart/{bookid}", method = RequestMethod.GET)
	public String addItemToCart(HttpSession session,
			@PathVariable("bookid") String bookid) {

		System.out.println("addItemTOCart bookid: " + bookid);
		int userid = (Integer) session.getAttribute("userid");
		System.out.println("userid is: " + userid);
		bzcart = cartManager.getSingleCart(userid);
		BZSimpleCartItem cartItem = bzcart.getSingleCartItem(bookid);
		if (cartItem != null) {
			cartItem.incrementCartItemQty();
		} else {
			System.out.println("cartItem IS null!");
			cartItem = new BZSimpleCartItem(bookManager.getSingleBook(bookid));
			System.out.println("cartItem has an id: "
					+ cartItem.getCartItemId());
			bzcart.setSingleCartItem(cartItem);
		}

		logger.info("cartItem - " + cartItem.toString());
		System.out.println("added book to cart for: " + userid
				+ " belonging to: " + bzcart.getCartId());
		session.setAttribute("addcartitem", cartItem);

		return "redirect:/confirmcartadd";
	}

	@RequestMapping(value = "/confirmcartadd", method = RequestMethod.GET)
	public ModelAndView showConfirmation(HttpSession session) {

		BZCartItem cartItem = (BZCartItem) session.getAttribute("addcartitem");
		return new ModelAndView("bzconfirmcartadd", "itemBookTitle", cartItem
				.getCartItemBook().getTitle());
	}

	@RequestMapping(value = "/bzcart", method = RequestMethod.GET)
	public ModelAndView showCart(HttpSession session) {

		int userid = (Integer) session.getAttribute("userid");
		BZSimpleCart bzcart = (BZSimpleCart) cartManager.getSingleCart(userid);
		if (bzcart != null) {
			System.out.println("cartID in showcart is: " + bzcart.getCartId());
			for (BZCartItem item : bzcart.getCartItems()) {
				System.out.println("itemID in showcart is: " + item.getCartItemId());
			}
		}

		/*
		 * for (Map.Entry<String, BZCartItem> entry : cart.getCart().entrySet())
		 * { System.out.println("book ISBN in cart: " +
		 * entry.getValue().getCartItemBook().getISBN() + " qty: " +
		 * entry.getValue().getCartItemQty()); }
		 */
		/*
		 * model.addAttribute("cart", cart); model.addAttribute("cartMap",
		 * cart.getCart());
		 */
		session.setAttribute("bzcart", bzcart);

		return new ModelAndView("bzcart", "bZSimpleCart", bzcart);
	}

	@RequestMapping(value = "/bzcart", method = RequestMethod.POST)
	public String updateCart(HttpSession session,
			@ModelAttribute BZSimpleCart bzcart) {

		System.out.println("returning from cart");
		BZSimpleCart checkcart = (BZSimpleCart) session.getAttribute("bzcart");
		System.out.println("checkcart: " + checkcart.getCartId());
		for (BZSimpleCartItem item : bzcart.getCartItems()) {
			System.out.println("book ISBN in cart: "
					+ item.getCartItemId() + " Total price: " + item.getCartItemTotalPrice() + " qty: "
					+ item.getCartItemQty());
            if (item.getCartItemQty() > 0) {
                checkcart.getSingleCartItem(item.getCartItemId()).setCartItemQty(item.getCartItemQty());
            } else {
                checkcart.setCartItemQty(item.getCartItemId(), 0);
            }
		}

		cartManager.setSingleCart(checkcart);
	        session.setAttribute("bzcart", checkcart);
	        
		return "redirect:bzcart";
	}

}
