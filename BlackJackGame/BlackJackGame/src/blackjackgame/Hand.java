/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import blackjackgame.Card.*;
import blackjackgame.Deck.*;
import java.io.*;

import java.util.*;

/**
 *
 * @author yqm15fqu
 */
public class Hand implements Serializable, Iterable {

    static final long serialVersionUID = 102;

    //create an array for later use
    private int[] rankSum = new int[13];
    private ArrayList<Integer> handValues = new ArrayList();
    private ArrayList<Card> hand;

    //create a new hand.
    public Hand() {
        this.hand = new ArrayList();
    }
    // create a hand list that takes a card array and adds it to the hand
    public Hand(Card[] hand) {
        this.hand = new ArrayList(Arrays.asList(hand));
    }
    // Create a new hand and take another hands cards. add them to the hand
    public Hand(Hand passedCards) {
        this.hand = new ArrayList(passedCards.hand);
    }
    //returns a hand and its cards
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    //add a card to the hand.
    public void add(Card card) {
        this.hand.add(card);
    }
    // add a collection of cards to the hand
    public void add(Collection<Card> card) {
        this.hand.addAll(card);
    }
    // add a hand to the hand.
    public void add(Hand hand) {
        this.hand.addAll(hand.getHand());
    }
    // remove a card, count the ranks and the hand. return the removed card
    public boolean remove(Card card) {
        boolean cardHolder = this.hand.remove(card);
        this.countRank();
        this.handSum();
        return cardHolder;
    }
    // remove a hand by assigning it to a bool, remove all cards then count.
    public boolean remove(Hand hand) {
        boolean tempHand = this.hand.removeAll(hand.getHand());
        this.countRank();
        return tempHand;
    }
    //remove a card at a specific position
    public Card remove(int num) {
        Card removedCard = this.hand.remove(num);
        this.countRank();
        return removedCard;
    }
    //count the ranks that are currently in hand using a case and switch
    public void countRank() {
        //initialise rankSum so each position is 0,
        for (int i = 0; i < rankSum.length; i++) {
            rankSum[i] = 0;
        }
        for (Card hand1 : this.hand) {
            switch (hand1.getRank()) {
                case TWO:
                    this.rankSum[0]++;
                    System.out.println("TWO in Hand");
                    break;
                case THREE:
                    this.rankSum[1]++;
                    System.out.println("THREE in Hand");
                    break;
                case FOUR:
                    this.rankSum[2]++;
                    System.out.println("FOUR in Hand");
                    break;
                case FIVE:
                    this.rankSum[3]++;
                    System.out.println("FIVE in Hand");
                    break;
                case SIX:
                    this.rankSum[4]++;
                    System.out.println("SIX in Hnad");
                    break;
                case SEVEN:
                    this.rankSum[5]++;
                    System.out.println("SEVEN in Hand");
                    break;
                case EIGHT:
                    this.rankSum[6]++;
                    System.out.println("EIGHT in Hand");
                    break;
                case NINE:
                    this.rankSum[7]++;
                    System.out.println("NINE in Hand");
                    break;
                case TEN:
                    this.rankSum[8]++;
                    System.out.println("TEN in Hand");
                    break;
                case JACK:
                    this.rankSum[9]++;
                    System.out.println("JACK in Hand");
                    break;
                case QUEEN:
                    this.rankSum[10]++;
                    System.out.println("QUEEN in Hand");
                    break;
                case KING:
                    this.rankSum[11]++;
                    System.out.println("KING in Hand");
                    break;
                case ACE:
                    this.rankSum[12]++;
                    System.out.println("ACE in Hand");
                    break;
                default:
                    break;
            }

        }
    }
    //arrayList that sums all the cards in hand and returns all possible soft
    //and hard values.
    public ArrayList<Integer> handSum() {
        handValues.clear();
        int possibleSum = 0;
        int foundAces = 0;

        //Checks all cards to see if a value is 11, if so add foundAces by 1
        for (int j = 0; j < this.hand.size(); j++) {
            possibleSum += this.hand.get(j).getRank().getValue();
            if (this.hand.get(j).getRank().getValue() == 11) {
                foundAces++;
            }
        }
        //add the highest possible hard value to hand.
        handValues.add(possibleSum);
        for (int i = 1; i <= foundAces; i++) {
            //remove 10 for every ace found. add the new value to possibleSum
            possibleSum -= 10;
            handValues.add(possibleSum);
        }
        //return an arraylist of all possible hand values.
        return handValues;
    }
    
    //creates a new iterator
    @Override
    public Iterator iterator() {
        return this.hand.iterator();
    }
    // sorts the cards in ascending order
    public void sortAscending() {
        Collections.sort(hand, new CompareAscending());
    }
    //sorts the cards in descending order
    public void sortDescending() {
        Collections.sort(hand);
    }
    //counts the amount a suit in hand passed in as an argument.
    public int countSuit(Suit suit) {
        int Suit = 0;
        for (Card x : hand) {
            if (x.getSuit() == suit) {
                Suit++;
            }
        }
        return Suit;
    }
    //counts the amount of a rank in a hand passed in as an argument.
    public int countRank(Rank rank) {
        int Rank = 0;
        for (Card x : hand) {
            if (x.getRank() == rank) {
                Rank++;
            }
        }
        return Rank;
    }
    //toString to show what hand contains.
    @Override
    public String toString() {
        return "Hand contians: " + hand;
    }
    //bool that returns true if the cards are greater than the passed value.
    public boolean isOver(int x) {
        if (handValues.get(handValues.size() - 2) > x) {
            return true;
        } else {
            return false;
        }
    }

    //reverses the order of the current hand.
    public void reverseHand() {
        Collections.reverse(this.hand);
    }

    public static void main(String[] args) {
        //create a new hand
        Hand hand = new Hand();
        //create cards
        Card card1 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card12 = new Card(Rank.TWO, Suit.CLUBS);
        Card card13 = new Card(Rank.ACE, Suit.CLUBS);
        Card card14 = new Card(Rank.THREE, Suit.CLUBS);
        Card card2 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card card22 = new Card(Rank.TWO, Suit.DIAMONDS);

        //add cards to hand
        hand.add(card1);
        hand.add(card12);
        hand.add(card13);
        hand.add(card14);
        hand.add(card2);
        hand.add(card22);

        //testing for countRank()
        hand.countRank();
        //testing for handSum()
        hand.handSum();

        //testing for sortAscending()
        hand.sortAscending();
        
        //testing for toString
        System.out.println(hand.toString());
        
        //testing for isOver
        System.out.println(hand.isOver(115));
        
        //testing for reverseHand
        hand.reverseHand();
        
        //printing the hand
        System.out.println(hand);

    }

}
