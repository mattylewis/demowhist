package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.util.Arrays;
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

    public void testCopyForSimulation() {
        Player initialPlayer = new Player("TestPlayer");
        Player copiedPlayer = initialPlayer.copyForSimulation();
        assertEquals("Checking that the contents of the two player objects is the same", true, initialPlayer.equals(copiedPlayer));
        assertEquals("Checking that the references to the two player objects is different", false, initialPlayer == copiedPlayer);
    }

    private int invokeCalculateScore(Player player, int targetScore) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class[] targetScoreArgument = new Class[1];
        targetScoreArgument[0] = int.class;
        Method calculateScore = player.getClass().getDeclaredMethod("calculateScore", targetScoreArgument);
        calculateScore.setAccessible(true);
        return (Integer)calculateScore.invoke(player, targetScore);
    }

    public void testCalculateTargetScore() throws Exception {
        Player player = new Player("Test Player");
        player.setTargetScore(0);
        assertEquals("Checking that with score of 0 and target of 0 returns 10", 10, invokeCalculateScore(player, 0));
        assertEquals("Checking that with score of 1 and target of 0 returns 1", 1, invokeCalculateScore(player, 1));
        assertEquals("Checking that with score of 7 and target of 0 returns 7", 7, invokeCalculateScore(player, 7));
        player.setTargetScore(2);
        assertEquals("Checking that with score of 0 and target of 2 returns 10", 0, invokeCalculateScore(player, 0));
        assertEquals("Checking that with score of 2 and target of 2 returns 10", 12, invokeCalculateScore(player, 2));
        assertEquals("Checking that with score of 10 and target of 2 returns 7", 7, invokeCalculateScore(player, 7));
    }

    private Map<Player, Integer> createIncompleteRoundResults() {
        Map<Player, Integer> roundResults = new HashMap<>();
        roundResults.put(new Player("Opponent1"), 10);
        roundResults.put(new Player("Opponent2"), 1);
        roundResults.put(new Player("Opponent3"), 6);
        return roundResults;
    }

    private int invokeScoreStrategySuccess(Player player, Map<Player, Integer> roundResults) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class[] scoreStrategySuccessArgument = new Class[1];
        scoreStrategySuccessArgument[0] = Map.class;
        Method scoreStrategySuccess = player.getClass().getDeclaredMethod("scoreStrategySuccess", scoreStrategySuccessArgument);
        scoreStrategySuccess.setAccessible(true);
        return (Integer)scoreStrategySuccess.invoke(player, roundResults);
    }

    private void testScoreStrategySuccessLowScore(Player player, Map<Player, Integer> roundResults) throws Exception {
        roundResults.put(player, 1);
        assertEquals("Checking player strategy score", -15, invokeScoreStrategySuccess(player, roundResults));
    }

    private void testScoreStrategySuccessMediumScore(Player player, Map<Player, Integer> roundResults) throws Exception {
        roundResults.put(player, 7);
        assertEquals("Checking player strategy score", -3, invokeScoreStrategySuccess(player, roundResults));
    }

    private void testScoreStrategySuccessHighScore(Player player, Map<Player, Integer> roundResults) throws Exception {
        roundResults.put(player, 2);
        assertEquals("Checking player strategy score", 7, invokeScoreStrategySuccess(player, roundResults));
    }

    public void testScoreStrategySuccess() throws Exception {
        Player player = new Player("Test Player");
        player.setTargetScore(2);
        Map<Player, Integer> roundResults = createIncompleteRoundResults();
        testScoreStrategySuccessLowScore(player, roundResults);
        testScoreStrategySuccessMediumScore(player, roundResults);
        testScoreStrategySuccessHighScore(player, roundResults);
    }
}