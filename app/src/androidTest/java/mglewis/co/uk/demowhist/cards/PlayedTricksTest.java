package mglewis.co.uk.demowhist.cards;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    public void testGetPlayedCards() {
        List<Player> players = createPlayers();
        PlayedTricks playedTricks = new PlayedTricks();
        playedTricks.add(createTrick(players, createCards(Card.Suit.DIAMONDS)));
        playedTricks.add(createTrick(players, createCards(Card.Suit.CLUBS)));
        playedTricks.add(createTrick(players, createCards(Card.Suit.SPADES)));
        List<Card> playedCards = playedTricks.getPlayedCards();
        assertEquals("Testing the number of played cards", 12, playedCards.size());
        Card card = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        assertEquals("Testing that the played cards list contains " + card, true, playedCards.contains(card));
    }

}
