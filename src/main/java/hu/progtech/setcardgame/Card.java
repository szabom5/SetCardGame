package hu.progtech.setcardgame;

import java.awt.*;

class Card {

    private int number;
    private Shape shape;
    private int shading;
    private Color color;

    public Card() {
    }

    public Card(int number, Shape shape, int shading, Color color) {
        this.number = number;
        this.shape = shape;
        this.shading = shading;
        this.color = color;
    }


}