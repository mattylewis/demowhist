package mglewis.co.uk.demowhist.cards;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import static mglewis.co.uk.demowhist.cards.Card.Suit;

/**
 * Created by MK on 23/08/2015.
 */
public class Trick {
    private static final String LOG_TAG = "Trick";

    private List<Play> plays = new LinkedList<>();
    private Suit trump;

    public Trick(Suit trump) {
        this.trump = trump;
    }

    private Trick(Suit trump, List<Play> previousPlays) {
        this.trump = trump;
        this.plays = new LinkedList<>(previousPlays);
    }

    public void makePlay(Play play) {
        Log.i(LOG_TAG, play.getPlayer() + " played the " + play.getCard());
        plays.add(play);
    }

    public Play getWinningPlay() {
        Suit lead = getLeadSuit();
        Play currentlyWinningPlay = null;
        for (Play play : plays) {
            if (currentlyWinningPlay == null) {
                currentlyWinningPlay = play;
            } else {
                currentlyWinningPlay = compare(currentlyWinningPlay, play, lead, trump);
            }
        }
        return currentlyWinningPlay;
    }

    public Player getWinningPlayer() {
        return getWinningPlay().getPlayer();
    }

    // note: game logic assumes that playA is always the lead suit or a trump
    private Play compare(Play playA, Play playB, Suit lead, Suit trump) {
        if (playA.isSuit(trump) && playB.isSuit(trump)) {
            return playA.compareCardValues(playB);
        } else if (playB.isSuit(trump)) {
            return playB;
        } else if (!playA.isSuit(trump) && playB.isSuit(lead)) {
            return playA.compareCardValues(playB);
        }
        return playA;
    }

    public Suit getTrumpSuit() {
        return trump;
    }

    public Suit getLeadSuit() {
        return plays.get(0).getCard().getSuit();
    }

    public List<Play> getPlays() {
        return plays;
    }

    public Trick copyForSimulation() {
        return new Trick(trump, plays);
    }

    // TODO: need to write a unit test for this
    public List<Player> getOutstandingPlayers(List<Player> players) {
        List<Player> outstandingPlayers = new LinkedList<>();
        for (Play play : plays) {
            Player player = play.getPlayer();
            if (!players.contains(player)) {
                outstandingPlayers.add(player);
            }
        }
        return outstandingPlayers;
    }
}
