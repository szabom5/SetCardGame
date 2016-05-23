package hu.progtech.setcardgame.bl;

/**
 * {@code Card} class represents a single {@code Card} of the {@link hu.progtech.setcardgame.bl.Deck}.
 * <p>To use this class, its constructor needs to be called, for example:</p>
    <pre>
    Card card = new Card();
    </pre>
    <p> or with 4 parameters, the 4 properties of a set card: </p>
    <pre>
    Card card = new Card(0,0,0,0);
    </pre>
 */

public class Card {

    /**
     * The {@code number} property of the {@code Card}.
     */

    private int number;

    /**
     * The {@code shape} property of the {@code Card}.
     */

    private int shape;

    /**
     * The {@code shading} property of the {@code Card}.
     */

    private int shading;

    /**
     * The {@code color} property of the {@code Card}.
     */

    private int color;

    /**
     * Initialises a newly created {@code Card} object.
     */

    public Card() {
    }

    /**
     * Constructs a new {@code Card} object by setting all four properties.
     * @param number the {@code number} property of the {@code Card}
     * @param shape the {@code shape} property of the {@code Card}
     * @param shading the {@code shading} property of the {@code Card}
     * @param color the {@code color} property of the {@code Card}
     */

    public Card(int number, int shape, int shading, int color) {
        this.number = number;
        this.shape = shape;
        this.shading = shading;
        this.color = color;

    }

    /**
     * Returns the {@code number} property of the {@code Card}.
     * @return the {@code number} property of the {@code Card}
     */

    public int getNumber() {
        return number;
    }

    /**
     * Sets the {@code number} property of the {@code Card}.
     * @param number the {@code number} property of the {@code Card}
     */

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Returns the {@code shape} property of the {@code Card}.
     * @return the {@code shape} property of the {@code Card}
     */

    public int getShape() {
        return shape;
    }

    /**
     * Sets the {@code Card} property of hte {@code Card}.
     * @param shape the {@code Card} property of the {@code Card}
     */

    public void setShape(int shape) {
        this.shape = shape;
    }

    /**
     * Returns the {@code shading} property of the {@code Card}.
     * @return the {@code shading} property of the {@code Card}
     */

    public int getShading() {
        return shading;
    }

    /**
     * Sets the {@code shading} property of the {@code Card}.
     * @param shading the {@code shading} property of the {@code Card}
     */

    public void setShading(int shading) {
        this.shading = shading;
    }

    /**
     * Returns the {@code color} property of the {@code Card}.
     * @return the {@code color} property of the {@code Card}
     */

    public int getColor() {
        return color;
    }

    /**
     * Sets the {@code color} property of the {@code Card}.
     * @param color the {@code color} property of the {@code Card}
     */

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (getNumber() != card.getNumber()) return false;
        if (getShape() != card.getShape()) return false;
        if (getShading() != card.getShading()) return false;
        return getColor() == card.getColor();

    }

    @Override
    public int hashCode() {
        int result = getNumber();
        result = result + 31 * getShape();
        result = result + 17 * getShading();
        result = result + 49 * getColor();
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", shape=" + shape +
                ", shading=" + shading +
                ", number=" + number +
                "}\n";
    }
}