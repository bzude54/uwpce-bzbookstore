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
public class BZApiAccountCreditCardsController {
   
    @Autowired
    private BZUsersManager usersManager;
    
    @Autowired
    private BZCreditCardsManager cardsManager;
        
    
    @RequestMapping(value="/cards", method=RequestMethod.GET)
    public List<BZCreditCard> getCards(){
       	return cardsManager.getCards();
    }
    
    
    @RequestMapping(value="/cards/{cardnum}", method=RequestMethod.GET)
    public BZCreditCard getCard(HttpServletResponse response, @PathVariable("cardnum") String cardnum) {
    	return cardsManager.getCard(cardnum);
    }
    
    
    @RequestMapping(value="/cards/{cardnum}", method=RequestMethod.POST)
    public Object createCard(@RequestBody BZCreditCard newcard, @PathVariable("cardnum") String cardnum, HttpServletResponse response) {
		if (cardsManager.getCard(cardnum) != null) {
            return new BZApiMessage(MsgType.ERROR, "Credit card with username= " + cardnum + " already exists.");
		} else {
			cardsManager.addCard(newcard);	
	        response.setStatus(HttpServletResponse.SC_CREATED);
			return cardsManager.getCard(cardnum);
		}    	    	
    }
    
    
    @RequestMapping(value="/cards/{cardnum}", method=RequestMethod.PUT)
    public BZCreditCard updateCard(@RequestBody BZCreditCard card, @PathVariable("cardnum") String cardnum){
    	cardsManager.updateCard(card);
    	return cardsManager.getCard(cardnum);
    }
    
    
    @RequestMapping(value="/cards/{cardnum}", method=RequestMethod.DELETE)
    public Object deleteCard(@PathVariable("cardnum") String cardnum, HttpServletResponse response) {
    	if (cardsManager.deleteCard(cardnum)){
    		return new BZApiMessage(MsgType.INFO, "Card number: " + cardnum + " has been deleted.");
    	} else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new BZApiMessage(MsgType.ERROR, "Card number: " + cardnum + " was not found.");
    	}
    }    
    
  
}
