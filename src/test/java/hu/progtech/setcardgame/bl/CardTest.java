package hu.progtech.setcardgame.bl;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by marianna on 2016.05.19..
 */
public class CardTest {

    @Test
    public void testGetNumber() throws Exception {
        final Card card = new Card();
        final Field field = card.getClass().getDeclaredField("number");
        field.setAccessible(true);

        field.set(card, 0);
        assertEquals("field wasn't retrieved properly", card.getNumber(), 0);
        field.set(card, 1);
        assertEquals("field wasn't retrieved properly", card.getNumber(), 1);
        field.set(card, 2);
        assertEquals("field wasn't retrieved properly", card.getNumber(), 2);
    }

    @Test
    public void testSetNumber() throws Exception {
        Card card = new Card();

        final Field field = card.getClass().getDeclaredField("number");
        field.setAccessible(true);

        card.setNumber(0);
        assertEquals("Fields didn't match", field.get(card), 0);
        card.setNumber(1);
        assertEquals("Fields didn't match", field.get(card), 1);
        card.setNumber(2);
        assertEquals("Fields didn't match", field.get(card), 2);
    }

    @Test
    public void testGetShape() throws Exception {
        final Card card = new Card();
        final Field field = card.getClass().getDeclaredField("shape");
        field.setAccessible(true);

        field.set(card, 0);
        assertEquals("field wasn't retrieved properly", card.getShape(), 0);
        field.set(card, 1);
        assertEquals("field wasn't retrieved properly", card.getShape(), 1);
        field.set(card, 2);
        assertEquals("field wasn't retrieved properly", card.getShape(), 2);
    }

    @Test
    public void testSetShape() throws Exception {
        Card card = new Card();

        final Field field = card.getClass().getDeclaredField("shape");
        field.setAccessible(true);

        card.setShape(0);
        assertEquals("Fields didn't match", field.get(card), 0);
        card.setShape(1);
        assertEquals("Fields didn't match", field.get(card), 1);
        card.setShape(2);
        assertEquals("Fields didn't match", field.get(card), 2);
    }

    @Test
    public void testGetShading() throws Exception {
        final Card card = new Card();
        final Field field = card.getClass().getDeclaredField("shading");
        field.setAccessible(true);

        field.set(card, 0);
        assertEquals("field wasn't retrieved properly", card.getShading(), 0);
        field.set(card, 1);
        assertEquals("field wasn't retrieved properly", card.getShading(), 1);
        field.set(card, 2);
        assertEquals("field wasn't retrieved properly", card.getShading(), 2);
    }

    @Test
    public void testSetShading() throws Exception {
        Card card = new Card();

        final Field field = card.getClass().getDeclaredField("shading");
        field.setAccessible(true);

        card.setShading(0);
        assertEquals("Fields didn't match", field.get(card), 0);
        card.setShading(1);
        assertEquals("Fields didn't match", field.get(card), 1);
        card.setShading(2);
        assertEquals("Fields didn't match", field.get(card), 2);
    }

    @Test
    public void testGetColor() throws Exception {
        final Card card = new Card();
        final Field field = card.getClass().getDeclaredField("color");
        field.setAccessible(true);

        field.set(card, 0);
        assertEquals("field wasn't retrieved properly", card.getColor(), 0);
        field.set(card, 1);
        assertEquals("field wasn't retrieved properly", card.getColor(), 1);
        field.set(card, 2);
        assertEquals("field wasn't retrieved properly", card.getColor(), 2);
    }

    @Test
    public void testSetColor() throws Exception {
        Card card = new Card();

        final Field field = card.getClass().getDeclaredField("color");
        field.setAccessible(true);

        card.setColor(0);
        assertEquals("Fields didn't match", field.get(card), 0);
        card.setColor(1);
        assertEquals("Fields didn't match", field.get(card), 1);
        card.setColor(2);
        assertEquals("Fields didn't match", field.get(card), 2);
    }

    @Test
    public void testEquals() throws Exception {
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(0,0,0,0);
        Card card3 = new Card(1,1,1,1);

        assertTrue(card1.equals(card2));
        assertFalse(card1.equals(card3));
        card3.setNumber(0);
        assertFalse(card1.equals(card3));
        card3.setShape(0);
        assertFalse(card1.equals(card3));
        card3.setShading(0);
        assertFalse(card1.equals(card3));
        assertFalse(card1.equals(null));
        assertFalse(card1.equals("valami"));
    }

    @Test
    public void testHashCode() throws Exception {
        Card card1 = new Card(0,0,0,0);
        Card card2 = new Card(0,0,0,0);
        Card card3 = new Card(1,1,1,1);

        assertEquals(card2.hashCode(), card1.hashCode());
        assertNotSame(card3.hashCode(), card1.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        Card card = new Card(0,1,2,0);
        String expected = "Card{color=0, shape=1, shading=2, number=0}\n";
        assertEquals(expected, card.toString());
    }
}