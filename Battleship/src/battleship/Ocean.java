package battleship;

import java.util.Random;

class Ocean {
    /*
     * FIELDS
     */
    final private Random rand = new Random();

    private int shotsFired = 0;
    private int hitCount = 0;
    private int shipsSunk = 0;

    private Ship[][] ships = new Ship[10][10];

    /*
     * ACCESSORS
     */

    /**
     * How many shots were made.
     * @return  shotsFired
     */
    int getShotsFired() {
        return shotsFired;
    }

    /**
     * How many hits were made.
     * @return  hitCount
     */
    int getHitCount() {
        return hitCount;
    }

    /**
     * How many ships were sunk.
     * @return  shipsSunk
     */
    private int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * Getter for ships array.
     * @return Ship[][] ships
     */
    Ship[][] getShipArray() {
        return ships;
    }

    /**
     * CONSTRUCTORS
     */
    Ocean() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                Ship ship = new EmptySea();
                ship.placeShipAt(i, j,true, this);
                //ships[i][j] = new EmptySea();
            }
        }
    }

    /*
     * METHODS
     */

    /**
     * Checks, if all the ships have been sunk.
     * @return true, if all (almost 10) the ships
     * have been sunk.
     *         false otherwise.
     */
    boolean isGameOver() {
        return getShipsSunk() == 10;
    }

    /**
     * Place all ten ships randomly on the
     * (initially empty) ocean.
     * For particular realisation:
     * @see Ocean#placeShipRandomly(Ship)
     */
    void placeAllShipsRandomly() {
        placeShipRandomly(new Battleship());

        for(int i = 0; i < 2; i++) {
            placeShipRandomly(new Cruiser());
        }

        for(int i = 0; i < 3; i++) {
            placeShipRandomly(new Destroyer());
        }

        for(int i = 0; i < 4; i++) {
            placeShipRandomly(new Submarine());
        }
    }

    /**
     * Executes shooting particular ship on the coordinates,
     * increments counters.
     * @param row row index in the ships array
     * @param column column index in the ships array
     * @return true if the given location contains a "real" ship,
     *              still afloat, (not an EmptySea).
     *         false if it does not.
     */
    boolean shootAt(int row, int column) {
        shotsFired++;
        boolean result = ships[row][column].shootAt(row, column);
        if(result) {
            hitCount++;
            if(ships[row][column].isSunk()) {
                shipsSunk++;
            }
        }
        return result;
    }

    /**
     * Prints the ocean.
     */
    void print() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
        for(int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for(int j = 0; j < 10; j++) {
                Ship thisShip = ships[i][j];
                boolean wasHit;
                if(thisShip.isHorizontal()) {
                    wasHit = thisShip.getHitArray()[j - thisShip.getBowColumn()];
                } else {
                    wasHit = thisShip.getHitArray()[i - thisShip.getBowRow()];
                }
                if(thisShip instanceof EmptySea || wasHit) {
                    System.out.print(ships[i][j].toString());
                } else {
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Checks, if the particular element (field)
     * in the ships array is a real ship, not
     * EmptySea.
     * @param row row index in the ships array
     * @param column column index in the ships array
     * @return true, if this particular field is
     *         EmptySea
     *         false otherwise
     */
    boolean isOccupied(int row, int column) {
        return !(ships[row][column] instanceof EmptySea);
    }

    /**
     * Randomly places one ship.
     * Is needed in the <code>placeAllShipsRandomly()</code>
     * method.
     * @param ship the ship, that is going to be placed
     *             in the Ocean.
     * @see Ocean#placeAllShipsRandomly()
     */
    private void placeShipRandomly(Ship ship) {
        int row;
        int column;
        boolean horizontal;

        do {
            horizontal = rand.nextBoolean();
            if(horizontal) {
                row = rand.nextInt(10);
                column = rand.nextInt(11 - ship.GetLength());
            } else {
                row = rand.nextInt(11 - ship.GetLength());
                column = rand.nextInt(10);
            }
        } while (!ship.okToPlaceShipAt(row, column, horizontal, this));

        ship.placeShipAt(row, column, horizontal, this);
    }
}
