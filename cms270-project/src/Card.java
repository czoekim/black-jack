
public class Card {

	private int cardValue;
	private String cardName;
	private String cardSuit;
	
	public Card(int value, String name, String suit) {
		cardValue = value;
		cardName = name;
		cardSuit = suit;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public int getCardValue() {
		return cardValue;
	}
	
	public String getCardSuit() {
		return cardSuit;
	}
}
