package graphical_interface.battleship;

class Destroyer extends Ship {
    /*
     * CONSTRUCTORS
     */
    Destroyer() {
        setLength(2);
    }

    /*
     * METHODS
     */

    /**
     * @return "destroyer" - type name
     *                      of this ship.
     */
    @Override
    public String getShipType() {
        return "destroyer";
    }
}
