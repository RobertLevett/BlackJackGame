package blackjackgame;

import java.io.*;
import java.util.*;

public class Card implements Serializable, Comparable<Card> {

    //Serialisation ID
    static final long serialVersionUID = 112;

    //final ensures each rank or suit value is defined
    private final Suit suit;
    private final Rank rank;
    
    //Enum to set up the Rank for the cards
    public enum Rank {      
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
        TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

        final int cardValue;
        // returns the values for each rank
        Rank(int getValue) {
            cardValue = getValue;
        }
        //returns the previous card 
        public Rank getPrevious() { 
            if (this.cardValue == 2) {
                return ACE;
            } else {
                return values()[this.ordinal() - 1];
            }
        }
        //returns the card value
        public int getValue() {
            return this.cardValue;
        }
    }
    //enum to set up the suit for cards
    enum Suit {     
        CLUBS(1), DIAMONDS(2), HEARTS(3), SPADES(4);

        final int suitType;     //final ensures suitType is always a set value

        Suit(int getValue) {
            suitType = getValue;
        }
    }
    //Constructor for a card
    public Card(Rank rank, Suit suit) {
        this.rank = rank;              
        this.suit = suit;
    }
    // returns the suit of a card
    public Suit getSuit() {             
        return suit;
    }
    // returns the rank of a card
    public Rank getRank() {            
        return rank;
    }
    // sums the value of 2 cards
    public static int sum(Card card1, Card card2) { 
        return card1.rank.getValue() + card2.rank.getValue();
    }
    // boolean that returns true if the value of 2 cards is equal to 21
    public static boolean isBlackJack(Card card1, Card card2) {
        if (sum(card1, card2) == 21 && (card1.rank == card1.rank.ACE
                || card2.rank == card2.rank.ACE)) {
            return true;
        }
        return false;
    }
    // Class made to compare cards into ascending order
    public static class CompareAscending implements Comparator<Card> {
        
        //2 cards are compared, 1 is returned if card 1 is greater than card 2
        @Override
        public int compare(Card card1, Card card2) {
            if (card1.getRank().ordinal() > card2.getRank().ordinal()) {
                return 1;
            } else if (card1.getRank().ordinal() == card2.getRank().ordinal()) {
                if (card1.getSuit().ordinal() < card2.getSuit().ordinal()) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }
    //class made to compare suits of cards
    public static class CompareSuit implements Comparator<Card> {

        //2 cards are compared, if card 1 is greater than card 2, -1 is returned
        @Override
        public int compare(Card card1, Card card2) {
            if (card1.getSuit().ordinal() < card2.getSuit().ordinal()) {
                return -1;
            } else if (card1.getSuit().ordinal() == card2.getSuit().ordinal()) {
                if (card1.getRank().ordinal() < card2.getRank().ordinal()) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }
    //compareTo method that returns -1 if tha card passed is less than the card
    //being called on.
    @Override
    public int compareTo(Card card) {
        if (this.rank.getValue() > card.getRank().getValue()) {
            return -1;
        } else if (this.getRank().getValue()
                < card.getRank().getValue()) {
            return 1;
        }
        return 0;
    }

    //toString that prints the name of a card e.g TWO OF HEARTS and returns
    //it as a string.
    @Override
    public String toString() {
        String cardInfo = this.getRank() + " OF " + this.getSuit();

        return cardInfo;
    }

    //-------------------TESTING, REMOVE LATER------------------------//
    public static void main(String[] args) {

        //creating cards giving rank and suit
        Card tenDiamond = new Card(Rank.TEN, Suit.DIAMONDS);
        Card tenSpades = new Card(Rank.TEN, Suit.SPADES);
        Card twoClubs = new Card(Rank.TWO, Suit.CLUBS);
        Card sixHearts = new Card(Rank.SIX, Suit.HEARTS);
        Card aceSpades = new Card(Rank.ACE, Suit.SPADES);
        Card aceHearts = new Card(Rank.ACE, Suit.HEARTS);

        //ArrayList of card objects
        ArrayList<Card> deck = new ArrayList();
        
        //adding cards to the card arraylist
        deck.add(tenDiamond);
        deck.add(tenSpades);
        deck.add(twoClubs);
        deck.add(sixHearts);
        
        // testing toString
        System.out.println("Cards added to an ArrayList using"
                + " the deck constructor: \t" + deck.toString());
        //testing sum
        System.out.println("Sum being tested: " + sum(aceHearts, tenSpades));
        //testing isBlackJack
        System.out.println("isBlackJack being"
                + " tested: " + isBlackJack(aceHearts, tenSpades));
        //testing compareTo
        System.out.println(tenDiamond.compareTo(tenSpades));

        //   CompareAscending x = new CompareAscending();
        //  System.out.println(x.compare(tenDiamond, tenSpades));
    }

}
