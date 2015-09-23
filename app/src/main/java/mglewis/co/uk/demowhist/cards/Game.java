package mglewis.co.uk.demowhist.cards;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by MK on 24/08/2015.
 */
public class Game extends IntentService {
    private static final String LOG_TAG = "Game";

    private List<Player> players = new LinkedList<>();
    private int numberOfRoundsToPlay = 1;

    public Game() {
        super("game_service");
    }

    public void setupGame() {
        players.add(new Player("Matt"));
        players.add(new Player("Katie"));
        //players.add(new Player("Ellie"));
        //players.add(new Player("Kara"));
    }

    public void playRounds() {
        for (int i = 0; i < numberOfRoundsToPlay; i++) {
            Round round = playRound(i);
            printResults(round);
        }
    }

    private Round playRound(int roundNumber) {
        Round round = new Round(players, Card.Suit.HEARTS, roundNumber);
        round.dealHands();
        round.playTricks();
        round.printSummary();
        return round;
    }

    private void printResults(Round round) {
        Map<Player, Integer> results = round.getResults();
        for (Player player : players) {
            int score = results.get(player) == null ? 0 : results.get(player);
            Log.i(LOG_TAG, player + " scored " + score);
        }
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        setupGame();
        playRounds();
    }
}
