package hu.progtech.setcardgame.dao;

/**
 * Created by marianna on 2016.04.23..
 */

import hu.progtech.setcardgame.bl.Deck;

import java.util.List;
import java.util.Map;

/**
 * Handling all xml operations for the game.
 */

public interface XMLHandler {

    /**
     * Writes into the leaderBoard xml file the score of the latest game.
     *
     * @param score the score of the latest game.
     * @param player the player who played the latest game.
     */

    public void WriteScore(double score, String player);

    /**
     * Returns a map of players and list of scores, which will build the leaderboard up.
     * @return a map with the name of the players as keys and the list of scores to each player.
     */

    public Map<String,List<Double> > ReadHighScoreTable();

    /**
     * Returns the requested pre-shuffeled deck.
     * @param n the n-th deck in the list of pre-shuffeled decks.
     * @return the requested pre-shuffeled deck.
     */

    public Deck ReadNextDeck(int n);

    /**
     * Returns the position of the player in the sorted leaderBoard xml.
     * @param player the subject of the search
     * @return the position of the player element, if not found returns -1.
     */
    public int positionOfPlayerInXML(String player);
}
