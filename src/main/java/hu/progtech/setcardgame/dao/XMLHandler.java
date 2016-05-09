package hu.progtech.setcardgame.dao;

/**
 * Created by marianna on 2016.04.23..
 */

import hu.progtech.setcardgame.bl.Deck;
import hu.progtech.setcardgame.bl.Score;

import java.util.List;

/**
 * Handling all xml operations for the game.
 */

public interface XMLHandler {

    /**
     * Writes into the leaderBoard xml file the score of the latest game.
     *
     * @param score the {@link hu.progtech.setcardgame.bl.Score} of the latest game.
     */

    void writeScore(Score score);

    /**
     * Returns a list of {@link hu.progtech.setcardgame.bl.Score} elements, which will build the leader board up.
     * @return a list of {@link hu.progtech.setcardgame.bl.Score} elements.
     */

    List<Score> readHighScoreTable();

    /**
     * Returns the requested pre-shuffled {@link hu.progtech.setcardgame.bl.Deck}.
     * @param n the n-th deck in the list of pre-shuffled {@link hu.progtech.setcardgame.bl.Deck}s.
     * @return the requested pre-shuffled {@link hu.progtech.setcardgame.bl.Deck}.
     */

    Deck readNextDeck(int n);

    /**
     * Returns the position of the player in the sorted leaderBoard xml.
     * @param player the subject of the search.
     * @return the position of the player element, if not found returns -1.
     */
    int positionOfPlayerInXML(String player);

    /**
     * Writes n pre-shuffeled {@link hu.progtech.setcardgame.bl.Deck}s into the listOfDecks.xml file.
     * @param list the list of {@link hu.progtech.setcardgame.bl.Deck}s to be written into the xml.
     */
    void writePreShuffeledDecks(List<Deck> list);
}
