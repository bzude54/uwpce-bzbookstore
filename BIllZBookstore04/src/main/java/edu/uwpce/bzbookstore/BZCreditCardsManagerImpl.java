package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BZCreditCardsManagerImpl implements BZCreditCardsManager {
	
	private Map<String, BZCreditCard> cards;
	
	public BZCreditCardsManagerImpl() {
		this.cards = new ConcurrentHashMap<String, BZCreditCard>();
	}
	
	@Override
	public Map<String, BZCreditCard> getCards() {
		return this.cards;		
	}
	
	@Override
	public void setCards(Map<String, BZCreditCard> cards) {
		this.cards = cards;
	}
	
	@Override
	public BZCreditCard getCard(String cardType) {
		BZCreditCard card = cards.get(cardType);
		return card;
	}
	
	@Override
	public void addCard(BZCreditCard card) {
		cards.put(card.getCardType(), card);
	}

	@Override
	public void updateCard(BZCreditCard card) {
		BZCreditCard checkcard = cards.get(card.getCardType());
		if (checkcard != null) {
			cards.remove(card.getCardType());
		}
		cards.put(card.getCardType(), card);
	}
	
	@Override
	public boolean deleteCard(String cardType) {
		boolean deleteSuccess = false;
		BZCreditCard checkcard = cards.get(cardType);
		if (checkcard != null) {
			cards.remove(cardType);
			deleteSuccess = true;
		}
		return deleteSuccess;		
	}

}
