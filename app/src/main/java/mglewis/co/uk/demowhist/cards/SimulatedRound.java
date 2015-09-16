package mglewis.co.uk.demowhist.cards;

import android.util.Log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Matthew Lewis on 16/09/2015.
 */
public class SimulatedRound extends Round {
    private static final String LOG_TAG = "SimulatedRound";

    private List<Player> playerList;
    private List<Play> winningPlays = new LinkedList<>();
    private Deck deck = new Deck();
    private Card.Suit trumpSuit;
    private int numberOfTricksToPlay;

    public SimulatedRound(List<Player> playerList, Card.Suit trumpSuit, int roundNumber) {
        super(playerList, trumpSuit, roundNumber);
        Log.i(LOG_TAG, "Starting Round #" + roundNumber);
        this.playerList = playerList;
        this.trumpSuit = trumpSuit;
        numberOfTricksToPlay = calculateNumberOfTricksToPlay(roundNumber);
        Log.i(LOG_TAG, "Round #" + roundNumber + " will consist of " + numberOfTricksToPlay + " tricks");
    }

    public int calculateNumberOfTricksToPlay(int roundNumber) {
        return 13 - roundNumber;
    }

    private void dealHands() {
        for (Player player : playerList) {
            player.setHand(deck.deal(numberOfTricksToPlay));
            player.printHand();
        }
    }

    public void playTricks() {
        for (int i = 0; i < numberOfTricksToPlay; i++) {
            Log.i(LOG_TAG, "Playing Trick #" + i);
            Play winningPlay = playTrick();
            Log.i(LOG_TAG, winningPlay.getPlayer() + " won the trick with the " + winningPlay.getCard());
            winningPlays.add(winningPlay);
            rotateToWinner(winningPlay.getPlayer());

        }
    }

    private Play playTrick() {
        Trick trick = new Trick(this, trumpSuit);
        Log.i(LOG_TAG, "The trump suit is: " + trumpSuit);
        for (Player player : playerList) {
            trick.makePlay(new Play(player, player.playCard(trick)));
        }
        return trick.getWinningPlay();
    }

    private void rotateToWinner(Player winner) {
        Collections.rotate(playerList, playerList.size() - playerList.indexOf(winner));
        Log.i(LOG_TAG, "Next player is: " + playerList.get(0));
    }
}
