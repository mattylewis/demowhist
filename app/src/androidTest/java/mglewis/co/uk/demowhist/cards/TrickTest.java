package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import mglewis.co.uk.demowhist.game.Trick;
import mglewis.co.uk.demowhist.player.HumanPlayer;

/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class TrickTest extends TestCase {

    private List<HumanPlayer> createPlayers() {
        List<HumanPlayer> playerList = new LinkedList<>();
        playerList.add(new HumanPlayer("Matt"));
        playerList.add(new HumanPlayer("Katie"));
        playerList.add(new HumanPlayer("Ellie"));
        playerList.add(new HumanPlayer("Kara"));
        return playerList;
    }

    private Play calculateResult(List<HumanPlayer> playerList, List<Card> cardList, Card.Suit trumpSuit) {
        Trick trick = new Trick(trumpSuit);
        for (int i = 0; i < playerList.size(); i++) {
            trick.makePlay(new Play(playerList.get(i), cardList.get(i)));
        }
        return trick.getWinningPlay();
    }

    private void testTrumpLead(List<HumanPlayer> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.KING));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.QUEEN));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.THREE));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.TWO));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.CLUBS);
        Card expectedWinningCard = cardList.get(0);
        HumanPlayer expectedWinningPlayer = playerList.get(0);
        assertEquals("Expecting winning card to be the KING of CLUBS", expectedWinningCard, winningPlay.getCard());
        assertEquals("Expecting winning player to be Matt", expectedWinningPlayer, winningPlay.getPlayer());
    }

    private void testTrumpPlay(List<HumanPlayer> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.SPADES, Card.Value.KING));
        cardList.add(new Card(Card.Suit.SPADES, Card.Value.QUEEN));
        cardList.add(new Card(Card.Suit.SPADES, Card.Value.THREE));
        cardList.add(new Card(Card.Suit.HEARTS, Card.Value.TWO));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.HEARTS);
        assertEquals("Expecting winning card to be the TWO of HEARTS", cardList.get(3), winningPlay.getCard());
        assertEquals("Expecting winning player to be Kara", playerList.get(3), winningPlay.getPlayer());
    }

    private void testNoTrumpPlayed(List<HumanPlayer> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.THREE));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.SIX));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.FOUR));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.TWO));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.HEARTS);
        assertEquals("Expecting winning card to be the SIX of DIAMONDS", cardList.get(1), winningPlay.getCard());
        assertEquals("Expecting winning player to be Katie", playerList.get(1), winningPlay.getPlayer());
    }

    private void testDiscardCardsPlayedWithTrumps(List<HumanPlayer> playerList) {
        List<Card> cardList = new LinkedList<>();
        cardList.add(new Card(Card.Suit.HEARTS, Card.Value.FOUR));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.KING));
        cardList.add(new Card(Card.Suit.CLUBS, Card.Value.FIVE));
        cardList.add(new Card(Card.Suit.DIAMONDS, Card.Value.ACE));
        Play winningPlay = calculateResult(playerList, cardList, Card.Suit.CLUBS);
        assertEquals("Expecting winning card to be the KING of CLUBS", cardList.get(1), winningPlay.getCard());
        assertEquals("Expecting winning player to be Katie", playerList.get(1), winningPlay.getPlayer());
    }

    private void testDiscardCardsPlayedNoTrumps(List<HumanPlayer> playerList) {
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
        List<HumanPlayer> playerList = createPlayers();
        testTrumpLead(playerList);
        testTrumpPlay(playerList);
        testNoTrumpPlayed(playerList);
        testDiscardCardsPlayedWithTrumps(playerList);
        testDiscardCardsPlayedNoTrumps(playerList);
    }

    private Trick createTrick(List<HumanPlayer> players, List<Card> cards) {
        Trick trick = new Trick(Card.Suit.HEARTS);
        Iterator<HumanPlayer> playerIterator = players.iterator();
        Iterator<Card> cardIterator = cards.iterator();
        while (playerIterator.hasNext() && cardIterator.hasNext()) {
            trick.makePlay(new Play(playerIterator.next(), cardIterator.next()));
        }
        return trick;
    }

    private List<Card> createCards() {
        List<Card> cards = new LinkedList<>();
        cards.add(new Card(Card.Suit.HEARTS, Card.Value.FOUR));
        cards.add(new Card(Card.Suit.HEARTS, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEARTS, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.Value.ACE));
        return cards;
    }

    public void testGetOutstandingPlayers() {
        List<HumanPlayer> players = createPlayers();
        List<Card> cards = createCards();
        Trick trick = createTrick(players.subList(0, 2), cards.subList(0, 2));
        List<HumanPlayer> outstandingPlayers = trick.getOutstandingPlayers(players);
        assertEquals("Checking the length of the outstanding players list", 2, outstandingPlayers.size());
    }

    public void testCopyForSimulation() {
        List<HumanPlayer> players = createPlayers();
        List<Card> cards = createCards();
        Trick trick = createTrick(players, cards);
        Trick trickCopy = trick.copyForSimulation();
        assertEquals("Checking that the copy of the trick has the same plays", trick.getPlays(), trickCopy.getPlays());
    }
}