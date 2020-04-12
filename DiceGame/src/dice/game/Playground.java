package dice.game;

/*
This Project was made by Serebrennikov Alexander, BSE181.
February, 2020.
 */

import java.util.ArrayList;

public class Playground {
    public static void main(String[] args) {
        if(!validateInput(args)) {
            System.out.println("Incorrect input!");
            return;
        }

        int numberOfPlayers = Integer.parseInt(args[0]);
        int numberOfDices = Integer.parseInt(args[1]);
        int numberOfWins = Integer.parseInt(args[2]);

        //Create a kit of dices to generate random numbers.
        Dices dices = new Dices(numberOfDices);

        //Invite players, mark them in ordinal scale and make familiar with dices.
        ArrayList<Player> playerList = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new Player(i+1, dices));
        }

        //Invite a commentator who'll judge the game and start hus thread.
        Commentator commentator = new Commentator(dices, playerList, numberOfWins);
        commentator.start();
        //Main thread ends, commentator works.
    }

    /**
     * Checks if the input was correct.
     * @param  args array of Strings.
     * @return      false, if not correct.
     *              true, if correct.
     */
    private static boolean validateInput(String[] args) {
        if(args == null || args.length != 3 || notPlayersNumber(args[0]) ||
                notDicesNumber(args[1]) || notWinsNumber(args[2])) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the given string can be an integer number
     * and a number of players.
     * @param   str input string.
     * @return      false, if <code>str<code/>
     *              can be converted to Integer and is
     *              in the [2;6] interval.
     *              true, if not.
     */
    private static boolean notPlayersNumber(String str) {
        try {
            int res = Integer.parseInt(str);
            return res < 2 || res > 6;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Checks if the given string can be an integer number
     * and a number of dices.
     * @param   str input string.
     * @return      false, if <code>str<code/>
     *              can be converted to Integer and is
     *              in the [2;5] interval.
     *              true, if not.
     */
    private static boolean notDicesNumber(String str) {
        try {
            int res = Integer.parseInt(str);
            return res < 2 || res > 5;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Checks if the given string can be an integer number
     * and a number of rounds a player should win to win the game.
     * @param   str input string.
     * @return      false, if <code>str<code/>
     *              can be converted to Integer and is
     *              in the [1;100] interval.
     *              true, if not.
     */
    private static boolean notWinsNumber(String str) {
        try {
            int res = Integer.parseInt(str);
            return res < 1 || res > 100;
        } catch (Exception e) {
            return true;
        }
    }
}
