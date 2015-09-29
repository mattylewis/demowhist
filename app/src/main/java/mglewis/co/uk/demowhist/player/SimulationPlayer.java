package mglewis.co.uk.demowhist.player;

import java.util.List;
import java.util.Random;

import mglewis.co.uk.demowhist.cards.Card;
import mglewis.co.uk.demowhist.cards.Hand;
import mglewis.co.uk.demowhist.cards.Play;
import mglewis.co.uk.demowhist.game.Round;
import mglewis.co.uk.demowhist.game.Trick;

/**
 * Created by Matthew Lewis on 29/09/2015.
 */
public class SimulationPlayer extends Player {
    private static final String LOG_TAG = "DWST:SimulationPlayer";
    private Random randomNumberGenerator = new Random();

    private Card determineCardToPlay(Trick trick) {
        List<Card> validCards = getHand().getValidCards(trick);
        int index = randomNumberGenerator.nextInt(validCards.size());
        return validCards.get(index);
    }

    private void removeCardFromHand(Card card, Hand hand) {
        hand.removeCard(card);
    }

    public Play playCard(Round round, Trick trick) {
        Card card = determineCardToPlay(trick);
        removeCardFromHand(card, getHand());
        return new Play(this, card);
    }

    public Player copyForSimulation() {
        return this;
    }
}
