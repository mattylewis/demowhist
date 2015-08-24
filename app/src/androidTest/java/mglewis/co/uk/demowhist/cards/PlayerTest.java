package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class PlayerTest extends TestCase {

    public void testPlayCard() throws Exception {
        Card testCard = new Card(Card.Suit.CLUBS, Card.Value.TWO);
        List<Card> cardList = new LinkedList<>();
        cardList.add(testCard);
        Player testPlayer = new Player("Test Player");
        testPlayer.setHand(new Hand(cardList));

        assertEquals("Checking played card is the same as the card in the hand", testCard, testPlayer.playCard(new Trick(Card.Suit.CLUBS)));
    }
}