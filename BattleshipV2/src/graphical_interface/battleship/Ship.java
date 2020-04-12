package graphical_interface.battleship;

public abstract class Ship {
    /*
     * FIELDS
     */
    final private int MaxCoord = 9;
    final private int MinCoord = 0;

    private int bowRow;
    private int bowColumn;
    private boolean horizontal;
    private int length;

    boolean[] hit = new boolean[4];

    /*
     * ACCESSORS
     */

    int getBowRow() {
        return bowRow;
    }


    private void setBowRow(int row) {
        bowRow = row;
    }

    int getBowColumn() {
        return bowColumn;
    }

    private void setBowColumn(int column) {
        bowColumn = column;
    }

    boolean isHorizontal() {
        return horizontal;
    }

    private void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    int GetLength() {
        return length;
    }

    void setLength(int length) {
        this.length = length;
    }

    boolean[] getHitArray() {
        return hit;
    }

    /*
     * METHODS
     */

    /**
     * Returns the type of this ship.
     * This method exists only to be overridden, so it doesn't
     * much matter what it returns.
     */
    public String getShipType() {
        return "";
    }

    /**
     * Says whether it is legal to place ship here ([row, column]).
     * The ship must not overlap another ship, or touch another ship
     * (vertically, horizontally, or diagonally), and it must not "stick out"
     * beyond the array. Does not actually change either the ship or the Ocean.
     * @param row index of bow row in the ships array
     * @param column index of bow column in the ships array
     * @param horizontal whether the ship is horizontal
     * @param ocean link to the Ocean object, in which array we are
     *              going to place the ship.
     * @return  true if it is okay to put a ship of this length
     *          with its bow in this location, with the given orientation.
     *          Returns false otherwise.
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if(horizontal && column + length - 1 > MaxCoord) {
            return false;
        } else if(!horizontal && row + length - 1 > MaxCoord) {
            return false;
        }

        if(horizontal) {
            for(int i = row - 1; i <= row + 1; i++) {
                for(int j = column - 1; j <= column + length + 1; j++) {
                    if(isInOcean(i, j) && ocean.isOccupied(i, j)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            for(int i = row - 1; i <= row + length + 1; i++) {
                for(int j = column - 1; j <= column + 1; j++) {
                    if(isInOcean(i, j) && ocean.isOccupied(i, j)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * "Puts" the ship in the ocean.
     * It involves putting a reference to the ship in each
     * of 1 or more locations (up to 4) in the ships array in
     * the Ocean object.
     * @param row index of bow row in the ships array
     * @param column index of bow column in the ships array
     * @param horizontal whether the ship is horizontal
     * @param ocean link to the Ocean object, in which array we are
     *              going to place the ship.
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        setBowRow(row);
        setBowColumn(column);
        setHorizontal(horizontal);
        if(horizontal) {
            for(int i = 0; i < length; i++) {
                ocean.getShipArray()[row][column + i] = this;
            }
        } else {
            for(int i = 0; i < length; i++) {
                ocean.getShipArray()[row + i][column] = this;
            }
        }
    }

    /**
     * Implements "shooting" in the ship - marking
     * in the hit array the certain part of the ship
     * as being hit.
     * @param row row index in the ships array
     * @param column column index in the ships array
     * @return true, if a part of the ship occupies the given row and column,
     *         and the ship hasn't been sunk.
     *         false otherwise.
     */
    boolean shootAt(int row, int column) {
        if(isSunk()) {
            return false;
        } else if (isHorizontal()) {
            hit[column - getBowColumn()] = true;
        } else {
            hit[row - getBowRow()] = true;
        }
        return true;
    }

    /**
     * Checks, if the ship is sunk.
     * @return true, if every part of the ship has been hit.
     *         false otherwise.
     */
    public boolean isSunk() {
        for (int i = 0; i < GetLength(); i++) {
            if (!hit[i]) return false;
        }
        return true;
    }

    /**
     * Checks, if the ship is safe.
     * @return true, if none part of the ship has been hit.
     *         false otherwise.
     */
    public boolean isSafe() {
        for (int i = 0; i < GetLength(); i++) {
            if (hit[i]) return false;
        }
        return true;
    }

    /**
     * Checks, if the given coordinates match the size of
     * the ship array in the Ocean.
     * @param row row index in the ships array
     * @param column column index in the ships array
     * @return true, if coordinates match the size of
     *         the ship array in the Ocean.
     */
    private boolean isInOcean(int row, int column) {
        return row >= MinCoord && row <= MaxCoord && column >= MinCoord && column <= MaxCoord;
    }
}