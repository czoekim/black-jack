/**
 * @author Zoe, Ebba, Carmen, Aruna
 * 
 * @version 
 * This class creates a player to play a game of Blackjack
 * and provides methods appropriate for a player of this game,
 * such as accessing name, hand, bet, the choice of double down
 * and printing the hand of the player. 
 */

import java.util.*;

public class Player {

	private String name = "";
	private double bet;
	private double totalMoney;
	private Hand hand;
	private Iterator handIterator;
	private Scanner scan = new Scanner(System.in);

	/**
	 * Constructor that creates a player with a unique name, a unique hand 
	 * and an amount of money they have to play Blackjack. 
	 * @param name - Name of the player
	 * @param totalmoney - Total amount of money for the player
	 */
	public Player(String name, double totalmoney){
		this.name = name;
		hand = new Hand();
		this.totalMoney = totalmoney;
	}
	/**
	 * This method accesses the name of a player.
	 * 
	 * @return The name to be added to the list of players at the table.
	 */
	public String getName(){
		return name;
	}

	/**
	 * This method accesses the player's hand.
	 * 
	 * @return a Hand for the player.
	 */
	public Hand getHand() {
		return hand;
	}

	/**
	 * This method accesses the player's total money.
	 * 
	 * @return an integer value that represents player's total money.
	 */
	public double getMoney() {
		return totalMoney;
	}

	/**
	 * This method accesses the player's set bet.
	 * 
	 * @return a double value that represents player's set bet.
	 */
	public double getSetBet() {
		return bet;
	}

	/**
	 * This method sets the player's bet to their input.
	 * 
	 * @param bet - the amount of money the player wants to bet for the game.
	 */
	public void setBet(double bet){
		this.bet = bet;
		totalMoney -= bet;
	}

	/**
	 * This method allows players to collect their winnings
	 * considering if they had blackjack or not
	 */
	public void collectWinnings() {
		if (hand.checkBlackjack()) {
			double win = bet * 1.5;
			totalMoney += (win + bet);
		} else {
			totalMoney += (bet + bet);
		}
	}

	/**
	 * This method allows bets to return to player's totalMoney
	 * Used when player has same handValue as dealer's
	 */
	public void takeBetBack() {
		totalMoney += bet;
	}

	/**
	 * This method is used when a player wants to double their bet, 
	 * and can no longer hit in the current round of the game.
	 */
	public void doubleDown(){
		totalMoney -= bet;
		bet += bet;
	}

	/**
	 * This method checks the hand for an Ace
	 * Used to distinguish 1's and 11's for players and dealers
	 * @return true boolean value if hand contains an Ace
	 */
	public boolean checkForAce() {
		handIterator = new HandIterator(hand.getCards());
		while (handIterator.hasNext()) {
			Card card = (Card) handIterator.next();
			if (card.getCardName().equals("A"))
				return true;
		}
		return false;
	}
	/**
	 * This method checks the player's hand to see if it holds an Ace
	 * card. It then asks if the player wants to
	 * change the value of the Ace card from 11 to 1.
	 */
	public void aceChanger() {
		if (checkForAce()) {
			printHand();
			Iterator handIterator = new HandIterator(hand.getCards());
			Card temp = null;
			int value = 0;
			while (handIterator.hasNext()) {
				Card c = (Card) handIterator.next();
				if (c.getCardName().equals("A")) {
					System.out.println("You hold Ace card(s). Value = 1 or 11?"
							+ " Type 0 to exit.");
					int ans = scan.nextInt();
					while (ans != 1 && ans != 11 && ans != 0) {
						System.out.println("Invalid input. Try again.");
						ans = scan.nextInt();
					}
					temp = c;
					if (ans == 1) {
						value = 1;
						hand.remove(c);
					} else if (ans == 11) {
						value = 11;
						hand.remove(c);
					} else {
						return;
					}
				}
			}
			hand.addCard(new Card(1, "A", temp.getCardSuit()));
			printHand();
		}
	}

	/**
	 * This method checks hand has busted (value is greater than 21)
	 * @return true boolean value if handValue is above 21
	 */
	public boolean isBusted() {
		if (hand.checkHandValue() > 21) {
			return true;
		}
		return false;
	}

	/**
	 * This method prints the player's hand using an iterator pattern.
	 */
	public void printHand() {
		handIterator = new HandIterator(hand.getCards());
		System.out.println("\n" + getName() + "'s hand:");
		while(handIterator.hasNext()) {
			Card card = (Card) handIterator.next();
			System.out.println("\t" + card);
		}
		System.out.println("\tHand value: " + hand.checkHandValue());
	}

}