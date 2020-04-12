package graphical_interface.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OceanTest {

    // Тестирование стрельбы

    @Test
    void shootAt() {
        Ocean ocean = new Ocean();
        Battleship battleship = new Battleship();
        battleship.placeShipAt(4, 4, true, ocean);
        Submarine submarine = new Submarine();
        submarine.placeShipAt(0, 0, true, ocean);
        Cruiser cruiser = new Cruiser();
        cruiser.placeShipAt(9, 0, true, ocean);
        Destroyer destroyer = new Destroyer();
        destroyer.placeShipAt(0, 9, false, ocean);

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                int shots = ocean.getShotsFired();
                int hits = ocean.getHitCount();
                if(ocean.getShipArray()[i][j] instanceof Battleship
                        || ocean.getShipArray()[i][j] instanceof Submarine
                        || ocean.getShipArray()[i][j] instanceof Destroyer
                        || ocean.getShipArray()[i][j] instanceof Cruiser) {
                    boolean s = ocean.shootAt(i , j);
                    assert(s);
                    assert(ocean.getShotsFired() - shots == 1);
                    assert(ocean.getHitCount() - hits == 1);
                } else {
                    boolean s = ocean.shootAt(i , j);
                    assertFalse(s);
                    assert(ocean.getShotsFired() - shots == 1);
                    assert(ocean.getHitCount() - hits == 0);
                }
            }
        }
        assert(ocean.getShipsSunk() == 4);
    }

    // Тест окончания игры

    @Test
    void isGameOver() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                ocean.shootAt(i , j);
            }
        }
        assert(ocean.isGameOver());

        ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (ocean.getShipArray()[i][j] instanceof Battleship
                        || ocean.getShipArray()[i][j] instanceof Submarine
                        || ocean.getShipArray()[i][j] instanceof Destroyer
                        || ocean.getShipArray()[i][j] instanceof Cruiser) {
                    ocean.shootAt(i, j);
                }
            }
        }
        assert(ocean.isGameOver());
    }

}