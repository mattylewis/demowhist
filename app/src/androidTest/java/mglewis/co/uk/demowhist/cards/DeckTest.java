package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;

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
        Card unwantedCard = new Card(Card.Suit.CLUBS, Card.Value.FOUR);
        unwantedCards.add(unwantedCard);
        Deck deck = new Deck(unwantedCards);

        assertEquals("Checking that there should be 51 cards in the deck", 51, deck.getSize());
        assertEquals("Checking that the " + unwantedCard + " isn't in the deck", false, deck.contains(unwantedCard));
    }

    public void testRemoveUnwantedCardsWithTwoCards() throws Exception {
        Deck deck = new Deck();

    }

    public void testRemoveUnwantedCardsWithFiftyOneCards() throws Exception {
        Deck deck = new Deck();

    }

}