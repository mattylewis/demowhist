package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class DeckTest extends TestCase {

    public void testSize() throws Exception {
        Deck deck = new Deck();
        deck.deal(10);
        assertEquals("Checking that there should be 42 cards remaining in the deck", 42, deck.getSize());
    }
}