package battleship;

class Battleship extends Ship {
    /*
     * CONSTRUCTORS
     */
    Battleship() {
        setLength(4);
    }

    /*
     * METHODS
     */

    /**
     * @return "battleship" - type name
     *                      of this ship.
     */
    @Override
    String getShipType() {
        return "battleship";
    }
}
