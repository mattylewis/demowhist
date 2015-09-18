package mglewis.co.uk.demowhist.cards;

import android.util.Log;

import java.util.List;

/**
 * Created by MK on 23/08/2015.
 */
public class Player {
    private static final String LOG_TAG = "Player";

    private String playerName;
    private Hand currentHand;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    // this constructor is use to create a deep copy of a player object for simulation purposes
    private Player(Player player) {
        this.playerName = player.toString();
    }

    public void setHand(Hand hand) {
        currentHand = hand;
    }

    public Play playCard(Round round, Trick trick) {
        List<Card> validCards = currentHand.getValidCards(trick);
        Card card = calculateBestCardToPlay(round, trick, validCards);
        currentHand.removeCard(card);
        return new Play(this, card);
    }

    private Card calculateBestCardToPlay(Round round, Trick currentTrick, List<Card> validCards) {
        // run through the rest of the round using each of the players valid cards in turn to see what is the best option
        for (Card card : validCards) {
            Trick simTrick = currentTrick.copyForSimulation();
            Round simRound = round.copyForSimulation();
            simRound.resumeTricks(simTrick, card);
            
            //round.getResults();

            // calculate how many points were won and if this is the best strategy so far
        }



        return validCards.get(0);
    }

    public void printHand() {
        if (currentHand == null) {
            Log.i(LOG_TAG, playerName + " has an empty hand");
        } else {
            Log.i(LOG_TAG, playerName + " has " + currentHand.size() + " cards:");
            for (Card card : currentHand.getCards()) {
                Log.i(LOG_TAG, "- " + card.toString());
            }
        }
    }

    public String toString() {
        return playerName;
    }

    public Player copyForSimulation() {
        return new Player(this);
    }

    public boolean playerNameMatches(Player player) {
        if (playerName.equals(player.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Player)) {
            return false;
        } else if (object == this) {
            return true;
        } else if (object.hashCode() == this.hashCode()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // add 13 to hashcode to ensure playerName doesn't have the same has as the underlying string
        return 13 + playerName.hashCode();
    }

}
