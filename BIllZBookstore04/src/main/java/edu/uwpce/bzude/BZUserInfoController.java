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

        // This is a shortcut if we only have one attribute to put in our model
        return new ModelAndView("bzlogin", "loginUserInfo", new BZUserInfo()); 
    }

    @RequestMapping(value = "/bzlogin", method = RequestMethod.POST)
    public String processLogin(HttpSession session,
                               @ModelAttribute BZUserInfo loginUserInfo) {
    	
//    	String username = request.getParameter("userName");
    	String username = loginUserInfo.getUserName();

  
    	Map<String, BZUserInfo> users = userManager.getUsers();
    	if (users != null && users.containsKey(username)) {
            session.setAttribute("username", users.get(username).getUserName());
            session.setAttribute("userId", users.get(username).getUserId());
            System.out.println("userId after login is: " + users.get(username).getUserId());
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

//    	BZUserInfo newuser = new BZUserInfo();
//    	newuser.setUserName(request.getParameter("userName"));
//    	newuser.setPassword(request.getParameter("password"));
    	userManager.setSingleUser(user);
        session.setAttribute("username", user.getUserName());
        session.setAttribute("userId", user.getUserId());
        System.out.println("new userId after register is: " + user.getUserId());
        return "redirect:/bzlogin";
    }
    
    @RequestMapping(value = "/bzaccountinfo/{username}", method = RequestMethod.GET)
    public ModelAndView displayAccountInfoForm(HttpSession session, Model model, @PathVariable("username") String username) {
    	
    	BZUserInfo accountUserInfo = new BZUserInfo();
    	accountUserInfo = userManager.getSingleUser(username);
    	if (accountUserInfo != null) {
    	       session.setAttribute("useraccountinfo", accountUserInfo);   	
    			model.addAttribute("acctUserInfo",  accountUserInfo);
    	} else {
    		accountUserInfo = new BZUserInfo();
    	}
        return new ModelAndView("bzaccountinfo", "acctUserInfo", accountUserInfo);    	
    	
    }
    
    
    @RequestMapping(value = "/bzaccountinfo/{usersname}", method = RequestMethod.POST)
    public String processAccountInfoForm(HttpSession session, @ModelAttribute BZUserInfo accountUserInfo) {
    	
    	int userId = accountUserInfo.getUserId();
    	String username = accountUserInfo.getUserName();
    	
		boolean update = false;
		Map<String, BZUserInfo> users = userManager.getUsers();
		
		BZUserInfo user = userManager.getSingleUser(username);
		
		if (user != null) {
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

			return "redirect:/bzaccountinfo/{userId}";
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
