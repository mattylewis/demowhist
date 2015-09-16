package mglewis.co.uk.demowhist.cards;

import java.util.List;

/**
 * Created by Matthew Lewis on 16/09/2015.
 */
public class ComputerPlayer extends Player {
    private static final String LOG_TAG = "ComputerPlayer";

    private String playerName;
    private Hand currentHand;

    public ComputerPlayer(String playerName) {
        super(playerName);
    }

    public Card playCard(Trick trick) {
        List<Card> validCards = currentHand.getValidCards(trick);
        Card card = calculateBestCard(trick, validCards);
        currentHand.removeCard(card);
        return card;
    }

    private Card calculateBestCard(Trick trick, List<Card> validCards) {
        Round gameState = new SimulatedRound();
    }

    private void simulateRound() {
        // take a copy of current game state and load into round simulator
        // loop through each card in hand and play
        // test the outcome
    }
}
