/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import blackjackgame.Card.*;
import java.util.*;

/**
 *
 * @author Robert
 */
public class BlackjackTable {

    public static void humanGame() {

        BlackjackTable table = new BlackjackTable();
        BlackjackDealer gameDealer = new BlackjackDealer();
        int playerNo = 0;
        int gameCycles = 0;
        Scanner scan = new Scanner(System.in);
        Scanner cont = new Scanner(System.in);

        System.out.println("\n\n -- Deck Shuffled --\n\n");
        gameDealer.dDeck.shuffleDeck();

        HumanPlayer player1 = new HumanPlayer();
        BasicPlayer player2 = new BasicPlayer();

        List<Player> humanExample = new ArrayList();

        humanExample.add(player1);
        humanExample.add(player2);

        gameDealer.assignPlayers(humanExample);

        System.out.println("Please enter the amount of rounds you would like"
                + " to play: ");

        gameCycles = scan.nextInt();

        while (gameCycles != 0 && gameCycles > 0) {

            System.out.println("\n\n -- Bets made --\n\n");
            for (Player player : humanExample) {
                player.makeBet();
            }
            System.out.println("\n\n -- Bets Taken --\n\n");
            gameDealer.takeBets();

            System.out.println("\n\n -- First Cards Delt --\n\n");
            gameDealer.dealFirstCards();

            System.out.println("\n\n -- Players Play --\n\n");

            for (Player player : humanExample) {
                System.out.println("Player" + playerNo);
                gameDealer.play(player);
                playerNo++;
            }

            System.out.println("\n\n -- dealer deals now --\n\n");
            gameDealer.playDealer();

            System.out.println("\n\n -- settle bets --\n\n");
            gameDealer.settleBets();
            gameCycles--;

            if (gameCycles == 0) {
                System.out.println("Would you like to continue? y/n");
                String h = cont.nextLine();
                if (h.charAt(0) == 'y' || h.charAt(0) == 'Y') {
                    System.out.println("Please enter the amount of rounds "
                            + "you would like to play: ");
                    gameCycles = scan.nextInt();
                } else {
                    System.out.println("Thanks for playing!");
                }

            }
            playerNo = 0;
        }

    }

    public static void basicGame() {
        BlackjackTable table = new BlackjackTable();
        BlackjackDealer gameDealer = new BlackjackDealer();
        int playerNo = 0;
        int gameCycles = 0;
        Scanner scan = new Scanner(System.in);
        Scanner cont = new Scanner(System.in);

        System.out.println("\n\n -- Deck Shuffled --\n\n");
        gameDealer.dDeck.shuffleDeck();

        BasicPlayer player1 = new BasicPlayer();
        BasicPlayer player2 = new BasicPlayer();
        BasicPlayer player3 = new BasicPlayer();
        BasicPlayer player4 = new BasicPlayer();

        List<Player> example = new ArrayList();

        example.add(player1);
        example.add(player2);
        example.add(player3);
        example.add(player4);

        gameDealer.assignPlayers(example);

        System.out.println("Please enter the amount of rounds you would like"
                + " to play: ");
        gameCycles = scan.nextInt();
        while (gameCycles != 0 && gameCycles > 0) {

            System.out.println("\n\n -- Bets made --\n\n");
            for (Player player : example) {
                player.makeBet();
            }
            System.out.println("\n\n -- Bets Taken --\n\n");
            gameDealer.takeBets();

            System.out.println("\n\n -- First Cards Delt --\n\n");
            gameDealer.dealFirstCards();

            System.out.println("\n\n -- Players Play --\n\n");

            for (Player player : example) {
                System.out.println("Player" + playerNo);
                gameDealer.play(player);
                playerNo++;
            }

            System.out.println("\n\n -- dealer deals now --\n\n");
            gameDealer.playDealer();

            System.out.println("\n\n -- settle bets --\n\n");
            gameDealer.settleBets();
            gameCycles--;

            if (gameCycles == 0) {
                System.out.println("Would you like to continue? y/n");
                String h = cont.nextLine();
                if (h.charAt(0) == 'y' || h.charAt(0) == 'Y') {
                    System.out.println("Please enter the amount of rounds "
                            + "you would like to play: ");
                    gameCycles = scan.nextInt();
                } else {
                    System.out.println("Thanks for playing!");
                }

            }
            playerNo = 0;
        }
    }

