package hu.progtech.setcardgame.bl;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by marianna on 2016.05.02..
 */
public class Score {

    private SimpleStringProperty name;

    private int numberOfSetsFound;

    private int numberOfHintsUsed;

    private long timeUsed;

    private SimpleDoubleProperty score;

    public Score() {
        this.name = new SimpleStringProperty();
        this.score = new SimpleDoubleProperty();
    }

    public Score(String name, double score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(score);
    }

    public Score(String name, int numberOfSetsFound, int numberOfHintsUsed, int timeUsed) {
        this.name = new SimpleStringProperty(name);
        this.numberOfSetsFound = numberOfSetsFound;
        this.numberOfHintsUsed = numberOfHintsUsed;
        this.timeUsed = timeUsed;
    }

    public void calculateScore() {
        score = new SimpleDoubleProperty(numberOfSetsFound*1000.0-numberOfHintsUsed*90.0-timeUsed/1000);
    }

    public int getNumberOfSetsFound() {
        return numberOfSetsFound;
    }

    public void setNumberOfSetsFound(int numberOfSetsFound) {
        this.numberOfSetsFound = numberOfSetsFound;
    }

    public int getNumberOfHintsUsed() {
        return numberOfHintsUsed;
    }

    public void setNumberOfHintsUsed(int numberOfHintsUsed) {
        this.numberOfHintsUsed = numberOfHintsUsed;
    }

    public long getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(long timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getScore() {
        return score.get();
    }

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
        return name + ":" + score;
    }
}
