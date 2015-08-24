package mglewis.co.uk.demowhist.cards;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static mglewis.co.uk.demowhist.cards.Card.Suit;

/**
 * Created by MK on 23/08/2015.
 */
public class Hand {
    private static final String LOG_TAG = "Player";

    List<Card> cards;

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
}
