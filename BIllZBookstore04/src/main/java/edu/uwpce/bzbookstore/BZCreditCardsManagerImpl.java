package edu.uwpce.bzbookstore;

import java.util.List;

public class BZCreditCardsManagerImpl implements BZCreditCardsManager {
	
	private List<BZCreditCard> cards;
	
	@Override
	public List<BZCreditCard> getCards() {
		return this.cards;		
	}
	
	@Override
	public void setCards(List<BZCreditCard> cards) {
		this.cards = cards;
	}
	
	@Override
	public BZCreditCard getCard(String cardNumber) {
		BZCreditCard card = null;
		for (BZCreditCard checkcard : cards){
			if (checkcard.getCardNumber().equals(cardNumber)){
				card = checkcard;
			}
		}
		return card;
	}
	
	@Override
	public void addCard(BZCreditCard card) {
		if (card != null) {
			cards.add(card);
		}
	}

	@Override
	public void updateCard(BZCreditCard card) {
		for (BZCreditCard checkcard : cards) {
			if (checkcard.getCardNumber().equals(card.getCardNumber())) {
				cards.remove(checkcard);
			}
		}
		cards.add(card);
	}
	
	@Override
	public boolean deleteCard(String cardNumber) {
		boolean deleteSuccess = false;
		for (BZCreditCard checkcard : cards) {
			if (checkcard.getCardNumber().equals(cardNumber)) {
				cards.remove(checkcard);
				deleteSuccess = true;
			}
		}
		return deleteSuccess;		
	}

}
