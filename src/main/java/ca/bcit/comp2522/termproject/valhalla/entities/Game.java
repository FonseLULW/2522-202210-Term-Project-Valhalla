package ca.bcit.comp2522.termproject.valhalla.entities;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;

public class Game extends Application {
    private static final int APP_WIDTH = 1000;
    private static final int APP_HEIGHT = 563;
    private static final String APP_TITLE = "Valhalla";

    @Override
    public void start(final Stage stage) {
        // group
        Group root = new Group();
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT, Color.GAINSBORO);

        // game
        Hero hero = new Hero();
        root.getChildren().add(hero);

        stage.setTitle(APP_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Drives Game.
     * @param args command line args
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
