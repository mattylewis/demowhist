package mglewis.co.uk.demowhist.player;

import mglewis.co.uk.demowhist.cards.Hand;
import mglewis.co.uk.demowhist.cards.Play;
import mglewis.co.uk.demowhist.game.Round;
import mglewis.co.uk.demowhist.game.Trick;

/**
 * Created by Matthew Lewis on 29/09/2015.
 */
public abstract class Player {

    private String playerName;
    private Hand currentHand = new Hand();

    public Player() {}

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Hand getHand() {
        return currentHand;
    }

    public void setHand(Hand hand) {
        currentHand = hand;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return getPlayerName();
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
        // add 13 to hashcode to ensure playerName doesn't have the same hash as the underlying string
        return 13 + playerName.hashCode();
    }

    public abstract Play playCard(Round round, Trick trick);

    public abstract Player copyForSimulation();

    protected void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private static int calculateScore(int tricksWon, int targetScore) {
        if (tricksWon == targetScore) {
            return 10 + tricksWon;
        }
        return tricksWon;
    }
}
