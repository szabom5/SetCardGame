package hu.progtech.setcardgame.bl;

import java.util.List;
import java.util.Set;

/**
 * Created by marianna on 2016.04.10..
 */
public class SetOfCards {

    private List<Card> cardSet;

    public SetOfCards() {
    }

    public SetOfCards(List<Card> cardSet) {
        this.cardSet = cardSet;
    }

    public SetOfCards(Card c1, Card c2, Card c3) {
        cardSet.add(c1);
        cardSet.add(c2);
        cardSet.add(c3);

    }

    boolean isAllDiffrent(int a, int b, int c) {
        return (a != b && a != c && b != c)?true:false;
    }

    boolean isAllSame(int a, int b, int c) {
        return (a == b && a == c && b == b)?true:false;
    }

    boolean isNumberValid() {
        if(
                 isAllDiffrent(cardSet.get(0).getNumber(),cardSet.get(1).getNumber(),cardSet.get(2).getNumber())
                    ||
                    isAllSame(cardSet.get(0).getNumber(),cardSet.get(1).getNumber(),cardSet.get(2).getNumber())
                ) {
            return true;
        }
        return false;
    }

    boolean isShapeValid() {
        if(
                isAllDiffrent(cardSet.get(0).getShape(),cardSet.get(1).getShape(),cardSet.get(2).getShape())
                        ||
                        isAllSame(cardSet.get(0).getShape(),cardSet.get(1).getShape(),cardSet.get(2).getShape())
                ) {
            return true;
        }
        return false;
    }

    boolean isShadingValid() {
        if(
                isAllDiffrent(cardSet.get(0).getShading(),cardSet.get(1).getShading(),cardSet.get(2).getShading())
                        ||
                        isAllSame(cardSet.get(0).getShading(),cardSet.get(1).getShading(),cardSet.get(2).getShading())
                ) {
            return true;
        }
        return false;
    }

    boolean isColorValid() {
        if(
                isAllDiffrent(cardSet.get(0).getColor(),cardSet.get(1).getColor(),cardSet.get(2).getColor())
                        ||
                        isAllSame(cardSet.get(0).getColor(),cardSet.get(1).getColor(),cardSet.get(2).getColor())
                ) {
            return true;
        }
        return false;
    }

    boolean isSet() {
        if(!cardSet.isEmpty()) {
            if(isNumberValid() && isShapeValid() && isShadingValid() && isColorValid()) {
                return true;
            }
        }
        return false;
    }

    public List<Card> getCardSet() {
        return cardSet;
    }

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
}
