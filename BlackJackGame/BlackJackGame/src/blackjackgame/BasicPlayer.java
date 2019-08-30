/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import java.util.*;

/**
 *
 * @author yqm15fqu
 */
public class BasicPlayer implements Player {

    Hand playerHand = new Hand();
    //cardcount for later
    int cardCount = 0;
    //Player balance
    int playerBalance = 200;
    //amount bet
    int playerBet = 0;

    //remove old hand, make a new one
    @Override
    public Hand newHand() {
        playerHand.remove(playerHand);
        return playerHand;
    }

    //make a bet
    @Override
    public int makeBet() {

        if (playerBalance - playerBet < 0 || playerBalance <= 0) {
            System.out.println("player doesn't have enough money");
            playerBet = 0;
            return playerBalance;
        }
        playerBet = 10;
        playerBalance -= playerBet;
        System.out.println("10 bet");
        return playerBet;
    }
    //return the bet
    @Override
    public int getBet() {
        return playerBet;
    }
    //return balance
    @Override
    public int getBalance() {
        return playerBalance;
    }
    //Hit is tested. if hand value is < 17 take a hit, if it's over 21 check
    //other possible values, if still over 21 bust. if lower value becaus of ACE
    // return lower value.
    @Override
    public boolean hit() {
        ArrayList<Integer> x = playerHand.handSum();
        int basicVal = x.get(0);

        if (basicVal < 17) {
            return true;
        }

        if (x.get(0) > 21) {

            for (int i = 1; i < x.size(); i++) {
                basicVal = x.get(i);
                if (basicVal >= 17 && basicVal <= 21) {
                    System.out.println(basicVal + "Jo");
                    return false;
                } else if (basicVal < 17) {
                    return true;
                }
            }
        }
        if (basicVal > 21) {
            return false;
        }

        return false;
    }

    // add a card to the hand
    @Override
    public void takeCard(Card c) {
        System.out.println(c.toString() + " taken");
        playerHand.add(c);
    }
    
    //add or remove the balance that is won in the game
    @Override
    public boolean settleBet(int p) {
        if (p >= 0) {
            playerBalance += p;
            playerBet = 0;
            return true;
        } else if (p < 0) {
            playerBet = 0;
            return true;
        }
        playerBet = 0;
        return false;
    }
    
    //return the total of the hand. the highest value below or equal to 21 if
    //possible
    @Override
    public int getHandTotal() {
        ArrayList<Integer> x = playerHand.handSum();

        if (x.get(0) > 21) {
            for (int i = 1; i < x.size(); i++) {
                if (x.get(i) <= 21) {
                    return x.get(i);
                }
            }
        }
        return x.get(0);
    }
    
    //checks 2 cards are passed, if they are then calls the previous is black
    //jack method and gets the return from it and returns true if true.
    @Override
    public boolean blackjack() {
        List<Card> x = new ArrayList();
        x = playerHand.getHand();

        if (x.size() == 2) {
            Card card1 = x.get(0);
            Card card2 = x.get(1);

            if (Card.isBlackJack(card1, card2) == true) {
                return true;
            }
        }
        return false;
    }
    
    //checks all possible values, if all are above 21 returns true, returns 
    //false if a possible value is below 22
    @Override
    public boolean isBust() {
        int bustCount = 0;
        List<Integer> x = playerHand.handSum();

        for (int i = 0; i < x.size(); i++) {
            if (x.get(i) > 21) {
                bustCount++;
            }
        }

        if (bustCount == x.size()) {
            return true;
        } else {
            return false;
        }
    }

    //return the hand
    @Override
    public Hand getHand() {
        return this.playerHand;
    }
    //view the dealers card
    @Override
    public void viewDealerCard(Card c) {
        Card dealerCard = c;

    }
    //tells players the deck has been shuffled, resets card count
    @Override
    public void newDeck() {
        System.out.println("The Deck has been shuffled.");
        cardCount = 0;
    }

    //records values for all cards that are played each round and updates card
    //count accordingly. used for advanced player.
    @Override
    public void viewCards(List<Card> cards) {
        int tenUp = 0, midRange = 0, sixDown = 0;

        for (Card card1 : cards) {
            if (card1.getRank().getValue() >= 10) {
                tenUp++;
            } else if (card1.getRank().getValue() >= 7
                    && card1.getRank().getValue() <= 9) {
                midRange++;
            } else if (card1.getRank().getValue() <= 6
                    && card1.getRank().getValue() >= 1) {
                sixDown++;
            }
        }
        cardCount = ((-tenUp) + sixDown);
    }

}
