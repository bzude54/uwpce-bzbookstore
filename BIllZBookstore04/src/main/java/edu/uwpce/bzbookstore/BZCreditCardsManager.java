package edu.uwpce.bzbookstore;

import java.util.List;

public interface BZCreditCardsManager {
	
	List<BZCreditCard> getCards();
	
	void setCards(List<BZCreditCard> cards);
	
	BZCreditCard getCard(String cardnum);
	
	void addCard(BZCreditCard card);

	void updateCard(BZCreditCard card);
	
	boolean deleteCard(String cardnum);

}
