package mglewis.co.uk.demowhist.cards;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MK on 23/08/2015.
 */
public class Player {
    private static final String LOG_TAG = "Player";

    private String playerName;
    private Hand currentHand;
    private int targetScore;

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
        Map<Card, Integer> cardScores = new HashMap<>();
        //for (int i = 0; i < 5; i++) {
            for (Card card : validCards) {
                Trick simTrick = currentTrick.copyForSimulation();
                Round simRound = round.copyForSimulation();
                simRound.resumeTricks(simTrick, new Play(this, card));
                int score = scoreStrategySuccess(simRound.getResults());
                cardScores = updateCumulativeCardScore(cardScores, card, score);
            }
        //}
        return getBestCard(cardScores);
    }

    private Map<Card, Integer> updateCumulativeCardScore(Map<Card, Integer> cardScores, Card card, int score) {
        if (cardScores.get(card) == null) {
            cardScores.put(card, score);
        } else {
            cardScores.put(card, score + cardScores.get(card));
        }
        return cardScores;
    }

    private Card getBestCard(Map<Card, Integer> cardScores) {
        Map.Entry<Card, Integer> maxEntry = null;
        for (Map.Entry<Card, Integer> entry : cardScores.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }

    private int scoreStrategySuccess(Map<Player, Integer> roundResults) {
        int score = 0;
        for (Player player : roundResults.keySet()) {
            int tricksWon = roundResults.get(player);
            if (player.equals(this)) {
                score += 2 * player.calculateScore(tricksWon);
            } else {
                score -= player.calculateScore(tricksWon);
            }
        }
        return score;
    }

    private int calculateScore(int tricksWon) {
        if (tricksWon == targetScore) {
            return 10 + tricksWon;
        }
        return tricksWon;
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

    public void setTargetScore(int targetScore) {
        this.targetScore = targetScore;
    }

    public int getTargetScore() {
        return targetScore;
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
