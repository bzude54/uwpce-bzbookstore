package edu.uwpce.bzude;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BZUserInfoController {
	
	private BZUserManager userManager;
	
	@Resource(name="simpleUserManager")
	public void setUserManager(BZUserManager usermanager) {
		this.userManager = usermanager;
	}

    @RequestMapping(value = "/bzlogin", method = RequestMethod.GET)
    public ModelAndView displayLoginForm() {
    	
    	
        return new ModelAndView("bzlogin", "loginUserInfo", new BZUserInfo()); 
    }

    @RequestMapping(value = "/bzlogin", method = RequestMethod.POST)
    public String processLogin(HttpSession session,
                               @ModelAttribute BZUserInfo loginUserInfo) {
    	
    	String username = loginUserInfo.getUserName();
    	int userid = 0;
    	session.setAttribute("checkout", false);
    	session.setAttribute("numCartItems", 0);

 //   	System.out.println("in bzlogin");
  
    	Map<Integer, BZUserInfo> users = userManager.getUsers();
    	
    	if (users != null) {
    	   	for (BZUserInfo user: users.values()) {
        		if (user.getUserName().equals(username)) {
        			userid = user.getUserId();
        		}
           	}    		
    	}
    	
    	if (userid != 0) {
            session.setAttribute("username", users.get(userid).getUserName());
            session.setAttribute("userid", users.get(userid).getUserId());
//            System.out.println("userid after login is: " + users.get(userid).getUserId());
           return "redirect:/bzbooks";
    		
    	} else {

    		return "redirect:/bzregister";
    	}
    }

    @RequestMapping(value = "/bzregister", method = RequestMethod.GET)
    public ModelAndView displayRegisterForm() {

        // This is a shortcut if we only have one attribute to put in our model
        return new ModelAndView("bzregister", "newUserInfo", new BZUserInfo()); 
    }

    @RequestMapping(value = "/bzregister", method = RequestMethod.POST)
    public String processRegister(HttpSession session,
                               @ModelAttribute BZUserInfo user) {

    	userManager.setSingleUser(user);
        session.setAttribute("username", user.getUserName());
        session.setAttribute("userid", user.getUserId());
 //     System.out.println("new userid after register is: " + user.getUserId());
        return "redirect:/bzlogin";
    }
    
    @RequestMapping(value = "/bzaccountinfo/{userid}", method = RequestMethod.GET)
    public ModelAndView displayAccountInfoForm(HttpSession session, Model model, @PathVariable("userid") int userid) {
    	
    	BZUserInfo accountUserInfo = new BZUserInfo();
    	accountUserInfo = userManager.getSingleUser(userid);
//    	 System.out.println("in bzacctinfo GET, userid is: " + userid);
//     	 System.out.println("in bzacctinfo GET, username is: " + accountUserInfo.getUserName());
     	 if (accountUserInfo != null) {
    	       session.setAttribute("bzuserinfo", accountUserInfo);   	
    	} else {
    		accountUserInfo = new BZUserInfo();
    	}
        return new ModelAndView("bzaccountinfo", "BZUserInfo", accountUserInfo);    	
    	
    }
    
    
    @RequestMapping(value = "/bzaccountinfo/{userid}", method = RequestMethod.POST)
    public String processAccountInfoForm(HttpSession session, @ModelAttribute BZUserInfo accountUserInfo) {
    	
    	int userid = accountUserInfo.getUserId();
//		System.out.println("in bzacctinfo, userid is: " + userid);
    	String username = accountUserInfo.getUserName();
//    	 System.out.println("in bzacctinfo, username is: " + username);
    	
		boolean update = false;
		Map<Integer, BZUserInfo> users = userManager.getUsers();
		
		BZUserInfo user = userManager.getSingleUser(userid);
		
		if (user != null) {
//			 System.out.println("in bzacctinfo, username back from usermananger is: " + user.getUserName());
			user.setFirstName(accountUserInfo.getFirstName());
			user.setLastName(accountUserInfo.getLastName());
			user.setUserName(accountUserInfo.getUserName());
			user.setPassword(accountUserInfo.getPassword());
			session.setAttribute("password", user.getPassword());
			user.setEmailAddress(accountUserInfo.getEmailAddress());
			session.setAttribute("emailaddress", user.getEmailAddress());
			user.setMailingStreetAddress(accountUserInfo.getMailingStreetAddress());
			session.setAttribute("mailingstreet",
					accountUserInfo.getMailingStreetAddress());
			user.setMailingCity(accountUserInfo.getMailingCity());
			session.setAttribute("mailingcity", user.getMailingCity());
			user.setMailingState(accountUserInfo.getMailingState());
			session.setAttribute("mailingstate", user.getMailingState());
			user.setMailingZip(accountUserInfo.getMailingZip());
			session.setAttribute("mailingzip", user.getMailingZip());    	
			user.setShippingStreetAddress(accountUserInfo.getShippingStreetAddress());
			session.setAttribute("shippingstreet",
					accountUserInfo.getShippingStreetAddress());
			user.setShippingCity(accountUserInfo.getShippingCity());
			session.setAttribute("shippingcity", user.getShippingCity());
			user.setShippingState(accountUserInfo.getShippingState());
			session.setAttribute("shippingstate", user.getShippingState());
			user.setShippingZip(accountUserInfo.getShippingZip());
			session.setAttribute("shippingzip", user.getShippingZip());    	
			user.setPhoneNumber1(accountUserInfo.getPhoneNumber1());
			session.setAttribute("phonenumber1", user.getPhoneNumber1());
			user.setPhoneNumber2(accountUserInfo.getPhoneNumber2());
			session.setAttribute("phonenumber2", user.getPhoneNumber2());
			user.setCreditCard1(accountUserInfo.getCreditCard1());
			session.setAttribute("creditcard1", user.getCreditCard1());
			user.setCreditCard2(accountUserInfo.getCreditCard2());
			session.setAttribute("creditcard2", user.getCreditCard2());

			return "redirect:/bzaccountinfo/" + userid;
		} else {
			return "redirect:/bzregister";
		}
		
		
    }
     
    

    @RequestMapping(value = "/bzlogout", method = RequestMethod.GET)
    public String logout(HttpSession session) {

        session.removeAttribute("username");
        return "redirect:";
    }
}
