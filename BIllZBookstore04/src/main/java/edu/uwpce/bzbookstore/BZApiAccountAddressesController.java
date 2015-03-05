package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

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
@RequestMapping("/api/accounts/{userid}")
public class BZApiAccountAddressesController {
   
    @Autowired
    private BZUsersManager usersManager;
    
    @Autowired
    private BZAddressesManager addressesManager;
        
    
    @RequestMapping(value="/addresses", method=RequestMethod.GET)
    public List<BZAddress> getAddresses(){
       	return addressesManager.getAddresses();
    }
    
    
    @RequestMapping(value="/addresses/{addressid}", method=RequestMethod.GET)
    public BZAddress getAddress(HttpServletResponse response, @PathVariable("addressid") String addressid) {
    	return addressesManager.getAddress(addressid);
    }
    
    
    @RequestMapping(value="/addresses/{addressid}", method=RequestMethod.POST)
    public Object createUser(@RequestBody BZAddress newaddress, @PathVariable("addressid") String addressid, HttpServletResponse response) {
		if (addressesManager.getAddress(addressid) != null) {
            return new BZApiMessage(MsgType.ERROR, "Address of type: " + addressid + " already exists.");
		} else {
			addressesManager.addAddress(newaddress);	
	        response.setStatus(HttpServletResponse.SC_CREATED);
			return addressesManager.getAddress(addressid);
		}
    }
    
    
    @RequestMapping(value="/addresses/{addressid}", method=RequestMethod.PUT)
    public BZAddress updateAddress(@RequestBody BZAddress newaddress, @PathVariable("addressid") String addressid){
    	addressesManager.updateAddress(newaddress);
    	return addressesManager.getAddress(addressid);
    
    }
    
    
    @RequestMapping(value="/addresses/{addressid}", method=RequestMethod.DELETE)
    public Object deleteUser(@PathVariable("addressid") String addressid, HttpServletResponse response) {
    	if (addressesManager.deleteAddress(addressid)){
    		return new BZApiMessage(MsgType.INFO, "Address of type: " + addressid + " has been deleted.");
    	} else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new BZApiMessage(MsgType.ERROR, "Address of type: " + addressid + " was not found.");
    	}
    }    
    
  
}
