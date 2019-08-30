/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import blackjackgame.Card.*;
import java.util.ArrayList;

/**
 *
 * @author yqm15fqu
 */
public class IntermediatePlayer extends BasicPlayer {

    Card dealerCard;
    
    //hit logic for advanced player. checks if the possible values are >1 to 
    //determine if there is an ace in hand or not, then follows appropriate 
    //logic
    @Override
    public boolean hit() {

        ArrayList<Integer> x = playerHand.handSum();
        ArrayList<Card> cards = playerHand.getHand();
        int basicVal = x.get(0);

        if (x.size() > 1) {
            System.out.println(x.get(x.size() - 1));
            basicVal = x.get(x.size() - 1);
            if (dealerCard.getRank().getValue() >= 7 && basicVal < 17) {
                if (basicVal == 9 || basicVal == 10 || basicVal >= 17) {
                    return false;
                }
                return true;
            } else if (dealerCard.getRank().getValue() <= 6 && basicVal < 12) {
                if (basicVal != 9 || basicVal != 10 || basicVal >= 12) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }
        if (x.size() == 1) {
            if (dealerCard.getRank().getValue() >= 7 && basicVal < 17) {
                return true;
            } else if (dealerCard.getRank().getValue() <= 6 && basicVal < 12) {
                return true;
            } else {
                return false;
            }
        }
        System.out.println("Some how outside of loop conditions.");
        return false;
    }
    //so the advanced player can count cards
    @Override
    public void viewDealerCard(Card c) {
        dealerCard = c;

    }
}
