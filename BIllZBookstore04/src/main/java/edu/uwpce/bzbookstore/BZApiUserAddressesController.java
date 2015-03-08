package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uwpce.bzbookstore.BZApiMessage.MsgType;

@RestController
@RequestMapping("/api/users/{userid}")
public class BZApiUserAddressesController {
   
	@Autowired
    private BZUsersManager usersManager;
    
    @Autowired
    private BZAddressesManager addressesManager;
    
    private Map<String, BZAddress> addresses;
 	

 /*   public BZApiUserAddressesController() {
    	usersManager = new BZUsersManagerImpl();
    }
 */    
    
    
    @RequestMapping(value="/addresses", method=RequestMethod.GET)
    public Map<String, BZAddress> getAddresses(@PathVariable("userid") int userid){
    	int userID = 100;
    	addressesManager.setAddresses(usersManager.getSingleUserById(userID).getAddresses());
    	return addressesManager.getAddresses();
    }
    
    
    @RequestMapping(value="/addresses/{addressid}", method=RequestMethod.GET)
    public BZAddress getAddress(HttpServletResponse response, @PathVariable("userid") int userid, @PathVariable("addressid") String addressid) {
    	addressesManager.setAddresses(usersManager.getSingleUserById(userid).getAddresses());
    	return addressesManager.getAddress(addressid);
    }
    
    
    @RequestMapping(value="/addresses/{addressid}", method=RequestMethod.POST)
    public Object createAddress(@RequestBody BZAddress newaddress, @PathVariable("userid") int userid, @PathVariable("addressid") String addressid, HttpServletResponse response) {
    	addressesManager.setAddresses(usersManager.getSingleUserById(userid).getAddresses());
		if (addressesManager.getAddress(addressid) != null) {
            return new BZApiMessage(MsgType.ERROR, "Address of type: " + addressid + " already exists.");
		} else {
			addressesManager.addAddress(newaddress);
			usersManager.getSingleUserById(userid).setAddresses(addressesManager.getAddresses());
	        response.setStatus(HttpServletResponse.SC_CREATED);
			return addressesManager.getAddress(addressid);
		}
    }
    
    
    @RequestMapping(value="/addresses/{addressid}", method=RequestMethod.PUT)
    public BZAddress updateAddress(@RequestBody BZAddress newaddress, @PathVariable("userid") int userid, @PathVariable("addressid") String addressid){
    	addressesManager.setAddresses(usersManager.getSingleUserById(userid).getAddresses());
    	addressesManager.updateAddress(newaddress);
		usersManager.getSingleUserById(userid).setAddresses(addressesManager.getAddresses());
		return addressesManager.getAddress(addressid);
    
    }
    
    
    @RequestMapping(value="/addresses/{addressid}", method=RequestMethod.DELETE)
    public Object deleteAddress(@PathVariable("userid") int userid, @PathVariable("addressid") String addressid, HttpServletResponse response) {
    	addressesManager.setAddresses(usersManager.getSingleUserById(userid).getAddresses());
    	if (addressesManager.deleteAddress(addressid)){
    		usersManager.getSingleUserById(userid).setAddresses(addressesManager.getAddresses());
    		return new BZApiMessage(MsgType.INFO, "Address of type: " + addressid + " has been deleted.");
    	} else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new BZApiMessage(MsgType.ERROR, "Address of type: " + addressid + " was not found.");
    	}
    }    
    
  
}
