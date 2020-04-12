module BattleshipV2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens graphical_interface to javafx.fxml;
    exports graphical_interface;
}

