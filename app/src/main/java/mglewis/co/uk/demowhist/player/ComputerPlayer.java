package mglewis.co.uk.demowhist.player;

import java.util.List;
import java.util.Map;

import mglewis.co.uk.demowhist.cards.Card;
import mglewis.co.uk.demowhist.cards.Play;
import mglewis.co.uk.demowhist.game.Round;
import mglewis.co.uk.demowhist.game.Trick;

/**
 * Created by Matthew Lewis on 29/09/2015.
 */
public class ComputerPlayer extends Player {

    public Play playCard(Round round, Trick trick) {
        List<Card> validCards = getHand().getValidCards(trick);
        // TODO: improve AI here
        return new Play(this, validCards.get(0));
    }

    public Player copyForSimulation() {
        return new SimulationPlayer();
    }

//    private int scoreStrategySuccess(Map<HumanPlayer, Integer> roundResults) {
//        int score = 0;
//        for (HumanPlayer player : roundResults.keySet()) {
//            int tricksWon = roundResults.get(player);
//            if (player.equals(this)) {
//                score += 2 * player.calculateScore(tricksWon);
//            } else {
//                score -= player.calculateScore(tricksWon);
//            }
//        }
//        return score;
//    }

//    private Card calculateBestCardToPlay(Round round, Trick currentTrick, List<Card> validCards) {
//        Log.i(LOG_TAG, "Calculating best card for " + this.toString() + " with " + currentHand.size() + " cards (" + validCards.size() + " valid)");
//        Map<Card, Integer> cardScores = new HashMap<>();
//        //for (int i = 0; i < 5; i++) {
//            for (Card card : validCards) {
//                Log.i(LOG_TAG, "Testing the " + card);
//                Trick simTrick = currentTrick.copyForSimulation();
//                Round simRound = round.copyForSimulation(this);
//                simRound.resumeTricks(simTrick, new Play(this, card));
//                int score = scoreStrategySuccess(simRound.getResults());
//                Log.i(LOG_TAG, playerName + " scored " + score + " with the strategy of playing the " + card);
//                cardScores = updateCumulativeCardScore(cardScores, card, score);
//            }
//        }
//        return getBestCard(cardScores);
//        return validCards.get(0);
//    }

//    private Map<Card, Integer> updateCumulativeCardScore(Map<Card, Integer> cardScores, Card card, int score) {
//        if (cardScores.get(card) == null) {
//            cardScores.put(card, score);
//        } else {
//            cardScores.put(card, score + cardScores.get(card));
//        }
//        return cardScores;

//    private Card getBestCard(Map<Card, Integer> cardScores) {
//        Map.Entry<Card, Integer> maxEntry = null;
//        for (Map.Entry<Card, Integer> entry : cardScores.entrySet()) {
//            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
//                maxEntry = entry;
//            }
//        }
//        Log.i(LOG_TAG, "FINALLY DECIDED ON THE " + maxEntry.getKey());
//        return maxEntry.getKey();
//    }

}
