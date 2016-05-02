package hu.progtech.setcardgame.bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Created by marianna on 2016.04.09..
 */
public class Deck {

    private List<Card> deck = new ArrayList<>();

    private int indexOfNextCard;

    public Deck() {
        makeNewDeck();
        suffleAllCards();
        indexOfNextCard = 0;
    }

    public Deck(List<Card> list) {
        deck = list;
        indexOfNextCard = 0;
    }

    public Card getNextCard() {
        Card card = null;
        if(indexOfNextCard < deck.size())
        {
            card = deck.get(indexOfNextCard);
            indexOfNextCard++;
        }
        return card;
    }

    public void resetNextCard() {
        indexOfNextCard = 0;
    }
    private void makeNewDeck() {

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
