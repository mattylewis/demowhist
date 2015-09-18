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

    public void setHand(Hand hand) {
        currentHand = hand;
    }

    public Play playCard(Round round, Trick trick) {
        List<Card> validCards = currentHand.getValidCards(trick);
        Card card = calculateBestCardToPlay(round, trick, validCards);
        currentHand.removeCard(card);
        return new Play(this, card);
    }

    private Card calculateBestCardToPlay(Round round, Trick trick, List<Card> validCards) {
        // run through the rest of the round using each of the players valid cards in turn to see what is the most successfully option
        for (Card card : validCards) {
            // load a copy of the round
            // - replacing human players with computer players
            // - swapping their 'actual' cards with a random selection of cards that haven't yet been played


            // play the rest of the round

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

//    public Player createPlayerStateForSimulation() {
//
//    }

}
