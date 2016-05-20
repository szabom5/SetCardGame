package hu.progtech.setcardgame.bl;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Score class represents a score and player pair with additional data about the player's points.
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
     * Initialises a newly created Score object.
     */

    public Score() {
        this.name = new SimpleStringProperty();
        this.score = new SimpleDoubleProperty();
    }

    /**
     * Constructs a new Score by setting the name and score of the player.
     * @param name The name of the player
     * @param score The score of the player
     */

    public Score(String name, double score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(score);
    }

    /**
     * Constructs a new Score by setting the name of the player and the data to calculate the actual score.
     * @param name The name of the player
     * @param numberOfSetsFound The number of Sets the player found in the current round
     * @param numberOfHintsUsed The number of hints the player used in the current round
     * @param timeUsed The time it took the player to earn this score
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
     * @return The number of Sets the player found in the current round
     */

    public int getNumberOfSetsFound() {
        return numberOfSetsFound;
    }

    /**
     * Sets the number of Sets the player found in the current round.
     * @param numberOfSetsFound The number of Sets the player found in the current round
     */

    public void setNumberOfSetsFound(int numberOfSetsFound) {
        this.numberOfSetsFound = numberOfSetsFound;
    }

    /**
     * Returns the number of hints the player used in the current round.
     * @return The number of hints the player used in the current round
     */

    public int getNumberOfHintsUsed() {
        return numberOfHintsUsed;
    }

    /**
     * Sets the number of hints the player used in the current round.
     * @param numberOfHintsUsed The number of hints the player used in the current round
     */

    public void setNumberOfHintsUsed(int numberOfHintsUsed) {
        this.numberOfHintsUsed = numberOfHintsUsed;
    }

    /**
     * Returns the time it took the player to earn this score.
     * @return The time it took the player to earn this score
     */

    public long getTimeUsed() {
        return timeUsed;
    }

    /**
     * Sets the time it took the player to earn this score.
     * @param timeUsed The time it took the player to earn this score
     */

    public void setTimeUsed(long timeUsed) {
        this.timeUsed = timeUsed;
    }

    /**
     * Returns the name of the player.
     * @return The name of the player
     */

    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the player.
     * @param name The name of the player
     */

    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Returns the actual points the player got in the current round.
     * @return The points the player got in the current round
     */

    public double getScore() {
        return score.get();
    }

    /**
     * Sets the score of the player.
     * @param score The points the player got in the current round
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
