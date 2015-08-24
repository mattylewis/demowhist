package mglewis.co.uk.demowhist.cards;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import mglewis.co.uk.demowhist.cards.Card.Suit;
import mglewis.co.uk.demowhist.cards.Card.Value;

/**
 * Created by MK on 23/08/2015.
 */
public class Deck {
    private List<Card> deck;

    public Deck() {
        deck = populateDeck();
        Collections.shuffle(deck);
    }

    private List<Card> populateDeck() {
        deck = new LinkedList<Card>();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                deck.add(new Card(suit, value));
            }
        }
        return deck;
    }

    public Hand deal(int cards) {
        List<Card> hand = new LinkedList<Card>();
        for (int i = 0; i < cards; i++) {
            Card dealtCard = deck.remove(0);
            hand.add(dealtCard);
        }
        return new Hand(hand);
    }

    public int getSize() {
        return deck.size();
    }
}
