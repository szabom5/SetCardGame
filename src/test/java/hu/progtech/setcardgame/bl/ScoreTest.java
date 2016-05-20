package hu.progtech.setcardgame.bl;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ScoreTest {

    @Test
    public void testCalculateScore() throws Exception {
        Score score = new Score("valaki",3,1,1000);
        score.calculateScore();
        assertEquals(2099.0,score.getScore(),0.1);

    }

    @Test
    public void testGetNumberOfSetsFound() throws Exception {
        final Score score = new Score();
        final Field field = score.getClass().getDeclaredField("numberOfSetsFound");
        field.setAccessible(true);

        field.set(score, 1);
        assertEquals("field wasn't retrieved properly", 1, score.getNumberOfSetsFound());
    }

    @Test
    public void testSetNumberOfSetsFound() throws Exception {
        Score score = new Score();

        final Field field = score.getClass().getDeclaredField("numberOfSetsFound");
        field.setAccessible(true);

        score.setNumberOfSetsFound(1);
        assertEquals("Fields didn't match", field.get(score), 1);
    }

    @Test
    public void testGetNumberOfHintsUsed() throws Exception {
        final Score score = new Score();
        final Field field = score.getClass().getDeclaredField("numberOfHintsUsed");
        field.setAccessible(true);

        field.set(score, 1);
        assertEquals("field wasn't retrieved properly", 1, score.getNumberOfHintsUsed());
    }

    @Test
    public void testSetNumberOfHintsUsed() throws Exception {
        Score score = new Score();

        final Field field = score.getClass().getDeclaredField("numberOfHintsUsed");
        field.setAccessible(true);

        score.setNumberOfHintsUsed(1);
        assertEquals("Fields didn't match", field.get(score), 1);
    }

    @Test
    public void testGetTimeUsed() throws Exception {
        final Score score = new Score();
        final Field field = score.getClass().getDeclaredField("timeUsed");
        field.setAccessible(true);

        field.set(score, 1000);
        assertEquals("field wasn't retrieved properly", 1000, score.getTimeUsed());
    }

    @Test
    public void testSetTimeUsed() throws Exception {
        Score score = new Score();

        final Field field = score.getClass().getDeclaredField("timeUsed");
        field.setAccessible(true);

        score.setTimeUsed(1000);
        assertEquals("Fields didn't match", 1000L, field.get(score));
    }

    @Test
    public void testGetName() throws Exception {
        final Score score = new Score();
        final Field field = score.getClass().getDeclaredField("name");
        field.setAccessible(true);

        field.set(score, new SimpleStringProperty("example"));
        assertEquals("field wasn't retrieved properly", "example", score.getName());
    }

    @Test
    public void testSetName() throws Exception {
        Score score = new Score();

        final Field field = score.getClass().getDeclaredField("name");
        field.setAccessible(true);

        score.setName("example");
        assertEquals( new SimpleStringProperty("example").getValue(), ((SimpleStringProperty)field.get(score)).getValue() );
    }

    @Test
    public void testGetScore() throws Exception {
        final Score score = new Score();
        final Field field = score.getClass().getDeclaredField("score");
        field.setAccessible(true);

        field.set(score, new SimpleDoubleProperty(100.0));
        assertEquals( 100.0, score.getScore(),0.1);
    }

    @Test
    public void testSetScore() throws Exception {
        Score score = new Score();

        final Field field = score.getClass().getDeclaredField("score");
        field.setAccessible(true);

        score.setScore(100.0);
        assertEquals(new SimpleDoubleProperty(100.0).getValue(), ((SimpleDoubleProperty) field.get(score)).getValue());
    }

    @Test
    public void testEquals() throws Exception {
        Score score1 = new Score("example", 1000.0);
        Score score2 = new Score("example",1000.0);
        Score score3 = new Score();
        Score score4 = new Score("example2",1000.0);
        Score score5 = new Score(null,1000.0);
        Score score6 = new Score(null,1000.0);

        assertTrue(score1.equals(score2));
        assertTrue(score1.equals(score1));
        assertTrue(score5.equals(score6));

        assertFalse(score1.equals(score3));
        assertFalse(score1.equals(score4));
        assertFalse(score1.equals(score5));
        assertFalse(score5.equals(score1));
        assertFalse(score1.equals(null));
        assertFalse(score1.equals("example"));
    }

    @Test
    public void testHashCode() throws Exception {
        Score score1 = new Score("example", 1000.0);
        Score score2 = new Score("example",1000.0);
        Score score3 = new Score();

        assertEquals(score2.hashCode(), score1.hashCode());
        assertNotSame(score3.hashCode(), score1.hashCode());
    }

    @Test
    public void testToString() throws Exception {
       Score score = new Score("example",1000.0);
        String expected = "example:1000.0";
        assertEquals(expected, score.toString());
    }
}