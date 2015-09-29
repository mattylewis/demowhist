package mglewis.co.uk.demowhist.cards;

import android.util.Log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import mglewis.co.uk.demowhist.game.Trick;

import static mglewis.co.uk.demowhist.cards.Card.Suit;

/**
 * Created by MK on 23/08/2015.
 */
public class Hand {
    private static final String LOG_TAG = "DWST:Hand";
    private List<Card> cards;

    public Hand() {
        cards = new LinkedList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
        Collections.sort(cards);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getValidCards(Trick trick) {
        List<Card> validCards = new LinkedList<>();
        if (handContainsLeadSuit(trick) == false) {
            return cards;
        }
        for (Card card : cards) {
            if (checkCardIsValid(card, trick.getLeadSuit())) {
                validCards.add(card);
            }
        }
        return validCards;
    }

    private boolean handContainsLeadSuit(Trick trick) {
        if (trick.getPlays().size() == 0) {
            return false;
        }
        for (Card card : cards) {
            if (card.getSuit().equals(trick.getLeadSuit())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCardIsValid(Card card, Suit leadSuit) {
        if (card.getSuit().equals(leadSuit)) {
            return true;
        }
        return false;
    }

    public void printHand() {
        if (cards == null || cards.size() == 0) {
            Log.i(LOG_TAG, "Hand is empty");
        } else {
            Log.i(LOG_TAG, "Hand contains " + cards.size() + " cards:");
            for (Card card : cards) {
                Log.i(LOG_TAG, "- " + card.toString());
            }
        }
    }
}
