package graphical_interface.battleship;

class EmptySea extends Ship {
    /*
     * CONSTRUCTORS
     */
    EmptySea() {
        setLength(1);
    }

    /*
     *  METHODS
     */

    /**
     * Overrides shooting in the ship.
     * @param row row index in the ships array
     * @param column column index in the ships array
     * @return false
     */
    @Override
    boolean shootAt(int row, int column) {
        hit[0] = true;
        return false;
    }

    /**
     * Empty sea can't be sunk.
     * @return false
     */
    @Override
    public boolean isSunk() {
        return false;
    }
}
