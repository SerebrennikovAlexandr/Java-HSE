package graphical_interface.battleship;

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
    public String getShipType() {
        return "battleship";
    }
}
