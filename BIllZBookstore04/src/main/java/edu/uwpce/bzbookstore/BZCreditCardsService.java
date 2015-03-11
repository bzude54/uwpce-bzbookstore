package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

public interface BZCreditCardsService {
	
	List<BZCreditCard> getCards(int userid);
	
	void setCards(int userid, List<BZCreditCard> cards);
	
	BZCreditCard getCard(int userid, String cardnum);
	
	void addCard(int userid, BZCreditCard card);

	void updateCard(int userid, BZCreditCard card);
	
	boolean deleteCard(int userid, String cardnum);

}
