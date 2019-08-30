/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import java.util.*;
import java.io.*;
import blackjackgame.Card.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert
 */
public class Deck implements Serializable, Iterable {
     static final long serialVersionUID = 111;

    //creating a static ArrayList of Cards.
    public static ArrayList<Card> Deck;
    
    //Creating a new deck and populating the Deck with all possible suits/cards.
    public Deck() {
        Deck = new ArrayList<Card>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Deck.add(new Card(rank, suit));
            }
        }
    }
    //method for shuffling the deck
    public void shuffleDeck() {
        int deckSize = Deck.size();
        //random seed generated
        Random random = new Random();
        
        //swap the positions of the generated seed index and the current card
        for (int i = 0; i < deckSize; i++) {
            Card curCard = Deck.get(i);
            int randIndex = i + random.nextInt(deckSize - i);
            Deck.set(i, Deck.get(randIndex));
            Deck.set(randIndex, curCard);

        }
    }
    //remove the top card of the shuffled deck and return it.
    public Card deal() {
        return Deck.remove(0);
    }

    //return the size of the deck currently
    public int size() {
        return Deck.size();
    }
    //clear the current deck and re-populate it.
    public void newDeck() {
        Deck.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Deck.add(new Card(rank, suit));
            }
        }
    }
    // iterator created.
    @Override
    public Iterator iterator() {
        return new SecondCardIterator();
    }
    //secondCardIterator class made that is serializable
    private static class SecondCardIterator implements Iterator<Card>,
            Serializable {

        int position = 0;
        //generate a null card
        Card card = null;
        //check there's a next card and return true if there is
        @Override
        public boolean hasNext() {
            return position < Deck.size() - 1;
        }
        //if true is returned from has next, go 2 cards ahead
        @Override
        public Card next() {
            if (hasNext() == true) {
                Card otherCard = Deck.get(position);
                position += 2;
                return otherCard;
            } else {
                return null;
            }
        }
    }
    // toString that returns each card in a deck.
    @Override
    public String toString() {
        String print = "";

        if (Deck.size() == 0) {
            return "Deck contains no cards, did you drop it?";
        } else {
            Deck.forEach((item) -> {
                System.out.println(item.getRank() + "\t OF " + item.getSuit());
            });
        }
        return "";
    }

    public static void main(String[] args) {
        //test deck made;
        Deck j = new Deck();
        //show deck before shuffle
        System.out.println("Before Shuffle: \n" + j);
        //create a new secondCardIterator
        SecondCardIterator example;
        example = new SecondCardIterator();

        //shuffle deck and print
        j.shuffleDeck();
        System.out.println("After Shuffle: \n" + j.toString());
        
        // testing size, newDeck and deal methods
        System.out.println(j.size());
        System.out.println(j.deal());
        System.out.println(j.deal());
        System.out.println(j.size());

        System.out.println(j.toString());

        j.newDeck();
        System.out.println(j.size());
        // testing size, newDeck and deal methods

      //  j.shuffleDeck();
        for (int i = 0; i < 10; i++) {
            System.out.println(example.next());
        }
        System.out.println("\n\n\n\n");
///////////////////////////////////////////////////////////////////////

        //              SERIALIZ(s)ATION read
        System.out.println("SERIALIZATION TEST");

        SecondCardIterator test = new SecondCardIterator();
        String filename = "SecondCardIteratorDeck.ser";
        j.newDeck();
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            while (test.hasNext() == true) {
                out.writeObject(test.next());
            }
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//                      SERIALIZ(s)ATION ouput
        
        System.out.println("\n\n\n\n\n\n Hello\n\n\n");
        
        //attempt to print the cards. works but error is always thrown
        //(No time left to fix)
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            Card readCard;       
            int counter = 0;
            
            while((readCard = (Card) in.readObject()) != null || counter == 25){
                System.out.println(readCard.toString());
                counter++;
            }
            in.close();

            System.out.println(j);
        } catch (FileNotFoundException ex) { 
             Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
         } 

    }

}
