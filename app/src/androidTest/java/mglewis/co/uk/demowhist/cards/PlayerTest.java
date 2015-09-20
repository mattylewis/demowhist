package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class PlayerTest extends TestCase {

    public void testPlayCard() throws Exception {
//        Card testCard = new Card(Card.Suit.CLUBS, Card.Value.TWO);
//        List<Card> cardList = new LinkedList<>();
//        cardList.add(testCard);
//        Player testPlayer = new Player("Test Player");
//        testPlayer.setHand(new Hand(cardList));
//
//        assertEquals("Checking played card is the same as the card in the hand", testCard, testPlayer.playCard(new Trick(Card.Suit.CLUBS)));


    }

    private Card invokeGetBestCard(Player player, Map<Card, Integer> cardScores) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class[] getBestCardArgument = new Class[1];
        getBestCardArgument[0] = Map.class;
        Method getBestCard = player.getClass().getDeclaredMethod("getBestCard", getBestCardArgument);
        getBestCard.setAccessible(true);
        return (Card)getBestCard.invoke(player, cardScores);
    }

    private Map<Card, Integer> getCardScores() {
        Map<Card, Integer> cardScores = new HashMap<Card, Integer>();
        cardScores.put(new Card(Card.Suit.CLUBS, Card.Value.ACE), 2);
        cardScores.put(new Card(Card.Suit.HEARTS, Card.Value.THREE), 10);
        cardScores.put(new Card(Card.Suit.DIAMONDS, Card.Value.KING), -12);
        return cardScores;
    }

    public void testGetBestCard() throws Exception {
        Player player = new Player("TestPlayer");
        Card bestCard = invokeGetBestCard(player, getCardScores());
        Card expectedCard = new Card(Card.Suit.HEARTS, Card.Value.THREE);
        assertEquals("Checking best card is " + expectedCard, expectedCard, bestCard);
    }
}