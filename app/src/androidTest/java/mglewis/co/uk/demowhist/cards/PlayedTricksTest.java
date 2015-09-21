package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Matthew Lewis on 20/09/2015.
 */
public class PlayedTricksTest extends TestCase {

    private List<Player> createPlayers() {
        List<Player> players = new LinkedList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));
        players.add(new Player("Player3"));
        players.add(new Player("Player4"));
        return players;
    }

    private List<Card> createCards(Card.Suit suit) {
        List<Card> cards = new LinkedList<>();
        cards.add(new Card(suit, Card.Value.FIVE));
        cards.add(new Card(suit, Card.Value.ACE));
        cards.add(new Card(suit, Card.Value.TEN));
        cards.add(new Card(suit, Card.Value.FIVE));
        return cards;
    }

    private List<Play> createPlays(List<Player> players, List<Card> cards) {
        List<Play> plays = new LinkedList<>();
        Iterator<Player> playerIterator = players.iterator();
        Iterator<Card> cardIterator = cards.iterator();
        while (playerIterator.hasNext() && cardIterator.hasNext()) {
            plays.add(new Play(playerIterator.next(), cardIterator.next()));
        }
        return plays;
    }

    private Trick createTrick(List<Player> players, List<Card> cards) {
        Trick trick = new Trick(Card.Suit.CLUBS);
        List<Play> plays = createPlays(players, cards);
        for (Play play : plays) {
            trick.makePlay(play);
        }
        return trick;
    }

    private PlayedTricks createPlayedTricks() {
        List<Player> players = createPlayers();
        PlayedTricks playedTricks = new PlayedTricks();
        playedTricks.add(createTrick(players, createCards(Card.Suit.DIAMONDS)));
        playedTricks.add(createTrick(players, createCards(Card.Suit.CLUBS)));
        playedTricks.add(createTrick(players, createCards(Card.Suit.SPADES)));
        return  playedTricks;
    }

    public void testGetPlayedCards() {
        List<Card> playedCards = createPlayedTricks().getPlayedCards();
        assertEquals("Testing the number of played cards", 12, playedCards.size());
        Card card = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        assertEquals("Testing that the played cards list contains " + card, true, playedCards.contains(card));
    }

    public void testGetResults() {
        PlayedTricks playedTricks = createPlayedTricks();
        Map<Player, Integer> results = playedTricks.getResults();
        Player winningPlayer = null;
        for ( Player player : results.keySet()) {
            if ( winningPlayer == null || results.get(player) > results.get(winningPlayer)) {
                winningPlayer = player;
            }
        }
        assertEquals("Testing that the winner won all 3 tricks", 3, (int)results.get(winningPlayer));
    }

    public void testCopyForSimulation() {
        PlayedTricks initial = createPlayedTricks();
        PlayedTricks copy = initial.copyForSimulation();
        assertEquals("Testing that the copy has a different reference", true, initial != copy);
    }
}
