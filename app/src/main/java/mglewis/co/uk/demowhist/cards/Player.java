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

    public Card playCard(Trick trick) {
        List<Card> validCards = currentHand.getValidCards(trick);
        Card card = calculateBestCard(trick, validCards);
        currentHand.removeCard(card);
        return card;
    }

    private Card calculateBestCard(Trick trick, List<Card> validCards) {
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
}
