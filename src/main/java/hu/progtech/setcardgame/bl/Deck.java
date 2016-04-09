package hu.progtech.setcardgame.bl;

import hu.progtech.setcardgame.bl.Card;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.random;

/**
 * Created by marianna on 2016.04.09..
 */
public class Deck {

    List<Card> deck = new ArrayList<Card>();

    public Deck() {
        makeNewDeck();
        suffleAllCards();
    }

    private void makeNewDeck() {

        Card cardInit = new Card();
        for(int number = 0; number < 3; number++) {
            for(Shape shape : cardInit.listOfShapes) {
                for(int shading = 0; shading < 3; shading++) {
                    for(Color color : cardInit.listOfColors) {
                        deck.add(new Card(number,shape,shading,color));
                    }
                }
            }
        }

    }

    private void suffleAllCards() {
        Collections.shuffle(deck,new Random());
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }
}
