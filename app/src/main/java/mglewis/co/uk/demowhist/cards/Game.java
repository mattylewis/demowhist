package mglewis.co.uk.demowhist.cards;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MK on 24/08/2015.
 */
public class Game {
    private List<Player> playerList = new LinkedList<>();
    private int numberOfRoundsToPlay = 13;

    public Game() {
        playerList.add(new Player("Matt"));
        playerList.add(new Player("Katie"));
        playerList.add(new Player("Ellie"));
        playerList.add(new Player("Kara"));
    }

    public void playRounds() {
        for (int i = 0; i < numberOfRoundsToPlay; i++) {
            playRound(i);
        }
    }

    private void playRound(int roundNumber) {
        Round round = new Round(playerList, Card.Suit.HEARTS, roundNumber);
        round.dealHands();
        round.playTricks();
    }


}
