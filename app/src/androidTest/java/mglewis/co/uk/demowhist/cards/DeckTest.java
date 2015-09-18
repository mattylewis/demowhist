package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;
import mglewis.co.uk.demowhist.cards.Card.Suit;
import mglewis.co.uk.demowhist.cards.Card.Value;


/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class DeckTest extends TestCase {

    public void testSize() throws Exception {
        Deck deck = new Deck();
        deck.deal(10);
        assertEquals("Checking that there should be 42 cards remaining in the deck", 42, deck.getSize());
    }

    public void testRemoveUnwantedCardsWithOneCard() throws Exception {
        List<Card> unwantedCards = new LinkedList<>();
        Card unwantedCard = new Card(Suit.CLUBS, Value.FOUR);
        unwantedCards.add(unwantedCard);
        Deck deck = new Deck(unwantedCards);

        assertEquals("Checking that there should be 51 cards in the deck", 51, deck.getSize());
        assertEquals("Checking that the " + unwantedCard + " isn't in the deck", false, deck.contains(unwantedCard));
    }

    public void testRemoveUnwantedCardsWithTwoCards() throws Exception {
        List<Card> unwantedCards = new LinkedList<>();
        unwantedCards.add(new Card(Suit.SPADES, Value.NINE));
        unwantedCards.add(new Card(Suit.DIAMONDS, Value.KING));
        Deck deck = new Deck(unwantedCards);

        assertEquals("Checking that there should be 50 cards in the deck", 50, deck.getSize());
        for (Card unwantedCard : unwantedCards) {
            assertEquals("Checking that the " + unwantedCard + " isn't in the deck", false, deck.contains(unwantedCard));
        }
    }

    private List<Card> getAllCards() {
        List<Card> cards = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
        return cards;
    }

    public void testRemoveUnwantedCardsWithFiftyOneCards() throws Exception {
        Card wantedCard = new Card(Suit.CLUBS, Value.FIVE);
        List<Card> unwantedCards = getAllCards();
        unwantedCards.remove(wantedCard);
        Deck deck = new Deck(unwantedCards);
        assertEquals("Checking that there should be 1 cards in the deck", 1, deck.getSize());
        assertEquals("Checking that the " + wantedCard + " is in the deck", true, deck.contains(wantedCard));
    }

    public void testRemoveUnwantedCardsWithFiftyTwoCards() throws Exception {
        List<Card> unwantedCards = getAllCards();
        Deck deck = new Deck(unwantedCards);
        assertEquals("Checking that there should be 0 cards in the deck", 0, deck.getSize());
    }

}