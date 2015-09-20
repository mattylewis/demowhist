package mglewis.co.uk.demowhist.cards;

/**
 * Created by Matthew Lewis on 23/08/2015.
 */
public class Card implements Comparable<Card> {
    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS
    }

    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getSuitOrdinal() {
        return suit.ordinal();
    }

    public Value getValue() {
        return value;
    }

    public int getValueOrdinal() {
        return value.ordinal();
    }

    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public int compareTo(Card otherCard) {
        if (getSuitOrdinal() > otherCard.getSuitOrdinal()) {
            return 1;
        } else if (getSuitOrdinal() < otherCard.getSuitOrdinal()) {
            return -1;
        } else if (getValueOrdinal() > otherCard.getValueOrdinal()) {
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Card)) {
            return false;
        } else if (object == this) {
            return true;
        } else if (object.hashCode() == this.hashCode()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1 + ((getSuitOrdinal() * 193) + (getValueOrdinal() * 3907));
    }

}
