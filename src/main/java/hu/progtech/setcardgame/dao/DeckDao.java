package hu.progtech.setcardgame.dao;

import hu.progtech.setcardgame.bl.Deck;

/**
 * Handling the data operations for the stored decks.
 */

public interface DeckDao {

    /**
     * Returns the requested pre-shuffled {@link hu.progtech.setcardgame.bl.Deck}.
     * @param n the n-th deck in the list of pre-shuffled {@link hu.progtech.setcardgame.bl.Deck}s.
     * @return the requested pre-shuffled {@link hu.progtech.setcardgame.bl.Deck}.
     */

    Deck readNextDeck(int n);
}
