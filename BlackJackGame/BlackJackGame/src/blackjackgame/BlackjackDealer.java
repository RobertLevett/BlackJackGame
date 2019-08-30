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
public class BlackjackDealer implements Dealer {

    //for assigning players to the dealer
    List<Player> currentPlayers = new ArrayList();
    //dealer hand
    Hand dHand = new Hand();
    //dealer deck
    Deck dDeck = new Deck();
    //for player positions
    int playerPosition = 0;
    //dealer hand total
    int dTotal = 0;
    //players bets
    int playerBets = 0;

    //adds players to the arraylist associated with the dealer
    @Override
    public void assignPlayers(List<blackjackgame.Player> p) {
        if (p.size() > 7) {
            System.out.println("There are too many playes for this game,"
                    + " a maximum of 8 plays only");
        } else if (p.size() <= 7) {
            currentPlayers.addAll(p);
        }
    }
    //takes the players bets for all the players
    @Override
    public void takeBets() {
        for (Player currentPlayer : currentPlayers) {
            playerBets += currentPlayer.getBet();
        }
        System.out.println("All bets recieved, as a total of £" + playerBets
                + " from " + currentPlayers.size() + " players\n\n");
        playerBets = 0;
    }  
    //checks theres enough cards for the round, if not it makes a new deck and
    //shuffles. then deals 2 cards to each player and one for the dealer.
    @Override
    public void dealFirstCards() {

        if ((dDeck.size() - (currentPlayers.size() * 2)) <= 13) {
            dDeck.newDeck();
            dDeck.shuffleDeck();

            for (Player currentPlayer : currentPlayers) {
                currentPlayer.newDeck();
            }
        }

        dHand.remove(dHand);

        for (Player currentPlayer : currentPlayers) {

            currentPlayer.newHand();
            currentPlayer.takeCard(dDeck.deal());
            currentPlayer.takeCard(dDeck.deal());

            System.out.println("Hand size: "
                    + currentPlayer.getHandTotal() + "\n");
        }
        dHand.add(dDeck.deal());
    }
    
    //makes each player call hit while true, then checks for blackjacks and bust
    @Override
    public int play(blackjackgame.Player p) {
        while (p.hit() == true) {
            p.takeCard(dDeck.deal());
        }
        if (p.blackjack() == true) {
            System.out.println("Total: " + p.getHandTotal() + "\n");
            return p.getHandTotal();
        }
        if (p.isBust() == true) {
            System.out.println("Player is bust!\n");
            return p.getHandTotal();
        }
        System.out.println("Total: " + p.getHandTotal() + "\n");
        return p.getHandTotal();
    }
    
    //dealer draws cards until 17 or higher
    @Override
    public int playDealer() {
        System.out.println(dHand);

        while (scoreHand(dHand) < 17) {
            dHand.add(dDeck.deal());
            dTotal = scoreHand(dHand);
            System.out.println(dHand);
        }
        System.out.println(dTotal);
        return dTotal;
    }
    
    //checks the hand values for each player.
    @Override
    public int scoreHand(Hand h) {
        ArrayList<Integer> x = h.handSum();
        int hTotal = x.get(0);

        int check = 1;
        if (hTotal > 21) {
            for (int i = 1; i < x.size() - 1; i++) {
                hTotal = x.get(check);
                if (hTotal >= 17 && hTotal <= 21) {
                    System.out.println(hTotal);
                    return hTotal;
                } else {
                    check++;
                }
            }
            if (hTotal > 21) {
                System.out.println("Hand is bust");
                return hTotal;
            }

        }

        return hTotal;
    }
    
    //settles all bets for all players and pays amoutns out
    @Override
    public void settleBets() {

        int playerTotal = 0;
        int playerBet = 0;

        for (Player currentPlayer : currentPlayers) {
            playerBet = currentPlayer.getBet();
            playerTotal = currentPlayer.getHandTotal();

            if (currentPlayer.blackjack() == true && dTotal != 21
                    && dHand.getHand().size() > 2) {
                currentPlayer.settleBet((playerBet * 2)
                        + playerBet);
                System.out.println("BlackJack for player" + playerPosition
                        + "! " + (((playerBet) * 2) + playerBet) + " won!");
            } else if (dHand.getHand().size() == 2 && dTotal == 21
                    && currentPlayer.blackjack() == false) {
                currentPlayer.settleBet(-playerBet);
                System.out.println("Player" + playerPosition + " loses "
                        + playerBet);
            } else if (dHand.getHand().size() == 2 && dTotal == 21
                    && currentPlayer.blackjack() == true) {
                currentPlayer.settleBet(playerBet);
                System.out.println("Dealer & Player" + playerPosition + "Both "
                        + "have BlackJack! Player" + playerPosition + "'s bet"
                        + " returned.");
            } else if (dTotal > 21 && playerTotal <= 21) {
                currentPlayer.settleBet(playerBet * 2);
                System.out.println("Dealer is bust, player" + playerPosition
                        + " wins " + (playerBet * 2));
            } else if (currentPlayer.isBust() == true) {
                currentPlayer.settleBet(-playerBet);
                System.out.println("Player" + playerPosition + " is bust");
            } else if (dTotal <= 21 && playerTotal < dTotal) {
                currentPlayer.settleBet(-playerBet);
                System.out.println("Dealer has a total " + dTotal + ". Player"
                        + playerPosition + " has "
                        + playerTotal + ". Dealer Wins");
            } else if (playerTotal > dTotal && playerTotal <= 21) {
                currentPlayer.settleBet(playerBet * 2);
                System.out.println("Dealer has a total " + dTotal + ". Player"
                        + playerPosition + " has " + playerTotal
                        + ". Player Wins " + (playerBet * 2));
            } else if (playerTotal == dTotal) {
                currentPlayer.settleBet(playerBet);
                System.out.println("Dealer has a total " + dTotal + ". Player"
                        + playerPosition + " has " + playerTotal + ". DRAW! "
                        + "bets returned");
            }
            playerPosition++;
        }
        //removes bust players
        for (int i = 0; i < currentPlayers.size(); i++) {
            if (currentPlayers.get(i).getBalance() <= 0) {
                currentPlayers.remove(i);
                System.out.println("Player has run out of money, OH NO!!!");
                System.out.println("Player forcefully "
                        + "removed from the table");
            }
        }
        //returns players current balances.
        for (Player currentPlayer1 : currentPlayers) {
            System.out.println("\n\nPlayer" + playerPosition + "'s balance: "
                    + currentPlayer1.getBalance());
            playerPosition++;
        }
        playerPosition = 0;
    }

}
