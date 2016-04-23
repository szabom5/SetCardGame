package hu.progtech.setcardgame.bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Created by marianna on 2016.04.09..
 */
public class Deck {

    List<Card> deck = new ArrayList<Card>();

    public Deck() {
        makeNewDeck();
        suffleAllCards();
    }

    public Deck(List<Card> list) {
        deck = list;
    }

    private void makeNewDeck() {

        Card cardInit = new Card();
        for(int number = 0; number < 3; number++) {
            for(int shape = 0; shape < 3; shape++) {
                for(int shading = 0; shading < 3; shading++) {
                    for(int color = 0; color < 3; color++) {
                        deck.add(new Card(number,shape,shading,color));
                    }
                }
            }
        }

    }

    private void suffleAllCards() {
        Collections.shuffle(deck,new Random());
    }

    public List<Card> getDeck() {
        return deck;
    }
    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }
}
