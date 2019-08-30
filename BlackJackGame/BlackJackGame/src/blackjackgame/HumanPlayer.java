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
public class HumanPlayer extends BasicPlayer {

    Scanner scan = new Scanner(System.in);
    //lets the player decide if they want to hit or not
    @Override
    public boolean hit() {
        ArrayList<Integer> x = playerHand.handSum();
        int check = 0;

        for (int i = 0; i < x.size(); i++) {
            if (x.get(i) > 21) {
                check++;
            }
        }
        if (check == x.size()) {
            return false;
        }

        System.out.println("Your hand is: " + playerHand);
        System.out.println("Do you want to hit? y/n");
        String hitOrNo = scan.next();

        if ("y".equals(hitOrNo) || "Y".equals(hitOrNo)) {
            return true;
        }

        return false;
    }
    //lets the player choose if they 
    @Override
    public int makeBet() {

        System.out.println("How much would you like to bet? (Your balance is: "
                + playerBalance + ") : ");

        playerBet = scan.nextInt();

        if (playerBet < 10 || playerBet > 500 || playerBet > playerBalance) {
            while (playerBet < 10 || playerBet > 500
                    || playerBet > playerBalance) {
                System.out.println("You can't bet more than you have."
                        + " Your balance is: " + playerBalance);
                playerBet = scan.nextInt();

            }
            playerBalance -= playerBet;
        }
        playerBalance -= playerBet;
        return playerBet;
    }

}
