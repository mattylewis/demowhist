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

    private List<HumanPlayer> players;
    private Deck deck = new Deck();
    private PlayedTricks playedTricks = new PlayedTricks();
    private Card.Suit trumpSuit;
    private int numberOfTricksToPlay;
    private int simulationDepth;
    private HumanPlayer simulationFor;
    private HumanPlayer simulationParent;

    public Round(List<HumanPlayer> players, Card.Suit trumpSuit, int roundNumber) {
        Log.i(LOG_TAG, "Starting Round #" + roundNumber);
        this.players = players;
        this.trumpSuit = trumpSuit;
        numberOfTricksToPlay = calculateNumberOfTricksToPlay(roundNumber);
        simulationDepth = 0;
        Log.i(LOG_TAG, "Round #" + roundNumber + " will consist of " + numberOfTricksToPlay + " tricks (Sim depth is " + simulationDepth + ")");
    }

    // private constructor for use creating a copy of an existing round state for ai simulation
    private Round(List<HumanPlayer> players, Card.Suit trumpSuit, PlayedTricks playedTricks, int numberOfTricksToPlay, int simulationDepth, HumanPlayer simulationFor, HumanPlayer simulationParent) {
        this.players = players;
        this.trumpSuit = trumpSuit;
        this.playedTricks = playedTricks.copyForSimulation();
        this.numberOfTricksToPlay = numberOfTricksToPlay;
        this.simulationDepth = simulationDepth + 1;
        this.simulationFor = simulationFor;
        this.simulationParent = simulationParent == null ? simulationFor : simulationParent;
    }

    private int calculateNumberOfTricksToPlay(int roundNumber) {
        return 3;
        //int tricksToPlay = 4 - roundNumber ;
        //return tricksToPlay;
    }

    public void dealHands() {
        for (HumanPlayer player : players) {
            player.setHand(deck.deal(numberOfTricksToPlay));
            player.getHand().printHand();
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

        Log.i(LOG_TAG, "Playing trick with sim depth of " + simulationDepth + " simulated by " + simulationFor + " with parent sim player " + simulationParent);
        Log.i(LOG_TAG, "Moves made so far: ");
        trick.printPlayByPlay();

        Log.i(LOG_TAG, "Starting a new trick");
//        Log.i(LOG_TAG, "The trump suit is: " + trumpSuit);
        for (HumanPlayer player : players) {
            trick.makePlay(player.playCard(this, trick));
        }
        return trick;
    }

    private Trick resumeTrick(Trick trick, Play nextPlay) {
        Log.i(LOG_TAG, "Resuming trick with sim depth of " + simulationDepth + " simulated by " + simulationFor + " with parent " + simulationParent);
        Log.i(LOG_TAG, "Moves made so far: ");
        trick.printPlayByPlay();

        trick.makePlay(nextPlay);
        List<HumanPlayer> outstandingPlayers = trick.getOutstandingPlayers(players);
        Log.i(LOG_TAG, "There are " + outstandingPlayers.size() + " players left to play in the trick");
        for (HumanPlayer player : outstandingPlayers) {
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

    private void setSimPlayerHands(List<Player> simPlayers) {
        Deck unplayedCards = new Deck(playedTricks.getPlayedCards());
        int remainingCardsInPlayersHands = numberOfTricksToPlay - playedTricks.size();
        for (HumanPlayer simPlayer : simPlayers) {
            int requiredHandSize = simPlayer.getHand().size();
            Hand hand = unplayedCards.deal(requiredHandSize);
            simPlayer.setHand(hand);
            Log.i(LOG_TAG, "Creating a sim player " + simPlayer + " with a hand size of " + requiredHandSize);
        }
    }

    public Round copyForSimulation(Player simulationPlayer) {
        List<Player> simPlayers = createSimPlayerList(players);
        setSimPlayerHands(simPlayers);
        return new Round(simPlayers, trumpSuit, playedTricks, numberOfTricksToPlay, simulationDepth, simulationPlayer, simulationParent);
    }

    public void printSummary() {
        Log.i(LOG_TAG, "Printing play by play");
        playedTricks.printPlayByPlay();
    }

    public Map<Player, Integer> getResults() {
        return playedTricks.getResults();
    }
}
