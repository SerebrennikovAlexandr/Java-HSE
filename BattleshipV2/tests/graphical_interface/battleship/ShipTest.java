package graphical_interface.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    //Произведено тестирование основных методов, отвечающих за коректную расстановку кораблей.

    @Test
    void okToPlaceShipAt() {
        Ocean ocean = new Ocean();
        Cruiser cruiser1 = new Cruiser();
        cruiser1.placeShipAt(4, 4, false, ocean);
        Cruiser cruiser2 = new Cruiser();

        assertTrue(cruiser2.okToPlaceShipAt(2, 3, true, ocean));

        for(int i = 3; i < 8; i++) {
            for(int j = 2; j < 6; j++) {
                assertFalse(cruiser2.okToPlaceShipAt(i, j, true, ocean));
            }
        }
        assertFalse(cruiser2.okToPlaceShipAt(100, 100, true, ocean));
    }

    @Test
    void placeShipAt() {
        Ocean ocean = new Ocean();
        Cruiser cruiser1 = new Cruiser();
        cruiser1.placeShipAt(4, 4, false, ocean);

        assert(ocean.getShipArray()[4][4] instanceof Cruiser);
        assert(ocean.getShipArray()[5][4] instanceof Cruiser);
        assert(ocean.getShipArray()[6][4] instanceof Cruiser);
        assertFalse(ocean.getShipArray()[7][4] instanceof Cruiser);

        Submarine sub = new Submarine();
        sub.placeShipAt(9, 9, false, ocean);
        assert(ocean.getShipArray()[9][9] instanceof Submarine);

        Cruiser cruiser = new Cruiser();
        cruiser.placeShipAt(0, 0, false, ocean);
        assert(ocean.getShipArray()[0][0] instanceof Cruiser);
        assert(ocean.getShipArray()[1][0] instanceof Cruiser);
        assert(ocean.getShipArray()[2][0] instanceof Cruiser);
    }

    // Тестирование стрельбы

    @Test
    void shootAt() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(ocean.getShipArray()[i][j] instanceof Battleship
                        || ocean.getShipArray()[i][j] instanceof Submarine
                        || ocean.getShipArray()[i][j] instanceof Destroyer
                        || ocean.getShipArray()[i][j] instanceof Cruiser) {
                    assert(ocean.getShipArray()[i][j].shootAt(i, j));
                } else {
                    assertFalse(ocean.getShipArray()[i][j].shootAt(i, j));
                }
            }
        }
    }
}