    public static void intermediateGame() {

        BlackjackTable table = new BlackjackTable();
        BlackjackDealer gameDealer = new BlackjackDealer();
        int playerNo = 0;
        int gameCycles = 0;
        Scanner scan = new Scanner(System.in);
        Scanner cont = new Scanner(System.in);

        System.out.println("\n\n -- Deck Shuffled --\n\n");
        gameDealer.dDeck.shuffleDeck();

        IntermediatePlayer player1 = new IntermediatePlayer();
        IntermediatePlayer player2 = new IntermediatePlayer();
        IntermediatePlayer player3 = new IntermediatePlayer();
        IntermediatePlayer player4 = new IntermediatePlayer();

        List<Player> intermediateExample = new ArrayList();

        intermediateExample.add(player1);
        intermediateExample.add(player2);
        intermediateExample.add(player3);
        intermediateExample.add(player4);

        gameDealer.assignPlayers(intermediateExample);

        System.out.println("Please enter the amount of rounds you would like"
                + " to play: ");

        gameCycles = scan.nextInt();

        while (gameCycles != 0 && gameCycles > 0) {

            System.out.println("\n\n -- Bets made --\n\n");
            for (Player player : intermediateExample) {
                player.makeBet();
            }
            System.out.println("\n\n -- Bets Taken --\n\n");
            gameDealer.takeBets();

            System.out.println("\n\n -- First Cards Delt --\n\n");
            gameDealer.dealFirstCards();
            System.out.println("DEALER CARD!!: "
                    + gameDealer.dHand.getHand().get(0));
            player1.viewDealerCard(gameDealer.dHand.getHand().get(0));
            player2.viewDealerCard(gameDealer.dHand.getHand().get(0));
            player3.viewDealerCard(gameDealer.dHand.getHand().get(0));
            player4.viewDealerCard(gameDealer.dHand.getHand().get(0));

            System.out.println("\n\n -- Players Play --\n\n");

            for (Player player : intermediateExample) {

                System.out.println("Player" + playerNo);
                gameDealer.play(player);
                playerNo++;
            }

            System.out.println("\n\n -- dealer deals now --\n\n");
            gameDealer.playDealer();

            System.out.println("\n\n -- settle bets --\n\n");
            gameDealer.settleBets();
            gameCycles--;

            if (gameCycles == 0) {
                System.out.println("Would you like to continue? y/n");
                String h = cont.nextLine();
                if (h.charAt(0) == 'y' || h.charAt(0) == 'Y') {
                    System.out.println("Please enter the amount of rounds "
                            + "you would like to play: ");
                    gameCycles = scan.nextInt();
                } else {
                    System.out.println("Thanks for playing!");
                }

            }
            playerNo = 0;
        }

    }

    public static void advancedGame() {

        BlackjackTable table = new BlackjackTable();
        BlackjackDealer gameDealer = new BlackjackDealer();

        int playerNo = 0;
        int gameCycles = 0;

        Scanner scan = new Scanner(System.in);
        Scanner cont = new Scanner(System.in);

        System.out.println("\n\n -- Deck Shuffled --\n\n");
        gameDealer.dDeck.shuffleDeck();
        
        //create players
        AdvancedPlayer player1       = new AdvancedPlayer();
        BasicPlayer player2          = new BasicPlayer();
        HumanPlayer player3          = new HumanPlayer();
        IntermediatePlayer player4   = new IntermediatePlayer();

        List<Player> advancedExample = new ArrayList();
        //assign to array
        advancedExample.add(player1);
        advancedExample.add(player2);
        advancedExample.add(player3);
        advancedExample.add(player4);
        //assign players
        gameDealer.assignPlayers(advancedExample);

        System.out.println("Please enter the amount of rounds you would like"
                + " to play: ");
        //how many games to play
        gameCycles = scan.nextInt();

        while (gameCycles != 0 && gameCycles > 0) {

            System.out.println("\n\n -- Bets made --\n\n");
            for (Player player : advancedExample) {
                player.makeBet();
            }
            System.out.println("\n\n -- Bets Taken --\n\n");
            gameDealer.takeBets();

            System.out.println("\n\n -- First Cards Delt --\n\n");
            gameDealer.dealFirstCards();
            System.out.println("DEALER CARD!!: "
                    + gameDealer.dHand.getHand().get(0));
            player1.viewDealerCard(gameDealer.dHand.getHand().get(0));
            player2.viewDealerCard(gameDealer.dHand.getHand().get(0));
            player3.viewDealerCard(gameDealer.dHand.getHand().get(0));
            player4.viewDealerCard(gameDealer.dHand.getHand().get(0));

            System.out.println("\n\n -- Players Play --\n\n");
            for (Player player : advancedExample) {

                System.out.println("Player" + playerNo);
                gameDealer.play(player);
                playerNo++;
            }

            System.out.println("\n\n -- dealer deals now --\n\n");
            gameDealer.playDealer();
            //count cards for advanced
            for (Player player : advancedExample) {
                player1.viewCards(player.getHand().getHand());
            }
            player1.viewCards(gameDealer.dHand.getHand());


            System.out.println("\n\n -- settle bets --\n\n");
            gameDealer.settleBets();
            gameCycles--;

            if (gameCycles == 0) {
                System.out.println("Would you like to continue? y/n");
                String h = cont.nextLine();
                if (h.charAt(0) == 'y' || h.charAt(0) == 'Y') {
                    System.out.println("Please enter the amount of rounds "
                            + "you would like to play: ");
                    gameCycles = scan.nextInt();
                } else {
                    System.out.println("Thanks for playing!");
                }

            }
            playerNo = 0;
        }

    }

    public static void main(String[] args) {

           basicGame();
//           humanGame();
//           intermediateGame();
//           advancedGame();

            ////////////////Didn't have time to finish saving or to be able to 
            ////////////////save to text file. Sorry!!
    }
}
