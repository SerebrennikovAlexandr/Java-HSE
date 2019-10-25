package battleship;

class Cruiser extends Ship {
    /*
     * CONSTRUCTORS
     */
    Cruiser() {
        setLength(3);
    }

    /*
     * METHODS
     */

    /**
     * @return "cruiser" - type name
     *                      of this ship.
     */
    @Override
    public String getShipType() {
        return "cruiser";
    }
}
