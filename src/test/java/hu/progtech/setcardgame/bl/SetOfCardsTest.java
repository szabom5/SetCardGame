package hu.progtech.setcardgame.bl;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by marianna on 2016.05.20..
 */
public class SetOfCardsTest {

    @Test
    public void testAddCardToSet() throws Exception {
        SetOfCards setOfCards = new SetOfCards();
        Card card = new Card(1,1,1,1);
        setOfCards.addCardToSet(card);
        assertTrue(setOfCards.getCardSet().contains(card));
        assertFalse(setOfCards.getCardSet().contains(new Card(2,2,2,2)));
        setOfCards.addCardToSet(card);
        setOfCards.addCardToSet(card);
        setOfCards.addCardToSet(new Card(2,2,2,2));
        assertTrue(setOfCards.getCardSet().contains(card));
        assertFalse(setOfCards.getCardSet().contains(new Card(2,2,2,2)));
    }

    @Test
    public void testIsSet() throws Exception {
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(1,1,1,1);
        Card card3 = new Card(2,2,2,2);
        SetOfCards setOfCards = new SetOfCards(card1, card2, card3);
        assertTrue(setOfCards.isSet());
        setOfCards = new SetOfCards(card1,card1, card1);
        assertTrue(setOfCards.isSet());
        card1 = new Card(0,0,0,0);
        card2 = new Card(0,1,1,1);
        card3 = new Card(0,2,2,2);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertTrue(setOfCards.isSet());
        card1 = new Card(0,0,0,0);
        card2 = new Card(1,0,1,1);
        card3 = new Card(2,0,2,2);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertTrue(setOfCards.isSet());
        card1 = new Card(0,0,0,0);
        card2 = new Card(1,1,0,1);
        card3 = new Card(2,2,0,2);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertTrue(setOfCards.isSet());
        card1 = new Card(0,0,0,0);
        card2 = new Card(1,1,1,0);
        card3 = new Card(2,2,2,0);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertTrue(setOfCards.isSet());

        card1 = new Card(0,0,0,0);
        card2 = new Card(0,1,1,1);
        card3 = new Card(2,2,2,2);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertFalse(setOfCards.isSet());
        card1 = new Card(0,0,0,0);
        card2 = new Card(1,0,1,1);
        card3 = new Card(2,2,2,2);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertFalse(setOfCards.isSet());
        card1 = new Card(0,0,0,0);
        card2 = new Card(1,1,0,1);
        card3 = new Card(2,2,2,2);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertFalse(setOfCards.isSet());
        card1 = new Card(0,0,0,0);
        card2 = new Card(1,1,1,0);
        card3 = new Card(2,2,2,2);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertFalse(setOfCards.isSet());
        card1 = new Card(0,0,0,0);
        card2 = new Card(1,1,1,1);
        card3 = new Card(2,2,2,1);
        setOfCards = new SetOfCards(card1,card2, card3);
        assertFalse(setOfCards.isSet());
        setOfCards.removeFromSetOfCards(card1);
        assertFalse(setOfCards.isSet());
    }

    @Test
    public void testGetCardSet() throws Exception {
        final SetOfCards setOfCards = new SetOfCards();
        final Field field = setOfCards.getClass().getDeclaredField("cardSet");
        field.setAccessible(true);
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(1,1,1,1);
        Card card3 = new Card(2,2,2,2);
        List list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        list.add(card3);
        field.set(setOfCards, list);
        assertEquals("field wasn't retrieved properly", setOfCards.getCardSet(), list);
        list.clear();
        field.set(setOfCards, list);
        assertEquals("field wasn't retrieved properly", setOfCards.getCardSet(), list);
    }

    @Test
    public void testSetCardSet() throws Exception {
        final SetOfCards setOfCards = new SetOfCards();
        final Field field = setOfCards.getClass().getDeclaredField("cardSet");
        field.setAccessible(true);
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(1,1,1,1);
        Card card3 = new Card(2,2,2,2);
        List list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        list.add(card3);
        setOfCards.setCardSet(list);
        assertEquals("Fields didn't match", field.get(setOfCards), list );
        list.clear();
        setOfCards.setCardSet(list);
        assertEquals("Fields didn't match", field.get(setOfCards), list );
    }

