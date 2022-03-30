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
        Hero hero = new Hero("![](../../../../../../../../../resources/img/hero1_attack2.png)");
        root.getChildren().add(hero);
        root.getChildren();
        hero.setHeight(60);

        // this is the game loop
        final Clock clock = new Clock() {
            @Override
            public void handle(final long timestamp) {
                // move
                hero.move();
            }
        };
        clock.start();

        scene.setOnKeyPressed((event) -> {
            switch (event.getCode()) {
                case A:
                    hero.setVelocityX(-10);
                    break;
                case D:
                    hero.setVelocityX(10);
                    break;
                case W:
                    hero.setVelocityY(-10);
                    break;
                case S:
                    hero.setVelocityY(10);
                    break;
                default:
                    break;
            }

        });

        scene.setOnKeyReleased((event) -> {
            switch (event.getCode()) {
                case A:
                    hero.setVelocityX(0);
                    break;
                case D:
                    hero.setVelocityX(0);
                    break;
                case W:
                    hero.setVelocityY(0);
                    break;
                case S:
                    hero.setVelocityY(0);
                    break;
                default:
                    break;
            }

        });

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
