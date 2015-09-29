package mglewis.co.uk.demowhist.player;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mglewis.co.uk.demowhist.cards.Card;
import mglewis.co.uk.demowhist.cards.Hand;
import mglewis.co.uk.demowhist.cards.Play;
import mglewis.co.uk.demowhist.game.Round;
import mglewis.co.uk.demowhist.game.Trick;

/**
 * Created by MK on 23/08/2015.
 */
public class HumanPlayer extends Player {
    private static final String LOG_TAG = "DWST:HumanPlayer";
    private String playerName;
    private Hand currentHand = new Hand();
    private int targetScore;

    public HumanPlayer(String playerName) {
        this.playerName = playerName;
    }

    public Play playCard(Round round, Trick trick) {
        List<Card> validCards = currentHand.getValidCards(trick);
        Card card = validCards.get(0);
        currentHand.removeCard(card);
        return new Play(this, card);
    }

    public Player copyForSimulation() {
        return new SimulationPlayer();
    }







    public boolean playerNameMatches(HumanPlayer player) {
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


}
