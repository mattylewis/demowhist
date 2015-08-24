package mglewis.co.uk.demowhist.cards;

/**
 * Created by Matthew Lewis on 23/08/2015.
 */
public class Card implements Comparable<Card> {
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

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS
    }

    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
}
