package dice.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DicesTest {


    @Test
    void getNumberOfDices() {
        Dices dices = new Dices(5);
        assertEquals(dices.getNumberOfDices(), 5);
    }

    @Test
    void roll() {
        Dices dices = new Dices(2);
        int result = dices.roll();
        assert(result >= 1 && result <= 12);
    }
}