package mglewis.co.uk.demowhist.game;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mglewis.co.uk.demowhist.cards.Card;
import mglewis.co.uk.demowhist.player.ComputerPlayer;
import mglewis.co.uk.demowhist.player.HumanPlayer;

/**
 * Created by MK on 24/08/2015.
 */
public class Game extends IntentService {
    private static final String LOG_TAG = "DWST:Game";

    private List<HumanPlayer> players = new LinkedList<>();
    private int numberOfRoundsToPlay = 2;

    public Game() {
        super("game_service");
    }

    public void setupGame() {
        players.add(new HumanPlayer("Matt"));
        players.add(new ComputerPlayer("Katie"));
        players.add(new ComputerPlayer("Ellie"));
        players.add(new ComputerPlayer("Kara"));
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
        Log.i(LOG_TAG, "Printing results!");
        Map<HumanPlayer, Integer> results = round.getResults();
        for (HumanPlayer player : players) {
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
