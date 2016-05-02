package hu.progtech.setcardgame.bl;

/**
 * Created by marianna on 2016.05.02..
 */
public class Score {

    private String name;

    private int numberOfSetsFound;

    private int numberOfHintsUsed;

    private int timeUsed;

    private double score;

    public Score() {
    }

    public Score(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Score(String name, int numberOfSetsFound, int numberOfHintsUsed, int timeUsed) {
        this.name = name;
        this.numberOfSetsFound = numberOfSetsFound;
        this.numberOfHintsUsed = numberOfHintsUsed;
        this.timeUsed = timeUsed;
    }

    public void calculateScore() {
        score = numberOfSetsFound*1000.0-numberOfHintsUsed*90.0-timeUsed/1000;
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

    public int getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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
