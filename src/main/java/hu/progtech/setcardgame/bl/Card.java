package hu.progtech.setcardgame.bl;

public class Card {

    private int number;
    private int shape;
    private int shading;
    private int color;

    public Card() {
    }

    public Card(int number, int shape, int shading, int color) {
        this.number = number;
        this.shape = shape;
        this.shading = shading;
        this.color = color;

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getShading() {
        return shading;
    }

    public void setShading(int shading) {
        this.shading = shading;
    }

    public int getColor() {
        return color;
    }

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