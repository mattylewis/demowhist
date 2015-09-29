package mglewis.co.uk.demowhist.cards;

import mglewis.co.uk.demowhist.player.Player;

/**
 * Created by Matthew Lewis on 24/08/2015.
 */
public class Play {
    private Player player;
    private Card card;

    public Play(Player player, Card card) {
        this.player = player;
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }

    public boolean isSuit(Card.Suit suit) {
        return card.getSuit().equals(suit);
    }

    public String toString() {
        return getPlayer() + " played the " + getCard();
    }

    public Play compareCardValues(Play otherPlay) {
        if (getCard().compareTo(otherPlay.getCard()) == 1) {
            return this;
        }
        return otherPlay;
    }
}