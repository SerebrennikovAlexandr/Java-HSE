package dice.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PlayerTest {

    @Test
    void getOrdinalScale() {
        Dices dices = new Dices(3);
        Player player = new Player(10, dices);
        assert(player.getOrdinalScale() == 10);
    }

    @Test
    void getWins() {
        Dices dices = new Dices(5);
        Player player = new Player(1, dices);
        assert(player.getWins() == 0);

        player.increaseWins();
        player.increaseWins();

        assert(player.getWins() == 2);
    }

    @Test
    void getPoints() {
        Dices dices = new Dices(2);
        Player player = new Player(3, dices);
        assert(player.getPoints() == 0);
        player.setPoints(7);
        assert(player.getPoints() == 7);
    }

    @Test
    void setPoints() {
        Dices dices = new Dices(2);
        Player player = new Player(3, dices);
        player.setPoints(9);
        assert(player.getPoints() == 9);
    }

    @Test
    void setCommentator() {
        int numberOfDices = 3;
        int numberOfWins = 3;

        Dices dices = new Dices(numberOfDices);
        ArrayList<Player> playerList = new ArrayList<>();
        Commentator commentator = new Commentator(dices, playerList, numberOfWins);
        Player player = new Player(10, dices);
        player.setCommentator(commentator);
    }

    @Test
    void increaseWins() {
        Dices dices = new Dices(5);
        Player player = new Player(1, dices);
        assert(player.getWins() == 0);
        player.increaseWins();
        assert(player.getWins() == 1);
    }
}