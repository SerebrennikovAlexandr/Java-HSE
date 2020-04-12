package dice.game;

public class Player extends Thread {
    /*
     * FIELDS
     */

    private int wins;
    private int points;
    private int ordinalScale;
    private Commentator commentator;
    private final Dices dices;

    /*
     * ACCESSORS
     */

    int getOrdinalScale() {
        return ordinalScale;
    }

    int getWins() {
        return wins;
    }

    int getPoints() {
        return points;
    }

    void setPoints(int points) {
        this.points = points;
    }

    public void setCommentator(Commentator commentator) {
        this.commentator = commentator;
    }

    /*
     * CONSTRUCTORS
     */

    public Player(int ordinalScale, Dices dices) {
        this.ordinalScale = ordinalScale;
        this.dices = dices;
        this.wins = 0;
        this.points = 0;
    }

    /*
     * METHODS
     */

    @Override
    public void run() {
        synchronized (dices) {
            commentator.playedNum++;

            if(commentator.getRoundLeader() != null &&
                    commentator.getRoundLeader().getPoints() == dices.getNumberOfDices() * 6) {
                commentator.printMaxPointsThrown(this);
                dices.notify();
                return;
            }

            setPoints(dices.roll());

            if(commentator.getRoundLeader() == null || this.getPoints() > commentator.getRoundLeader().getPoints()) {
                commentator.setRoundLeader(this);
            }

            commentator.printCurrentTurn(this);

            dices.notify();
        }
    }

    void increaseWins() {
        this.wins++;
    }
}
