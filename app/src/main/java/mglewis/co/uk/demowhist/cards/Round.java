package mglewis.co.uk.demowhist.cards;

import android.util.Log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by MK on 24/08/2015.
 */
public class Round {
    private static final String LOG_TAG = "Round";

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
        Log.i(LOG_TAG, "Round #" + roundNumber + " will consist of " + numberOfTricksToPlay + " tricks");
    }

    // private constructor for use creating a copy of an existing round state for ai simulation
    private Round(List<Player> players, Card.Suit trumpSuit, PlayedTricks playedTricks, int numberOfTricksToPlay) {
        this.players = players;
        this.trumpSuit = trumpSuit;
        this.playedTricks = playedTricks.copyForSimulation();
        this.numberOfTricksToPlay = numberOfTricksToPlay;
    }

    private int calculateNumberOfTricksToPlay(int roundNumber) {
        return 1;
        //return 2 - roundNumber;
    }

    public void dealHands() {
        for (Player player : players) {
            player.setHand(deck.deal(numberOfTricksToPlay));
            player.printHand();
        }
    }

    public void playTricks() {
        for (int i = 0; i < numberOfTricksToPlay; i++) {
            Trick trick = playTrick();
            playedTricks.add(trick);
            Play winningPlay = trick.getWinningPlay();
            // Log.i(LOG_TAG, winningPlay.getPlayer() + " won the trick with the " + winningPlay.getCard());
            rotateToWinner(winningPlay.getPlayer());
        }
    }

    public void resumeTricks(Trick trick, Play nextPlay) {
        trick = resumeTrick(trick, nextPlay);
        playedTricks.add(trick);
        rotateToWinner(trick.getWinningPlayer());
        numberOfTricksToPlay -= 1;
        playTricks();
    }

    private Trick playTrick() {
        Trick trick = new Trick(trumpSuit);
//        Log.i(LOG_TAG, "The trump suit is: " + trumpSuit);
        for (Player player : players) {
            trick.makePlay(player.playCard(this, trick));
        }
        return trick;
    }

    private Trick resumeTrick(Trick trick, Play nextPlay) {
        trick.makePlay(nextPlay);
        List<Player> outstandingPlayers = trick.getOutstandingPlayers(players);
        for (Player player : outstandingPlayers) {
            trick.makePlay(player.playCard(this, trick));
        }
        return trick;
    }

    private void rotateToWinner(Player winner) {
        Collections.rotate(players, players.size() - players.indexOf(winner));
        //Log.i(LOG_TAG, "Next player is: " + players.get(0));
    }

    private List<Player> createSimPlayerList(List<Player> players) {
        List<Player> simPlayers = new LinkedList<>();
        for (Player player : this.players) {
            simPlayers.add(player.copyForSimulation());
        }
        return simPlayers;
    }

    private void setSimPlayerHands(List<Player> simPlayers) {
        Deck unplayedCards = new Deck(playedTricks.getPlayedCards());
        int remainingCardsInPlayersHands = numberOfTricksToPlay - playedTricks.size();
        for (Player simPlayer : simPlayers) {
            Hand hand = unplayedCards.deal(remainingCardsInPlayersHands);
            simPlayer.setHand(hand);
        }
    }

    public Round copyForSimulation() {
        List<Player> simPlayers = createSimPlayerList(players);
        setSimPlayerHands(simPlayers);
        return new Round(simPlayers, trumpSuit, playedTricks, numberOfTricksToPlay);
    }

    public void printSummary() {
        Log.i(LOG_TAG, "Printing play by play");
        playedTricks.printPlayByPlay();
    }

    public Map<Player, Integer> getResults() {
        return playedTricks.getResults();
    }
}
