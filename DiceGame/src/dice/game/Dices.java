package dice.game;

import java.util.Random;

final class Dices {
    /*
     * FIELDS
     */

    final private Random rand = new Random();
    private final int numberOfDices;

    /*
     * ACCESSORS
     */

    int getNumberOfDices() {
        return numberOfDices;
    }

    /*
     * CONSTRUCTORS
     */

    Dices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    /*
     * METHODS
     */

    synchronized int roll() {
        int result = 0;
        for(int i = 0; i < getNumberOfDices(); i++) {
            result += rand.nextInt(6) + 1;
        }
        return result;
    }
}
