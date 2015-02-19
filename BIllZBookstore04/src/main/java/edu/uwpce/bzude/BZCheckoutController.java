package edu.uwpce.bzude;

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
public class BZCheckoutController {
	
	   private static final Logger logger = LoggerFactory.getLogger(BZCheckoutController.class);
		
		private BZCheckoutManager checkoutManager = new BZSimpleCheckoutManager();
		private BZUserManager userManager = new BZSimpleUserManager();
		private BZCartManager cartManager = new BZSimpleCartManager();
		private BZSimpleCart bzcart = new BZSimpleCart();	
		private BZUserInfo bzuserinfo = new BZUserInfo();
		private BZCheckoutInfo bzcheckoutinfo = new BZCheckoutInfo();
		
		@Resource(name="simpleUserManager")
		public void setUserManager(BZUserManager usermanager) {
			this.userManager = usermanager;
		}
		
		@RequestMapping(value = "/bzcheckout/{cartid}", method = RequestMethod.GET)
		public ModelAndView showCheckout(HttpSession session, @PathVariable("cartid") int cartid, Model model ){
			
//			System.out.println("in showcheckout, cartid is: " + cartid);
			String username = (String) session.getAttribute("username");
//			System.out.println("in showcheckout, username is: " + username);
			int userid = (Integer) session.getAttribute("userid");
			bzcart = (BZSimpleCart) session.getAttribute("bzcart");
//			System.out.println("in showcheckout, bzcart id is: " + bzcart.getCartId() + " and qty is: " + bzcart.getCartQty());
			bzuserinfo = userManager.getSingleUser(userid);
//			System.out.println("in showcheckout, bzuserinfo has username: " + bzuserinfo.getUserName());
			bzcheckoutinfo = new BZCheckoutInfo(bzcart, bzuserinfo);
//			System.out.println("bzcheckoutinfo has username: " + bzcheckoutinfo.getUserInfo().getUserName());
			checkoutManager.setCheckoutinfo(bzcheckoutinfo);
			session.setAttribute("checkout", true);
			session.setAttribute("numCartItems", bzcart.getCartQty());

			return new ModelAndView("bzcheckout", "bZCheckoutInfo", bzcheckoutinfo);
		}

		@RequestMapping(value = "/bzcheckout/{cartid}", method = RequestMethod.POST)
		public String processCheckout(HttpSession session, @ModelAttribute BZCheckoutInfo bzcheckoutInfo, Model model) {

			String username = (String) session.getAttribute("username");
//			System.out.println("bzcheckoutinfo has username: " + bzcheckoutinfo.getUserInfo().getUserName());
			int userid = (Integer) session.getAttribute("userid");
			checkoutManager.setCheckoutinfo(bzcheckoutinfo);
/*			System.out.println("shipping address is: " + checkoutManager.getCheckoutinfo().getUserStreetAddress());
			System.out.println("credit card1 is: " + checkoutManager.getCheckoutinfo().getUserCreditCard());
			System.out.println("validshippingaddress is: " + checkoutManager.validShippingAddress());
			System.out.println("validcreditcard is: " + checkoutManager.validCreditCard());
*/			
			session.setAttribute("numCartItems", bzcart.getCartQty());

			if (!(checkoutManager.validShippingAddress() && checkoutManager.validCreditCard())) {
//				model.addAttribute("userid", userid);
				return "redirect:/bzaccountinfo/" + userid;
			} else {
				model.addAttribute("bZCheckoutInfo", bzcheckoutinfo);
				return "bzthankyou";
			}			
		}

}
