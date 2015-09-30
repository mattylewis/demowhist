package mglewis.co.uk.demowhist.game;

import android.util.Log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mglewis.co.uk.demowhist.cards.Card;
import mglewis.co.uk.demowhist.cards.Deck;
import mglewis.co.uk.demowhist.cards.Hand;
import mglewis.co.uk.demowhist.cards.Play;
import mglewis.co.uk.demowhist.player.HumanPlayer;
import mglewis.co.uk.demowhist.player.Player;

/**
 * Created by MK on 24/08/2015.
 */
public class Round {
    private static final String LOG_TAG = "DWST:Round";

    private List<Player> players;
    private Deck deck = new Deck();
    private PlayedTricks playedTricks = new PlayedTricks();
    private Card.Suit trumpSuit;
    private int numberOfTricksToPlay;

    public Round(List<Player> players, Card.Suit trumpSuit, int roundNumber) {
        Log.i(LOG_TAG, "Starting Round #" + roundNumber);
        this.players = players;
        this.trumpSuit = trumpSuit;
        numberOfTricksToPlay = calculateNumberOfTricksToPlay(roundNumber);
    }

    private int calculateNumberOfTricksToPlay(int roundNumber) {
        int tricksToPlay = 13 - roundNumber ;
        return tricksToPlay;
    }

    public void dealHands() {
        for (Player player : players) {
            player.setHand(deck.deal(numberOfTricksToPlay));
            player.getHand().printHand();
        }
    }

    public void playTricks() {
        for (int i = 0; i < numberOfTricksToPlay; i++) {
            Trick trick = playTrick();
            playedTricks.add(trick);
            Play winningPlay = trick.getWinningPlay();
            Log.i(LOG_TAG, winningPlay.getPlayer() + " won the trick with the " + winningPlay.getCard());
            rotateToWinner(winningPlay.getPlayer());
        }
    }

    private Trick playTrick() {
        Trick trick = new Trick(trumpSuit);
        for (Player player : players) {
            trick.makePlay(player.playCard(this, trick));
        }
        return trick;
    }

    private void rotateToWinner(Player winner) {
        Log.i(LOG_TAG, "The winner is: " + winner);
        Collections.rotate(players, players.size() - players.indexOf(winner));
        Log.i(LOG_TAG, "The next player is: " + players.get(0));
    }

    private List<Player> createSimPlayerList(List<HumanPlayer> players) {
        List<Player> simPlayers = new LinkedList<>();
        for (Player player : this.players) {
            simPlayers.add(player.copyForSimulation());
        }
        return simPlayers;
    }

    public void printSummary() {
        Log.i(LOG_TAG, "Printing play by play");
        playedTricks.printPlayByPlay();
    }

    public Map<Player, Integer> getResults() {
        return playedTricks.getResults();
    }
}
