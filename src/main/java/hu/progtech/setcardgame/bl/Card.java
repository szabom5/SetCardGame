package hu.progtech.setcardgame.bl;

/**
 * Card class represents a single Card of the Deck.
 */

public class Card {

    /**
     * The number property of the Card.
     */

    private int number;

    /**
     * The shape property of the Card.
     */

    private int shape;

    /**
     * The shading property of the Card.
     */

    private int shading;

    /**
     * The color property of the Card.
     */

    private int color;

    /**
     * Initialises a newly created Card object.
     */

    public Card() {
    }

    /**
     * Constructs a new Card object by setting all four properties.
     * @param number The number property of the Card
     * @param shape The shape property of the Card
     * @param shading The shading property of the Card
     * @param color The color property of the Card
     */

    public Card(int number, int shape, int shading, int color) {
        this.number = number;
        this.shape = shape;
        this.shading = shading;
        this.color = color;

    }

    /**
     * Returns the number property of the Card.
     * @return The number property of the Card
     */

    public int getNumber() {
        return number;
    }

    /**
     * Sets the number property of the Card.
     * @param number The number property of the Card
     */

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Returns the shape property of the Card.
     * @return The shape property of the Card
     */

    public int getShape() {
        return shape;
    }

    /**
     * Sets the shape property of hte Card.
     * @param shape The shape property of the Card
     */

    public void setShape(int shape) {
        this.shape = shape;
    }

    /**
     * Returns the shading property of the Card.
     * @return The shading property of the Card
     */

    public int getShading() {
        return shading;
    }

    /**
     * Sets the shading property of the Card.
     * @param shading The shading property of the Card
     */

    public void setShading(int shading) {
        this.shading = shading;
    }

    /**
     * Returns the color property of the Card.
     * @return The color property of the Card
     */

    public int getColor() {
        return color;
    }

    /**
     * Sets the color property of the Card.
     * @param color The color property of the Card
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
        result = 31 * result + getShape();
        result = 31 * result + getShading();
        result = 31 * result + getColor();
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", shading=" + shading +
                ", shape=" + shape +
                ", number=" + number +
                "}\n";
    }
}