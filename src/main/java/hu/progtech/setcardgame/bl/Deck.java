package hu.progtech.setcardgame.bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * {@code Deck} class represents the deck of {@link hu.progtech.setcardgame.bl.Card}s.
 */

public class Deck {

    /**
     * The {@link java.util.List} of {@link hu.progtech.setcardgame.bl.Card}s to maintain the deck.
     */

    private List<Card> deck = new ArrayList<>();

    /**
     * The index of the next {@link hu.progtech.setcardgame.bl.Card} to be placed on the board.
     */

    private int indexOfNextCard;

    /**
     * Initialises a newly created {@code Deck} object.
     */

    public Deck() {
        makeNewDeck();
        shuffleAllCards();
        indexOfNextCard = 0;
    }

    /**
     * Constructs a new {@code Deck} object by setting the list of {@link hu.progtech.setcardgame.bl.Card}s and resetting the index.
     * @param list The List of {@link hu.progtech.setcardgame.bl.Card}s to maintain the deck
     */

    public Deck(List<Card> list) {
        deck = list;
        indexOfNextCard = 0;
    }

    /**
     * Returns the next {@link hu.progtech.setcardgame.bl.Card} to be placed onto the Board.
     * @return The next {@link hu.progtech.setcardgame.bl.Card} to be placed
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
     * Resets the index of the next {@link hu.progtech.setcardgame.bl.Card} to be placed.
     */

    public void resetNextCard() {
        indexOfNextCard = 0;
    }

    /**
     * Fills the deck with all the 81 {@link hu.progtech.setcardgame.bl.Card}s that can be found in the Set Card Game.
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
     * Shuffles the deck, rearranging the cards in a random order.
     */

    private void shuffleAllCards() {
        Collections.shuffle(deck,new Random());
    }

    /**
     * Search for possible sets in a list of {@link hu.progtech.setcardgame.bl.Card}s.
     * @param list The list of card to search in
     * @return A {@link hu.progtech.setcardgame.bl.SetOfCards} if a set is found, else null
     */

    public static SetOfCards hint(List<Card> list){
        SetOfCards setOfCards = new SetOfCards();

        for (int i = 0; i < 10; i++) {
            for (int z = i + 1; z < 11; z++) {
                for (int u = z + 1; u < 12; u++) {
                    if (list.get(i) == null || list.get(z) == null || list.get(u) == null)
                    {
                        continue;
                    }
                    setOfCards.getCardSet().add(list.get(i));
                    setOfCards.getCardSet().add(list.get(z));
                    setOfCards.getCardSet().add(list.get(u));

                    if (setOfCards.isSet()) {
                        return setOfCards;
                    } else {
                        setOfCards.getCardSet().clear();
                    }
                }
            }
        }
        return setOfCards;
    }

    /**
     * Returns the list of {@link hu.progtech.setcardgame.bl.Card}s represented.
     * @return The list of {@link hu.progtech.setcardgame.bl.Card}s
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
