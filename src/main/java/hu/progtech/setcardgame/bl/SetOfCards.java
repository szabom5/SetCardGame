package hu.progtech.setcardgame.bl;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code SetOfCards} class represents a Set made up by three {@link hu.progtech.setcardgame.bl.Card}s.
 * <p>To use this class, its constructor needs to be called, for example:</p>
     <pre>
     SetOfCards setOfCards = new SetOfCards();
    </pre>
    <p>or with 1 parameter, a list of cards to be stored as a set: </p>
    <pre>
    SetOfCards setOfCards = new SetOfCards(new ArrayList&lt;Card&gt;());
    </pre>
    <p> or with 3 parameters, such as 3 single cards: </p>
    <pre>
    SetOfCards setOfCards = new SetOfCards(new Card(), new Card(), new Card());
     </pre>
 */

public class SetOfCards {

    /**
     * A {@link java.util.List} containing the three {@link hu.progtech.setcardgame.bl.Card}s.
     */

    private List<Card> cardSet;

    /**
     * Initializes a newly created {@code SetOfCards} object.
     */

    public SetOfCards() {
        cardSet = new ArrayList<>();
    }

    /**
     * Constructs a new {@code SetOfCards} object by setting the value of the {@link java.util.List} of {@link hu.progtech.setcardgame.bl.Card}s.
     * @param cardSet A {@link java.util.List} containing the three {@link hu.progtech.setcardgame.bl.Card}s

     */

    public SetOfCards(List<Card> cardSet) {
        this.cardSet = cardSet;
    }

    /**
     * Constructs a new {@code SetOfCards} object by setting the value of the {@link java.util.List} of {@link hu.progtech.setcardgame.bl.Card}s with three {@link hu.progtech.setcardgame.bl.Card} objects.
     * @param c1 A {@link hu.progtech.setcardgame.bl.Card} object
     * @param c2 A {@link hu.progtech.setcardgame.bl.Card} object
     * @param c3 A {@link hu.progtech.setcardgame.bl.Card} object
     */

    public SetOfCards(Card c1, Card c2, Card c3) {
        cardSet = new ArrayList<>();
        cardSet.add(c1);
        cardSet.add(c2);
        cardSet.add(c3);

    }

    /**
     * Adds a {@link hu.progtech.setcardgame.bl.Card} object to the {@link java.util.List} of {@link hu.progtech.setcardgame.bl.Card}s if there is less than three cards in it.
     * @param card A {@link hu.progtech.setcardgame.bl.Card} object
     */

    public void addCardToSet(Card card) {
        if(cardSet.size()<3) {
            cardSet.add(card);
        }
    }

    /**
     * Compares three values of the same property of a {@link hu.progtech.setcardgame.bl.Card}.
     * @param a The first value
     * @param b The second value
     * @param c The third value
     * @return True if the three values are all different, false if not.
     */

    private boolean isAllDifferent(int a, int b, int c) {
        return (a != b && a != c && b != c);
    }

    /**
     * Compares three values of the same property of a {@link hu.progtech.setcardgame.bl.Card}.
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
        return isAllDifferent(cardSet.get(0).getNumber(),cardSet.get(1).getNumber(),cardSet.get(2).getNumber())
               ||
               isAllSame(cardSet.get(0).getNumber(),cardSet.get(1).getNumber(),cardSet.get(2).getNumber());
    }

    /**
     * Returns true if the shape properties of the three cards in the list are compatible to make a Set.
     * @return Returns true if the shape properties of the three cards in the list are compatible to make a Set, false if not
     */

    private boolean isShapeValid() {
        return (isAllDifferent(cardSet.get(0).getShape(),cardSet.get(1).getShape(),cardSet.get(2).getShape())
                ||
                isAllSame(cardSet.get(0).getShape(),cardSet.get(1).getShape(),cardSet.get(2).getShape())
        );

    }

    /**
     * Returns true if the shading properties of the three cards in the list are compatible to make a Set.
     * @return Returns true if the shading properties of the three cards in the list are compatible to make a Set, false if not
     */

    private boolean isShadingValid() {
        return isAllDifferent(cardSet.get(0).getShading(),cardSet.get(1).getShading(),cardSet.get(2).getShading())
               ||
               isAllSame(cardSet.get(0).getShading(),cardSet.get(1).getShading(),cardSet.get(2).getShading());
    }

    /**
     * Returns true if the color properties of the three cards in the list are compatible to make a Set.
     * @return Returns true if the color properties of the three cards in the list are compatible to make a Set, false if not
     */

    private boolean isColorValid() {
        return isAllDifferent(cardSet.get(0).getColor(),cardSet.get(1).getColor(),cardSet.get(2).getColor())
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
     * Removes a {@link hu.progtech.setcardgame.bl.Card} from the {@code cardSet}.
     * @param c The {@link hu.progtech.setcardgame.bl.Card} to remove
     */

    public void removeFromSetOfCards(Card c) {
        cardSet.remove(c);
    }

    /**
     * Returns the {@link java.util.List} of {@link hu.progtech.setcardgame.bl.Card}s.
     * @return The {@link java.util.List} of {@link hu.progtech.setcardgame.bl.Card}s
     */

    public List<Card> getCardSet() {
        return cardSet;
    }

    /**
     * Sets the {@link java.util.List} of {@link hu.progtech.setcardgame.bl.Card}s.
     * @param cardSet The {@link java.util.List} of {@link hu.progtech.setcardgame.bl.Card}s
     */

    public void setCardSet(List<Card> cardSet) {
        this.cardSet = cardSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetOfCards that = (SetOfCards) o;

        if(that.cardSet.size() == cardSet.size()){
            switch (cardSet.size()) {
                case 1: return cardSet.get(0).equals(that.cardSet.get(0));
                case 2: return cardSet.get(0).equals(that.cardSet.get(0)) &&
                        cardSet.get(1).equals(that.cardSet.get(1));
                case 3: return cardSet.get(0).equals(that.cardSet.get(0)) &&
                        cardSet.get(1).equals(that.cardSet.get(1)) &&
                        cardSet.get(2).equals(that.cardSet.get(2));
                default: return that.cardSet.isEmpty();
            }
        }else {
            return false;
        }


    }

    @Override
    public int hashCode() {
        return !cardSet.isEmpty() ? cardSet.stream().mapToInt((x) -> x.hashCode()).sum() : 0;
    }

    @Override
    public String toString() {
        return "SetOfCards{" +
                "cardSet= " + cardSet.get(0).toString() + cardSet.get(1).toString()+ cardSet.get(2).toString() +
                '}';
    }
}