    @Test
    public void testEquals() throws Exception {
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(1,1,1,1);
        Card card3 = new Card(2,2,2,2);
        Card card4 = new Card(0,1,0,1);
        final SetOfCards setOfCards1 = new SetOfCards(card1, card2, card3);
        final SetOfCards setOfCards2 = new SetOfCards(card1, card2, card3);
        final SetOfCards setOfCards3 = new SetOfCards(card1, card2, card4);
        final SetOfCards setOfCards4 = new SetOfCards();
        final SetOfCards setOfCards5 = new SetOfCards();
        assertTrue(setOfCards1.equals(setOfCards2));
        assertTrue(setOfCards1.equals(setOfCards1));
        assertFalse(setOfCards1.equals(setOfCards3));
        assertFalse(setOfCards1.equals(null));
        assertFalse(setOfCards1.equals("valami"));
        assertFalse(setOfCards1.equals(setOfCards4));
        assertTrue(setOfCards4.equals(setOfCards5));

    }

    @Test
    public void testEquals2() throws Exception {
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(1,1,1,1);
        Card card3 = new Card(2,2,2,2);
        Card card4 = new Card(0,1,0,1);
        final SetOfCards setOfCards4 = new SetOfCards();
        final SetOfCards setOfCards5 = new SetOfCards();

        setOfCards4.addCardToSet(card1);
        setOfCards5.addCardToSet(card1);
        assertTrue(setOfCards4.equals(setOfCards5));
        setOfCards4.addCardToSet(card2);
        setOfCards5.addCardToSet(card3);
        assertFalse(setOfCards4.equals(setOfCards5));
        setOfCards4.removeFromSetOfCards(card2);
        setOfCards4.addCardToSet(card3);
        assertTrue(setOfCards4.equals(setOfCards5));
    }

    @Test
    public void testEquals3() throws Exception {
        Card card1 = new Card(0, 0, 0, 0);
        Card card2 = new Card(1, 1, 1, 1);
        Card card3 = new Card(2, 2, 2, 2);
        Card card4 = new Card(0, 1, 2, 0);
        SetOfCards setOfCards4 = new SetOfCards(card1, card2, card3);
        SetOfCards setOfCards5 = new SetOfCards(card1, card2, card3);
        assertTrue(setOfCards4.equals(setOfCards5));
        setOfCards4 = new SetOfCards(card1, card2, card4);
        assertFalse(setOfCards4.equals(setOfCards5));
        setOfCards4 = new SetOfCards(card1, card4, card3);
        assertFalse(setOfCards4.equals(setOfCards5));
        setOfCards4 = new SetOfCards(card4, card2, card3);
        assertFalse(setOfCards4.equals(setOfCards5));
    }

    @Test
    public void testHashCode() throws Exception {
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(1,1,1,1);
        Card card3 = new Card(2,2,2,2);
        Card card4 = new Card(0,1,0,1);
        final SetOfCards setOfCards1 = new SetOfCards(card1, card2, card3);
        final SetOfCards setOfCards2 = new SetOfCards(card1, card2, card3);
        final SetOfCards setOfCards3 = new SetOfCards(card1, card2, card4);
        final SetOfCards setOfCards4 = new SetOfCards();
        assertEquals(setOfCards1.hashCode(), setOfCards2.hashCode());
        assertNotSame(setOfCards1.hashCode(), setOfCards3.hashCode());
        assertEquals(0, setOfCards4.hashCode());

    }

    @Test
    public void testToString() throws Exception {
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(1,1,1,1);
        Card card3 = new Card(2,2,2,2);
        final SetOfCards setOfCards = new SetOfCards(card1, card2, card3);
        String expected = "SetOfCards{cardSet= "+ card1.toString()+ card2.toString()+ card3.toString() +"}";
        assertEquals(expected, setOfCards.toString());
    }

    @Test
    public void testRemoveFromSetOfCards() throws Exception {
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(1,1,1,1);
        Card card3 = new Card(2,2,2,2);
        List list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        list.add(card3);
        final SetOfCards setOfCards = new SetOfCards(list);
        final Field field = setOfCards.getClass().getDeclaredField("cardSet");
        field.setAccessible(true);
        setOfCards.removeFromSetOfCards(card1);
        list.remove(card1);
        assertEquals("Fields didn't match", field.get(setOfCards), list );
    }
}