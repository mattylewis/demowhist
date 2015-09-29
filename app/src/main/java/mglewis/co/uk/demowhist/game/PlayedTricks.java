package mglewis.co.uk.demowhist.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mglewis.co.uk.demowhist.cards.Card;
import mglewis.co.uk.demowhist.cards.Play;
import mglewis.co.uk.demowhist.player.HumanPlayer;

/**
 * Created by Matthew Lewis on 18/09/2015.
 */
public class PlayedTricks {
    private List<Trick> playedTricks = new LinkedList<>();

    public PlayedTricks() {}

    private PlayedTricks(List<Trick> playedTricks) {
        this.playedTricks.addAll(playedTricks);
    }

    public void add(Trick trick) {
        playedTricks.add(trick);
    }

    public List<Card> getPlayedCards() {
        List<Card> playedCards = new LinkedList<>();
        for (Trick trick : playedTricks) {
            for (Play play : trick.getPlays()) {
                playedCards.add(play.getCard());
            }
        }
        return playedCards;
    }

    public int size() {
        return playedTricks.size();
    }

    public PlayedTricks copyForSimulation() {
        return new PlayedTricks(playedTricks);
    }

    public Map<HumanPlayer, Integer> getResults() {
        Map<HumanPlayer, Integer> results = new HashMap<>();
        for (Trick trick : playedTricks) {
            HumanPlayer winner = trick.getWinningPlayer();
            if (results.get(winner) == null) {
                results.put(winner, 1);
            } else {
                results.put(winner, results.get(winner)+1);
            }
        }
        return results;
    }

    public void printPlayByPlay() {
        for (Trick trick : playedTricks) {
            trick.printPlayByPlay();
        }
    }

}
