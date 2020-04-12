package graphical_interface.battleship;

class Submarine extends Ship {
    /**
     * Constructors
     */
    Submarine() {
        setLength(1);
    }

    /**
     * Methods
     */
    @Override
    public String getShipType() {
        return "submarine";
    }
}
