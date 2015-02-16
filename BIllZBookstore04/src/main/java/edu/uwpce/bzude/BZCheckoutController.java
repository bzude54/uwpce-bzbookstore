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
		
		@RequestMapping(value = "/showcheckout/{cartid}", method = RequestMethod.GET)
		public String showcheckout(HttpSession session, @PathVariable("cartid") int cartid, Model model ){
			
			System.out.println("in showcart, cartid is: " + cartid);
			String username = (String) session.getAttribute("username");
			System.out.println("in showcart, username is: " + username);
			int userId = (Integer) session.getAttribute("userId");
			bzcart = (BZSimpleCart) session.getAttribute("bzcart");
			System.out.println("in showcart, bzcart id is: " + bzcart.getCartId() + " and qty is: " + bzcart.getCartQty());
			bzuserinfo = userManager.getSingleUser(username);
			System.out.println("in showcart, bzuserinfo has username: " + bzuserinfo.getUserName());
			
			bzcheckoutinfo = new BZCheckoutInfo(bzcart, bzuserinfo);
			System.out.println("bzcheckoutinfo has username: " + bzcheckoutinfo.getUserInfo().getUserName());
			checkoutManager.setCheckoutinfo(bzcheckoutinfo);
			if (!checkoutManager.validShippingAddress() || !checkoutManager.validCreditCard()) {
				return "redirect:/bzaccountinfo/{username}";
			} else {
				model.addAttribute("bZCheckoutInfo", bzcheckoutinfo);
				return "redirect:/bzcheckout";
			}			
			
//			return new ModelAndView("bzcheckout", "bZCheckoutInfo", bzcheckoutinfo);
		}


}
