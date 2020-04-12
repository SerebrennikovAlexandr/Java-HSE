/*
This Project was made by Serebrennikov Alexander, BSE181.
April, 2020.
 */

package graphical_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Method generates the Main Scene of the app.
     * Main scene - Start menu scene.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        primaryStage.setTitle("Battleship-2");
        Scene scene = new Scene(root, 720, 480);
        scene.getStylesheets().add("main.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * The start point of the application, starts the app
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
