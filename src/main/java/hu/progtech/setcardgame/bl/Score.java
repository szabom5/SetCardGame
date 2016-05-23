package hu.progtech.setcardgame.bl;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * {@code Score} class represents a score and player pair with additional data about the player's points.
 * <p>To use this class, its constructor needs to be called, for example:</p>
     <pre>
     Score score = new Score();
    </pre>
     <p>or with 2 parameters, the name and actual score of the player: </p>
     <pre>
    Score score = new Score("example",1000.0);
    </pre>
     <p> or with 4 parameters, name of the player and the data to calculate the actual score: </p>
    <pre>
    Score score = new Score("example", 1, 1, 1000);
    </pre>
 */
public class Score {

    /**
     * The name of the player.
     */

    private SimpleStringProperty name;

    /**
     * The number of Sets the player found in the current round.
     */

    private int numberOfSetsFound;

    /**
     * The number of Hints the player used in the current round.
     */

    private int numberOfHintsUsed;

    /**
     * The time it took the player to earn this score.
     */

    private long timeUsed;

    /**
     * The actual score of the player.
     */

    private SimpleDoubleProperty score;

    /**
     * Initialises a newly created {@code Score} object.
     */

    public Score() {
        this.name = new SimpleStringProperty();
        this.score = new SimpleDoubleProperty();
    }

    /**
     * Constructs a new {@code Score} by setting the name and score of the player.
     * @param name the name of the player
     * @param score the score of the player
     */

    public Score(String name, double score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(score);
    }

    /**
     * Constructs a new {@code Score} by setting the name of the player and the data to calculate the actual score.
     * @param name the name of the player
     * @param numberOfSetsFound the number of Sets the player found in the current round
     * @param numberOfHintsUsed the number of hints the player used in the current round
     * @param timeUsed the time it took the player to earn this score
     */

    public Score(String name, int numberOfSetsFound, int numberOfHintsUsed, int timeUsed) {
        this.name = new SimpleStringProperty(name);
        this.numberOfSetsFound = numberOfSetsFound;
        this.numberOfHintsUsed = numberOfHintsUsed;
        this.timeUsed = timeUsed;
    }

    /**
     * Calculates the actual score based on the number of Sets the player found and the number of hints the player used and the time it took the player to earn this score.
     */

    public void calculateScore() {
        score = new SimpleDoubleProperty(numberOfSetsFound*1000.0-numberOfHintsUsed*900.0-timeUsed/1000);
    }

    /**
     * Returns the number of Sets the player found in the current round.
     * @return the number of Sets the player found in the current round
     */

    public int getNumberOfSetsFound() {
        return numberOfSetsFound;
    }

    /**
     * Sets the number of Sets the player found in the current round.
     * @param numberOfSetsFound the number of Sets the player found in the current round
     */

    public void setNumberOfSetsFound(int numberOfSetsFound) {
        this.numberOfSetsFound = numberOfSetsFound;
    }

    /**
     * Returns the number of hints the player used in the current round.
     * @return the number of hints the player used in the current round
     */

    public int getNumberOfHintsUsed() {
        return numberOfHintsUsed;
    }

    /**
     * Sets the number of hints the player used in the current round.
     * @param numberOfHintsUsed the number of hints the player used in the current round
     */

    public void setNumberOfHintsUsed(int numberOfHintsUsed) {
        this.numberOfHintsUsed = numberOfHintsUsed;
    }

    /**
     * Returns the time it took the player to earn this score.
     * @return the time it took the player to earn this score
     */

    public long getTimeUsed() {
        return timeUsed;
    }

    /**
     * Sets the time it took the player to earn this score.
     * @param timeUsed the time it took the player to earn this score
     */

    public void setTimeUsed(long timeUsed) {
        this.timeUsed = timeUsed;
    }

    /**
     * Returns the name of the player.
     * @return the name of the player
     */

    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the player.
     * @param name the name of the player
     */

    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Returns the actual points the player got in the current round.
     * @return the points the player got in the current round
     */

    public double getScore() {
        return score.get();
    }

    /**
     * Sets the score of the player.
     * @param score the points the player got in the current round
     */

    public void setScore(double score) {
        this.score.set(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score1 = (Score) o;

        if (Double.compare(score1.getScore(), getScore()) != 0) return false;
        return getName() != null ? getName().equals(score1.getName()) : score1.getName() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getScore());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return name.get() + ":" + score.get();
    }
}
