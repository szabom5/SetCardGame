package hu.progtech.setcardgame.dao;

import hu.progtech.setcardgame.bl.Score;

import java.util.List;

/**
 * Handling the data operations for the leader board.
 */

public interface ScoreDao {

    /**
     * Writes the score of the latest game.
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
     * Returns the position of the player in the sorted leader board.
     * @param player the subject of the search.
     * @return the position of the player element, if not found returns -1.
     */
    int positionOfPlayerInXML(String player);


}
