package hu.progtech.setcardgame.bl;

import java.util.ArrayList;
import java.util.List;

/**
 * SetOfCards class represents a Set made up by three Cards.
 */

public class SetOfCards {

    /**
     * A List containing the three Cards.
     */

    private List<Card> cardSet;

    /**
     * Initializes a newly created SetOfCards object.
     */

    public SetOfCards() {
        cardSet = new ArrayList<>();
    }

    /**
     * Constructs a new SetOfCards object by setting the value of the List of Cards.
     * @param cardSet A List containing the three Cards

     */

    public SetOfCards(List<Card> cardSet) {
        this.cardSet = cardSet;
    }

    /**
     * Constructs a new SetOfCards object by setting the value of the List of Cards with three Card objects.
     * @param c1 A Card object
     * @param c2 A Card object
     * @param c3 A Card object
     */

    public SetOfCards(Card c1, Card c2, Card c3) {
        cardSet = new ArrayList<>();
        cardSet.add(c1);
        cardSet.add(c2);
        cardSet.add(c3);

    }

    /**
     * Adds a Card object to the List of Cards if there is less than three Cards in it.
     * @param card A Card object
     */

    public void addCardToSet(Card card) {
        if(cardSet.size()<3) {
            cardSet.add(card);
        }
    }

    /**
     * Compares three values of the same property of a Card.
     * @param a The first value
     * @param b The second value
     * @param c The third value
     * @return True if the three values are all different, false if not.
     */

    private boolean isAllDiffrent(int a, int b, int c) {
        return (a != b && a != c && b != c);
    }

    /**
     * Compares three values of the same property of a Card.
     * @param a The first value
     * @param b The second value
     * @param c The third value
     * @return True if the three values are all the same, false if not.
     */

    private boolean isAllSame(int a, int b, int c) {
        return (a == b && a == c);
    }

    /**
     * Returns true if the number properties of the three cards in the list are compatible to make a Set.
     * @return Returns true if the number properties of the three cards in the list are compatible to make a Set, false if not
     */

    private boolean isNumberValid() {
        return isAllDiffrent(cardSet.get(0).getNumber(),cardSet.get(1).getNumber(),cardSet.get(2).getNumber())
               ||
               isAllSame(cardSet.get(0).getNumber(),cardSet.get(1).getNumber(),cardSet.get(2).getNumber());
    }

    /**
     * Returns true if the shape properties of the three cards in the list are compatible to make a Set.
     * @return Returns true if the shape properties of the three cards in the list are compatible to make a Set, false if not
     */

    private boolean isShapeValid() {
        return (isAllDiffrent(cardSet.get(0).getShape(),cardSet.get(1).getShape(),cardSet.get(2).getShape())
                ||
                isAllSame(cardSet.get(0).getShape(),cardSet.get(1).getShape(),cardSet.get(2).getShape())
        );

    }

    /**
     * Returns true if the shading properties of the three cards in the list are compatible to make a Set.
     * @return Returns true if the shading properties of the three cards in the list are compatible to make a Set, false if not
     */

    private boolean isShadingValid() {
        return isAllDiffrent(cardSet.get(0).getShading(),cardSet.get(1).getShading(),cardSet.get(2).getShading())
               ||
               isAllSame(cardSet.get(0).getShading(),cardSet.get(1).getShading(),cardSet.get(2).getShading());
    }

    /**
     * Returns true if the color properties of the three cards in the list are compatible to make a Set.
     * @return Returns true if the color properties of the three cards in the list are compatible to make a Set, false if not
     */

    private boolean isColorValid() {
        return isAllDiffrent(cardSet.get(0).getColor(),cardSet.get(1).getColor(),cardSet.get(2).getColor())
               ||
               isAllSame(cardSet.get(0).getColor(),cardSet.get(1).getColor(),cardSet.get(2).getColor());

    }

    /**
     * Returns true if the three Cards in the cardSet are compatible to form a Set and false if not.
     * @return Returns true if the three Cards in the cardSet are compatible to form a Set, false if not
     */

    public boolean isSet() {
        return (cardSet.size()==3 && isNumberValid() && isShapeValid() && isShadingValid() && isColorValid());
    }

    /**
     * Returns the List of Cards.
     * @return The List of Cards
     */

    public List<Card> getCardSet() {
        return cardSet;
    }

    /**
     * Sets the list of Cards.
     * @param cardSet The List of Cards
     */

    public void setCardSet(List<Card> cardSet) {
        this.cardSet = cardSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetOfCards that = (SetOfCards) o;

        return cardSet != null ? cardSet.equals(that.cardSet) : that.cardSet == null;

    }

    @Override
    public int hashCode() {
        return cardSet != null ? cardSet.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SetOfCards{" +
                "cardSet=" + cardSet +
                '}';
    }

    public void removeFromSetOfCards(Card c) {
        cardSet.remove(c);
    }
}
