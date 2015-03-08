package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
public class BZApiUserCreditCardsController {
   
    @Autowired
    private BZUsersManager usersManager;
    
    @Autowired
    private BZCreditCardsManager cardsManager;

/*    public BZApiUserCreditCardsController() {
    	usersManager = new BZUsersManagerImpl();
    }
*/
   
    @RequestMapping(value="/cards", method=RequestMethod.GET)
    public Map<String, BZCreditCard> getCards(@PathVariable("userid") int userid){
    	cardsManager.setCards(usersManager.getSingleUserById(userid).getCards());
       	return cardsManager.getCards();
    }
    
    
    @RequestMapping(value="/cards/{cardid}", method=RequestMethod.GET)
    public BZCreditCard getCard(HttpServletResponse response, @PathVariable("userid") int userid,  @PathVariable("cardid") String cardid) {
    	cardsManager.setCards(usersManager.getSingleUserById(userid).getCards());
    	return cardsManager.getCard(cardid);
    }
    
    
    @RequestMapping(value="/cards/{cardid}", method=RequestMethod.POST)
    public Object createCard(@RequestBody BZCreditCard newcard, @PathVariable("userid") int userid, @PathVariable("cardid") String cardid, HttpServletResponse response) {
    	cardsManager.setCards(usersManager.getSingleUserById(userid).getCards());
		if (cardsManager.getCard(cardid) != null) {
            return new BZApiMessage(MsgType.ERROR, "Credit card with username= " + cardid + " already exists.");
		} else {
			cardsManager.addCard(newcard);	
			usersManager.getSingleUserById(userid).setCards(cardsManager.getCards());
	        response.setStatus(HttpServletResponse.SC_CREATED);
			return cardsManager.getCard(cardid);
		}    	    	
    }
    
    
    @RequestMapping(value="/cards/{cardid}", method=RequestMethod.PUT)
    public BZCreditCard updateCard(@RequestBody BZCreditCard card, @PathVariable("userid") int userid, @PathVariable("cardid") String cardid){
    	cardsManager.setCards(usersManager.getSingleUserById(userid).getCards());
    	cardsManager.updateCard(card);
		usersManager.getSingleUserById(userid).setCards(cardsManager.getCards());
    	return cardsManager.getCard(cardid);
    }
    
    
    @RequestMapping(value="/cards/{cardid}", method=RequestMethod.DELETE)
    public Object deleteCard(@PathVariable("userid") int userid, @PathVariable("cardid") String cardid, HttpServletResponse response) {
    	cardsManager.setCards(usersManager.getSingleUserById(userid).getCards());
    	if (cardsManager.deleteCard(cardid)){
    		usersManager.getSingleUserById(userid).setCards(cardsManager.getCards());
    		return new BZApiMessage(MsgType.INFO, "Card number: " + cardid + " has been deleted.");
    	} else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new BZApiMessage(MsgType.ERROR, "Card number: " + cardid + " was not found.");
    	}
    }    
    
  
}
