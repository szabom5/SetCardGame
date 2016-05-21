package hu.progtech.setcardgame.bl;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void testGetNextCard() throws Exception {
        Deck deck = new Deck();
        Card card;

        for(int i = 0;i < deck.getDeck().size();i++){
            card = deck.getDeck().get(i);
            assertEquals(card, deck.getNextCard());
        }

        assertEquals(null, deck.getNextCard());
    }

    @Test
    public void testResetNextCard() throws Exception {
        Deck deck = new Deck();
        Card card0 = deck.getDeck().get(0);
        deck.getNextCard();
        deck.getNextCard();

        deck.resetNextCard();
        assertEquals(card0,deck.getNextCard());
    }

    @Test
    public void testHint() throws Exception {
        Deck deck = new Deck();
        SetOfCards setOfCards = new SetOfCards();
        List<Card> list = new ArrayList<>();
        list.add(deck.getNextCard());
        list.add(deck.getNextCard());

        assertFalse(deck.hint(deck.getDeck()).equals(setOfCards));
        assertTrue(deck.hint(null).equals(setOfCards));
        assertTrue(deck.hint(list).equals(setOfCards));
        list.add(list.get(0));
        assertTrue(deck.hint(list).equals(setOfCards));
        list.add(null);
        assertTrue(deck.hint(list).equals(setOfCards));
        list.clear();
        list.add(deck.getNextCard());
        list.add(null);
        list.add(null);
        assertTrue(deck.hint(list).equals(setOfCards));
    }

    @Test
    public void testToString() throws Exception {
        Deck deck = new Deck();
        String expected = "Deck {deck="+deck.getDeck().toString()+"}";
        assertEquals(expected, deck.toString());
    }

    @Test
    public void testGetDeck() throws Exception {
        List<Card> list = new ArrayList<>();
        list.add(new Card(0,0,0,0));
        final Deck deck = new Deck(list);
        final Field field = deck.getClass().getDeclaredField("deck");
        field.setAccessible(true);

        field.set(deck, list);
        assertEquals("field wasn't retrieved properly", list, deck.getDeck());
    }
}