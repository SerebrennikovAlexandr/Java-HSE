package dice.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommentatorTest {

    @Test
    void getCurrentWinner() {
        int numberOfPlayers = 3;
        int numberOfDices = 3;
        int numberOfWins = 3;

        Dices dices = new Dices(numberOfDices);

        ArrayList<Player> playerList = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new Player(i+1, dices));
        }

        Commentator commentator = new Commentator(dices, playerList, numberOfWins);

        Player player = new Player(10, dices);
        assert(commentator.getCurrentWinner() == null);
        commentator.setCurrentWinner(player);
        assert(commentator.getCurrentWinner().getOrdinalScale() == 10);
    }

    @Test
    void setCurrentWinner() {
        int numberOfDices = 3;
        int numberOfWins = 3;

        Dices dices = new Dices(numberOfDices);

        ArrayList<Player> playerList = new ArrayList<>();

        Commentator commentator = new Commentator(dices, playerList, numberOfWins);

        Player player = new Player(10, dices);
        assert(commentator.getCurrentWinner() == null);
        commentator.setCurrentWinner(player);
        assert(commentator.getCurrentWinner() == player);
    }

    @Test
    void getRoundLeader() {
        int numberOfPlayers = 3;
        int numberOfDices = 3;
        int numberOfWins = 3;

        Dices dices = new Dices(numberOfDices);

        ArrayList<Player> playerList = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new Player(i+1, dices));
        }

        Commentator commentator = new Commentator(dices, playerList, numberOfWins);

        Player player = new Player(10, dices);
        assert(commentator.getRoundLeader() == null);
        commentator.setRoundLeader(player);
        assert(commentator.getRoundLeader().getOrdinalScale() == 10);
    }

    @Test
    void setRoundLeader() {
        int numberOfDices = 3;
        int numberOfWins = 3;

        Dices dices = new Dices(numberOfDices);

        ArrayList<Player> playerList = new ArrayList<>();

        Commentator commentator = new Commentator(dices, playerList, numberOfWins);

        Player player = new Player(10, dices);
        assert(commentator.getRoundLeader() == null);
        commentator.setRoundLeader(player);
        assert(commentator.getRoundLeader() == player);
    }
}