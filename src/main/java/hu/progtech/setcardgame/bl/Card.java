package hu.progtech.setcardgame.bl;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private int number;
    private Shape shape;
    private int shading;
    private Color color;

    public List<Shape> listOfShapes = new ArrayList<Shape>();
    public List<Color> listOfColors = new ArrayList<Color>();

    public Card() {
        listOfShapes.add(new Circle());
        listOfShapes.add(new Rectangle());
        listOfShapes.add(new Polygon());

        listOfColors.add(Color.BLUE);
        listOfColors.add(Color.RED);
        listOfColors.add(Color.YELLOW);
    }

    public Card(int number, Shape shape, int shading, Color color) {
        super();
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

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getShading() {
        return shading;
    }

    public void setShading(int shading) {
        this.shading = shading;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (getNumber() != card.getNumber()) return false;
        if (getShading() != card.getShading()) return false;
        if (getShape() != null ? !getShape().equals(card.getShape()) : card.getShape() != null) return false;
        return getColor() != null ? getColor().equals(card.getColor()) : card.getColor() == null;

    }

    @Override
    public int hashCode() {
        int result = getNumber();
        result = 31 * result + (getShape() != null ? getShape().hashCode() : 0);
        result = 31 * result + getShading();
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
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