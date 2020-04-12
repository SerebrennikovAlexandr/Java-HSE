package graphical_interface;

import graphical_interface.battleship.Ocean;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameController {

    /*
     * Fields
     */

    private Ocean ocean;

    @FXML
    Label p, o, b, e, d, a;

    @FXML
    TextField totalShots;

    @FXML
    TextField sunk;

    @FXML
    TextField shot;

    @FXML
    TextField safe;

    @FXML
    TextField coordinateX, coordinateY;

    @FXML
    GridPane seaGrid;

    @FXML
    TextArea output;

    /*
     * Initializer
     */

    /**
     * This method actually starts the game.
     * It generates new Ocean field and randomly fills it with ships.
     * Then it initializes 10x10 Buttons in seaGrid GridPane asa a GUI for Ocean.
     * For each button it sets onSeaActionOuter - the method to be invoked
     * with mouse click or pressing Enter.
     */
    @FXML
    void initialize() {
        ocean = new Ocean();
        ocean.placeAllShipsRandomly();

        totalShots.setText("0");
        sunk.setText("0");
        shot.setText("0");
        safe.setText("10");

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                Button sea = new Button("");
                changeSeaImage(sea, "sea.jpg");

                sea.setMinSize(31, 31);
                sea.setPrefSize(31, 31);
                sea.setMaxSize(31, 31);
                sea.setOnAction(e -> onSeaActionOuter(sea));

                seaGrid.add(sea, i , j);
            }
        }
        output.appendText("- Командир, введите координаты\n для ведения огня!\n");
    }

    /*
     * Methods
     */

    /**
     * The method to be invoked with mouse click or pressing Enter.
     * Lets user activate button in the oceanGrid.
     * @param sea - Button in the oceanGrid, that was triggered by the event (clicking or pressing)
     */
    @FXML
    private void onSeaActionOuter(Button sea)
    {
        int x = GridPane.getRowIndex(sea);
        int y = GridPane.getColumnIndex(sea);
        boolean isSunk = ocean.getShipArray()[x][y].isSunk();

        if(isSunk) {
            output.appendText("Этот вражеский корабль потоплен.\nНевозможно выстрелить!\n");
            return;
        }

        shootAtSea(sea, x, y);

        if(!isSunk && ocean.getShipArray()[x][y].isSunk()) {

            output.appendText("Вы только что потопили " +
                    ocean.getShipArray()[x][y].getShipType() +
                    ".\n");

            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    if(ocean.getShipArray()[i][j].isSunk()) {
                        Button sea1 = getSeaButtonByIndex(i, j);
                        changeSeaImage(sea1, "sunk.jpg");
                    }
                }
            }
        }

        totalShots.setText("" + ocean.getShotsFired());
        sunk.setText("" + ocean.getShipsSunk());
        safe.setText("" + ocean.getShipsSafe());
        shot.setText("" + (10 - ocean.getShipsSunk() - ocean.getShipsSafe()));

        if(!ocean.isGameOver()) {
            output.appendText("- Командир, введите координаты\n для ведения огня!\n");
        } else {
            finishGame();
        }
    }

    /**
     * Implements shooting in GUI
     * @param sea - Button in the oceanGrid, that was triggered by the event (clicking or pressing)
     * @param x - its row position in the Ocean
     * @param y - its column position int the Ocean
     */
    private void shootAtSea(Button sea, int x, int y) {
        if(ocean.shootAt(x, y)) {
            output.appendText("Есть попадание!\n");
            changeSeaImage(sea, "hit.jpg");
        } else {
            output.appendText("Промах\n");
            changeSeaImage(sea, "splash.jpg");
        }
    }

    /**
     *
     * @param event - clicking or pressing Fire button
     */
    @FXML
    void onButtonFireAction(ActionEvent event) {
        String x = coordinateX.getText();
        String y = coordinateY.getText();
        if(x.equals("") || y.equals("")) {
            output.appendText("Укажите координаты точнее.\n");
            return;
        }
        if(notCoordinate(x) || notCoordinate(y)) {
            return;
        } else {
            int row = Integer.parseInt(x);
            int column = Integer.parseInt(y);
            Button sea = getSeaButtonByIndex(row, column);
            onSeaActionOuter(sea);
        }
    }

    /**
     * This method implements the finish of the game.
     * In deactivates the seaGrid and game buttons, prints "pobeda".
     */
    private void finishGame() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                Button sea = getSeaButtonByIndex(i, j);
                sea.setDisable(true);
            }
        }

        coordinateX.setEditable(false);
        coordinateY.setEditable(false);
        p.setVisible(true);
        o.setVisible(true);
        b.setVisible(true);
        e.setVisible(true);
        d.setVisible(true);
        a.setVisible(true);

        output.appendText("Поздравляю! Вражеский флот побеждён!\n");
        output.appendText("Попаданий: " + ocean.getHitCount() + "\n");
        output.appendText("Всего выстрелов: " + ocean.getShotsFired() + "\n");
    }

    /**
     * Method for changing a button image.
     * @param sea - Button from seaGrid.
     * @param name - String name of image from the resources
     */
    private void changeSeaImage(Button sea, String name) {
        Image image = new Image(name);
        sea.graphicProperty().setValue(new ImageView(image));
    }

    /**
     * Method for finding the proper Button in seaGrid
     * @param row - int
     * @param column - int
     * @return - Button, that is in the given row and column of seaGrid
     */
    private Button getSeaButtonByIndex(final int row, final int column) {
        ObservableList<Node> childrens = seaGrid.getChildren();

        for (Node node : childrens) {
            if(node instanceof Button && GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return (Button) node;
            }
        }
        return null;
    }

    /**
     * Method that is activated by clicking or pressing Retreat button.
     * Changes Game scene to the Start menu scene.
     * @param event - clicking or pressing Retreat button
     * @throws Exception
     */
    public void onButtonRetreatClicked(ActionEvent event) throws Exception {
        Parent rulesRoot = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        Scene rulesScene = new Scene(rulesRoot, 720, 480);
        rulesScene.getStylesheets().add("main.css");

        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        currentStage.setScene(rulesScene);
        currentStage.show();
    }

    /**
     * Checks if the given string can be an integer number
     * and a coordinate in Ocean.
     * @param   str input string.
     * @return      false, if <code>str<code/>
     *              can be converted to Integer and is
     *              in the [0;9] interval.
     *              true, if not.
     */
    private boolean notCoordinate(String str) {
        try {
            int res = Integer.parseInt(str);
            if(res < 0 || res > 9) {
                output.appendText("Координата вне поля боя!\n");
                return true;
            }
            return false;
        } catch (Exception e) {
            output.appendText("Неверные координаты!\nУкажите число от 0 до 9.\n");
            return true;
        }
    }
}
