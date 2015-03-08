package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

public interface BZCreditCardsManager {
	
	Map<String, BZCreditCard> getCards();
	
	void setCards(Map<String, BZCreditCard> cards);
	
	BZCreditCard getCard(String cardnum);
	
	void addCard(BZCreditCard card);

	void updateCard(BZCreditCard card);
	
	boolean deleteCard(String cardnum);

}
