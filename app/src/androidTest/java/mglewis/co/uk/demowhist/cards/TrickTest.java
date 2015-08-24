package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class TrickTest extends TestCase {

    private List<Player> createPlayers() {
        List<Player> playerList = new LinkedList<>();
        playerList.add(new Player("Matt"));
        playerList.add(new Player("Katie"));
        playerList.add(new Player("Ellie"));
        playerList.add(new Player("Kara"));
        return playerList;
    }

    private Play calculateResult(List<Player> playerList, List<Card> cardList, Card.Suit trumpSuit) {
        Trick trick = new Trick(trumpSuit);
        for (int i = 0; i < playerList.size(); i++) {
            trick.makePlay(new Play(playerList.get(i), cardList.get(i)));
        }
        return trick.getWinningPlay();
    }

    private void testTrumpLead(List<Player> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.KING));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.QUEEN));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.THREE));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.TWO));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.CLUBS);
        Card expectedWinningCard = cardList.get(0);
        Player expectedWinningPlayer = playerList.get(0);
        assertEquals("Expecting winning card to be the KING of CLUBS", expectedWinningCard, winningPlay.getCard());
        assertEquals("Expecting winning player to be Matt", expectedWinningPlayer, winningPlay.getPlayer());
    }

    private void testTrumpPlay(List<Player> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.SPADES, Card.Value.KING));
        cardList.add(new Card(Card.Suit.SPADES, Card.Value.QUEEN));
        cardList.add(new Card(Card.Suit.SPADES, Card.Value.THREE));
        cardList.add(new Card(Card.Suit.HEARTS, Card.Value.TWO));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.HEARTS);
        assertEquals("Expecting winning card to be the TWO of HEARTS", cardList.get(3), winningPlay.getCard());
        assertEquals("Expecting winning player to be Kara", playerList.get(3), winningPlay.getPlayer());
    }

    private void testNoTrumpPlayed(List<Player> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.THREE));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.SIX));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.FOUR));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.TWO));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.HEARTS);
        assertEquals("Expecting winning card to be the SIX of DIAMONDS", cardList.get(1), winningPlay.getCard());
        assertEquals("Expecting winning player to be Katie", playerList.get(1), winningPlay.getPlayer());
    }

    private void testDiscardCardsPlayedWithTrumps(List<Player> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.HEARTS, Card.Value.FOUR));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.KING));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.FIVE));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.ACE));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.CLUBS);
        assertEquals("Expecting winning card to be the KING of CLUBS", cardList.get(1), winningPlay.getCard());
        assertEquals("Expecting winning player to be Katie", playerList.get(1), winningPlay.getPlayer());
    }

    private void testDiscardCardsPlayedNoTrumps(List<Player> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.HEARTS, Card.Value.FOUR));
        cardList.add(new Card(Card.Suit.HEARTS, Card.Value.KING));
        cardList.add(new Card(Card.Suit.HEARTS, Card.Value.FIVE));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.ACE));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.CLUBS);
        assertEquals("Expecting winning card to be the KING of HEARTS", cardList.get(1), winningPlay.getCard());
        assertEquals("Expecting winning player to be Katie", playerList.get(1), winningPlay.getPlayer());
    }

    public void testGetWinningPlay() throws Exception {
        List<Player> playerList = createPlayers();
        testTrumpLead(playerList);
        testTrumpPlay(playerList);
        testNoTrumpPlayed(playerList);
        testDiscardCardsPlayedWithTrumps(playerList);
        testDiscardCardsPlayedNoTrumps(playerList);
    }
}