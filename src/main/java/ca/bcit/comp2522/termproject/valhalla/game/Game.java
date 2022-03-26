package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.entities.Hero;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * The Game class.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public class Game extends Application {
    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 563;
    private static final String APP_TITLE = "Valhalla";

    @Override
    public void start(final Stage stage) {
        // application setup
        Group root = new Group();
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT, Color.GAINSBORO);
        stage.setTitle(APP_TITLE);

        // game setup
        Hero hero = new Hero("baymax.jpg");
        root.getChildren().add(hero.getSprite());
        root.getChildren();

        // display the screen
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
