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
			
//			logger.info("in showcheckout, cartid is: " + cartid);
			String username = (String) session.getAttribute("username");
//			logger.info("in showcheckout, username is: " + username);
			int userid = (Integer) session.getAttribute("userid");
			bzcart = (BZSimpleCart) session.getAttribute("bzcart");
//			logger.info("in showcheckout, bzcart id is: " + bzcart.getCartId() + " and qty is: " + bzcart.getCartQty());
			bzuserinfo = userManager.getSingleUser(userid);
//			logger.info("in showcheckout, bzuserinfo has username: " + bzuserinfo.getUserName());
			bzcheckoutinfo = new BZCheckoutInfo(bzcart, bzuserinfo);
//			logger.info("bzcheckoutinfo has username: " + bzcheckoutinfo.getUserInfo().getUserName());
			checkoutManager.setCheckoutinfo(bzcheckoutinfo);
			session.setAttribute("checkout", true);
			session.setAttribute("numCartItems", bzcart.getCartQty());

			return new ModelAndView("bzcheckout", "bZCheckoutInfo", bzcheckoutinfo);
		}

		@RequestMapping(value = "/bzcheckout/{cartid}", method = RequestMethod.POST)
		public String processCheckout(HttpSession session, @ModelAttribute BZCheckoutInfo bZCheckoutInfo, @PathVariable("cartid") int cartid, Model model) {

			String username = (String) session.getAttribute("username");
//			logger.info("bzcheckoutinfo has username: " + bzcheckoutinfo.getUserInfo().getUserName());
			int userid = (Integer) session.getAttribute("userid");
			BZCheckoutInfo checkoutinfo = checkoutManager.getCheckoutinfo();
			if (checkoutinfo != null) {
				checkoutinfo.setUserStreetAddress(bZCheckoutInfo.getUserStreetAddress());
				checkoutinfo.setUserCity(bZCheckoutInfo.getUserCity());
				checkoutinfo.setUserState(bZCheckoutInfo.getUserState());
				checkoutinfo.setUserZip(bZCheckoutInfo.getUserZip());
				checkoutinfo.setUserCreditCard(bZCheckoutInfo.getUserCreditCard());
			}
			checkoutManager.setCheckoutinfo(checkoutinfo);
//			logger.info("shipping address is: " + checkoutManager.getCheckoutinfo().getUserStreetAddress());
//			logger.info("credit card1 is: " + checkoutManager.getCheckoutinfo().getUserCreditCard());
//			logger.info("validshippingaddress is: " + checkoutManager.validShippingAddress());
//			logger.info("validcreditcard is: " + checkoutManager.validCreditCard());
			
			session.setAttribute("numCartItems", bzcart.getCartQty());

			if (!(checkoutManager.validShippingAddress() && checkoutManager.validCreditCard())) {
				return "redirect:/bzaccountinfo/" + userid;
			} else {
				model.addAttribute("bZCheckoutInfo", checkoutManager.getCheckoutinfo());
				return "redirect:/bzcheckout/" + userid;
			}			
		}
		
		
		@RequestMapping(value = "/bzthankyou", method = RequestMethod.GET)
		public String confirmCheckout(HttpSession session) {
			
			int userid = (Integer) session.getAttribute("userid");

			if (!(checkoutManager.validShippingAddress() && checkoutManager.validCreditCard())) {
				return "redirect:/bzaccountinfo/" + userid;
			} else {
				return "bzthankyou";
			}
		}

}
