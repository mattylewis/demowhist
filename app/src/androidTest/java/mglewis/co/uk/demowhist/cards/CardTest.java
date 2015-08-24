package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class CardTest extends TestCase {

    public void testCompareTo() throws Exception {
        testCompareToSameSuit();
        testCompareToSameValue();
        testCompareToDifferentCards();

    }

    private void testCompareToSameSuit() {
        Card aceOfClubs = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card jackOfClubs = new Card(Card.Suit.CLUBS, Card.Value.JACK);
        Card twoOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Value.TWO);
        Card sixOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Value.SIX);
        assertEquals("Expecting " + aceOfClubs + " to be greater than " + jackOfClubs + " and therefore return 1", 1, aceOfClubs.compareTo(jackOfClubs));
        assertEquals("Expecting " + jackOfClubs + " to be less than " + aceOfClubs + " and therefore return 1", -1, jackOfClubs.compareTo(aceOfClubs));
        assertEquals("Expecting " + twoOfDiamonds + " to be less than " + sixOfDiamonds + " and therefore return -1", -1, twoOfDiamonds.compareTo(sixOfDiamonds));
        assertEquals("Expecting " + sixOfDiamonds + " to be greater than " + twoOfDiamonds + " and therefore return 1", 1, sixOfDiamonds.compareTo(twoOfDiamonds));
    }

    // note that required suit order is: CLUBS, DIAMONDS, SPADES, HEARTS
    private void testCompareToSameValue() {
        Card queenOfSpades = new Card(Card.Suit.SPADES, Card.Value.QUEEN);
        Card queenOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Value.QUEEN);
        Card queenOfHearts = new Card(Card.Suit.HEARTS, Card.Value.QUEEN);
        Card queenOfClubs = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        assertEquals("Expecting " + queenOfSpades + " to be greater than " + queenOfDiamonds + " and therefore return 1", 1, queenOfSpades.compareTo(queenOfDiamonds));
        assertEquals("Expecting " + queenOfDiamonds + " to be less than " + queenOfSpades + " and therefore return -1", -1, queenOfDiamonds.compareTo(queenOfSpades));
        assertEquals("Expecting " + queenOfHearts + " to be greater than " + queenOfClubs + " and therefore return 1", 1, queenOfHearts.compareTo(queenOfClubs));
        assertEquals("Expecting " + queenOfClubs + " to be less than " + queenOfSpades + " and therefore return -1", -1, queenOfClubs.compareTo(queenOfSpades));
    }

    private void testCompareToDifferentCards() {
        Card queenOfSpades = new Card(Card.Suit.SPADES, Card.Value.QUEEN);
        Card aceOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        Card twoOfHearts = new Card(Card.Suit.HEARTS, Card.Value.QUEEN);
        Card sixOfClubs = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        assertEquals("Expecting " + queenOfSpades + " to be greater than " + aceOfDiamonds + " and therefore return 1", 1, queenOfSpades.compareTo(aceOfDiamonds));
        assertEquals("Expecting " + aceOfDiamonds + " to be less than " + queenOfSpades + " and therefore return -1", -1, aceOfDiamonds.compareTo(queenOfSpades));
        assertEquals("Expecting " + twoOfHearts + " to be greater than " + sixOfClubs + " and therefore return 1", 1, twoOfHearts.compareTo(sixOfClubs));
        assertEquals("Expecting " + sixOfClubs + " to be less than " + queenOfSpades + " and therefore return -1", -1, sixOfClubs.compareTo(queenOfSpades));
    }
}