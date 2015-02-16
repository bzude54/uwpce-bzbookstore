package edu.uwpce.bzude;

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
public class BZCheckoutController {
	
	   private static final Logger logger = LoggerFactory.getLogger(BZCheckoutController.class);
		
		private BZCheckoutManager checkoutManager = new BZSimpleCheckoutManager();
		private BZUserManager userManager = new BZSimpleUserManager();
		private BZCartManager cartManager = new BZSimpleCartManager();
		private BZCart bzcart = new BZSimpleCart();	
		private BZUserInfo bzuserinfo = new BZUserInfo();
		private BZCheckoutInfo bzcheckoutinfo = new BZCheckoutInfo();
		
		@Resource(name="simpleUserManager")
		public void setUserManager(BZUserManager usermanager) {
			this.userManager = usermanager;
		}
		
		@RequestMapping(value = "/showcheckout/{cartid}", method = RequestMethod.GET)
		public ModelAndView showcheckout(HttpSession session, @PathVariable("cartid") int cartid, @ModelAttribute BZCheckoutInfo bzcheckoutinfo ){

			String username = (String) session.getAttribute("username");
			bzcart = cartManager.getSingleCart(cartid);
			bzuserinfo = userManager.getSingleUser(username);
			
			bzcheckoutinfo = new BZCheckoutInfo(bzcart, bzuserinfo);
			checkoutManager.setCheckoutinfo(bzcheckoutinfo);
/*			if (!checkoutManager.validShippingAddress() || !checkoutManager.validCreditCard()) {
				return "redirect:/bzaccountinfo/{cartid}";
			} else {
				return "bzcheckout";
			}
*/			
			
			return new ModelAndView("bzcheckout", "bzcheckoutinfo", bzcheckoutinfo);
		}


}
