package hu.progtech.setcardgame.bl;

/**
 * Created by marianna on 2016.04.09..
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Deck class represents the deck of Cards.
 */

public class Deck {

    /**
     * The List of Cards to maintain the deck.
     */

    private List<Card> deck = new ArrayList<>();

    /**
     * The index of the next Card to be placed on the board.
     */

    private int indexOfNextCard;

    /**
     * Initialises a newly created Deck object.
     */

    public Deck() {
        makeNewDeck();
        suffleAllCards();
        indexOfNextCard = 0;
    }

    /**
     * Constructs a new Deck object by setting the list of Cards and resetting the index.
     * @param list The List of Cards to maintain the deck
     */

    public Deck(List<Card> list) {
        deck = list;
        indexOfNextCard = 0;
    }

    /**
     * Returns the next Card to be placed onto the Board.
     * @return The next Card to be placed
     */

    public Card getNextCard() {
        Card card = null;
        if(indexOfNextCard < deck.size())
        {
            card = deck.get(indexOfNextCard);
            indexOfNextCard++;
        }
        return card;
    }

    /**
     * Resets the index of the next Card to be placed.
     */

    public void resetNextCard() {
        indexOfNextCard = 0;
    }

    /**
     * Fills the deck with all the 81 Cards that can be found in the Set Card Game.
     */

    private void makeNewDeck() {

        for(int number = 0; number < 3; number++) {
            for(int shape = 0; shape < 3; shape++) {
                for(int shading = 0; shading < 3; shading++) {
                    for(int color = 0; color < 3; color++) {
                        deck.add(new Card(number,shape,shading,color));
                    }
                }
            }
        }

    }

    /**
     * Shuffeles the deck, rearranging the Cards in a random order.
     */

    private void suffleAllCards() {
        Collections.shuffle(deck,new Random());
    }

    /**
     * Returns the list of Cards represented.
     * @return The list of Cards
     */

    public List<Card> getDeck() {
        return deck;
    }
    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }
}
