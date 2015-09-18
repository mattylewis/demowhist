package mglewis.co.uk.demowhist.cards;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

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
}
