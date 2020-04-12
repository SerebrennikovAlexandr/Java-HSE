package dice.game;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Commentator extends Thread {
    /*
     * FIELDS
     */

    private final Dices dices;
    private final List<Player> playerList;
    private Player currentWinner;
    private Player roundLeader;
    private final int numberOfWins;
    private int currentRound;

    int playedNum;

    /*
     * ACCESSORS
     */

    public Player getCurrentWinner() {
        return currentWinner;
    }

    public void setCurrentWinner(Player currentWinner) {
        this.currentWinner = currentWinner;
    }

    public Player getRoundLeader() {
        return roundLeader;
    }

    public void setRoundLeader(Player roundLeader) {
        this.roundLeader = roundLeader;
    }

    /*
     * CONSTRUCTORS
     */

    public Commentator(Dices dices, List<Player> playerList, int numberOfWins) {
        this.dices = dices;
        this.playerList = playerList;
        this.numberOfWins = numberOfWins;
        this.currentRound = 1;
    }

    /*
     * METHODS
     */

    @Override
    public void run() {
        synchronized (dices) {
            for(Player player : playerList) {
                player.setCommentator(this);
            }

            while(!isGameOver()) {
                System.out.println(" --- ROUND " + currentRound + " --- ");

                //We prepare for the new round
                roundLeader = null;
                playedNum = 0;
                for(Player player : playerList) {
                    player.setPoints(0);
                }

                //Start all threads
                for(Player player : playerList) {
                    new Thread(player).start();
                }

                lockUntilAllRolls();

                getRoundLeader().increaseWins();

                //Search for the current winner
                for(Player player : playerList) {
                    if(getCurrentWinner() == null || getCurrentWinner().getWins() < player.getWins()) {
                        setCurrentWinner(player);
                    }
                }

                //Printing results of the round
                printRoundWinner();
                if(!isGameOver()) {
                    printCurrentWinner();
                }
                System.out.println();

                currentRound++;
            }

            printResults();
        }
    }

    synchronized void printMaxPointsThrown(Player player) {
        System.out.print(" - As player " + getRoundLeader().getOrdinalScale() + " rolled max points, ");
        System.out.println("player " + player.getOrdinalScale() + "skips his turn.");
    }

    synchronized void printCurrentTurn(Player player) {
        System.out.print(" - Player " + player.getOrdinalScale() + " totally rolled " + player.getPoints() + ". ");
        if(playerList.size() > playedNum) {
            System.out.println("Player " + getRoundLeader().getOrdinalScale() + " is leading in this round.");
        }
    }

    private void printResults() {
        System.out.println("--------------------------------");
        System.out.println("Player " + getCurrentWinner().getOrdinalScale() + " wins in this game!");

        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.getWins(), o1.getWins());
            }
        });

        System.out.println("--------------------------------");
        System.out.println("Results table:");
        System.out.println();
        System.out.println("Player     Wins");

        for(Player player : playerList) {
            System.out.println("  " + player.getOrdinalScale() + "        " + player.getWins());
        }
    }

    private void printCurrentWinner() {
        System.out.println("Player " + getCurrentWinner().getOrdinalScale() + " is leading now with " +
                getCurrentWinner().getWins() + " wins.");
    }

    private void printRoundWinner() {
        System.out.print("Player " + getRoundLeader().getOrdinalScale() + " wins round " + currentRound + " ");
        System.out.print("with " + getRoundLeader().getPoints() + " points. ");
        System.out.println("He has " + getRoundLeader().getWins() + " wins.");
    }

    private boolean isGameOver() {
        for(Player player : playerList) {
            if(player.getWins() == numberOfWins) {
                return true;
            }
        }
        return false;
    }

    private void lockUntilAllRolls() {
        while (playedNum != playerList.size()) {
            try {
                dices.wait();
            } catch (InterruptedException e) {}
        }
    }
}
