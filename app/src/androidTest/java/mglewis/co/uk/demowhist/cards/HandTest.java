package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;

import mglewis.co.uk.demowhist.cards.Card.Suit;
import mglewis.co.uk.demowhist.cards.Card.Value;
import mglewis.co.uk.demowhist.game.Trick;
import mglewis.co.uk.demowhist.player.HumanPlayer;

/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class HandTest extends TestCase {

    private Hand createHand() {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Suit.CLUBS, Value.TWO));
        cardList.add(new Card(Suit.CLUBS, Value.SIX));
        cardList.add(new Card(Suit.DIAMONDS, Value.TWO));
        cardList.add(new Card(Suit.DIAMONDS, Value.THREE));
        cardList.add(new Card(Suit.DIAMONDS, Value.ACE));
        cardList.add(new Card(Suit.HEARTS, Value.TWO));
        cardList.add(new Card(Suit.HEARTS, Value.QUEEN));
        cardList.add(new Card(Suit.HEARTS, Value.KING));
        return new Hand(cardList);
    }

    public void testGetValidCards() throws Exception {
        testGetValidCardsWhereMakingLeadPlay();
        testGetValidCardsWhereMakingSecondPlay();
        testGetValidCardsWhereNoLeadCard();
    }

    private void testGetValidCardsWhereMakingLeadPlay() {
        Hand hand = createHand();
        Trick trick = new Trick(Suit.CLUBS);
        assertEquals("Checking that all cards are valid when making initial play", hand.size(), hand.getValidCards(trick).size());
    }

    private void testGetValidCardsWhereMakingSecondPlay() {
        Hand hand = createHand();
        Trick trick = new Trick(Suit.DIAMONDS);
        trick.makePlay(new Play(new HumanPlayer("Matt"), new Card(Suit.CLUBS, Value.NINE)));
        assertEquals("Checking only 2 cards are valid when making second play", 2, hand.getValidCards(trick).size());
    }

    private void testGetValidCardsWhereNoLeadCard() {
        Hand hand = createHand();
        Trick trick = new Trick(Suit.SPADES);
        trick.makePlay(new Play(new HumanPlayer("Matt"), new Card(Suit.SPADES, Value.NINE)));
        trick.makePlay(new Play(new HumanPlayer("Katie"), new Card(Suit.SPADES, Value.TEN)));
        assertEquals("Checking only 2 cards are valid when making second play", 8, hand.getValidCards(trick).size());
    }
}