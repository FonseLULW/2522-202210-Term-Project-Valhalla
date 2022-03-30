package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.entities.Hero;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
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
    private Stage gameStage;
    private Scene currentScene;
    private Scene gameplayScene;
    private Scene titleScene;


    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 563;
    private static final String APP_TITLE = "Valhalla";


    @Override
    public void start(final Stage stage) {
        gameStage = stage;
        gameStage.setTitle(APP_TITLE);

        gameplayScene = initGameplayScene();
        titleScene = initTitleScene();

        // application setup
        currentScene = titleScene;

        currentScene.setOnKeyPressed((event) -> {
            switch (event.getCode()) {
                case ESCAPE:
                    switchScene();
                    break;
                default:
                    break;
            }
        });

        // this is the game loop
        final Clock clock = new Clock() {
            @Override
            public void handle(final long timestamp) {
                // move
//                hero.move();
            }
        };
        clock.start();

//        currentScene.setOnKeyPressed((event) -> {
//            switch (event.getCode()) {
//                case A:
//                    hero.setVelocityX(-10);
//                    break;
//                case D:
//                    hero.setVelocityX(10);
//                    break;
//                case W:
//                    hero.setVelocityY(-10);
//                    break;
//                case S:
//                    hero.setVelocityY(10);
//                    break;
//                default:
//                    break;
//            }
//
//        });
//
//        currentScene.setOnKeyReleased((event) -> {
//            switch (event.getCode()) {
//                case A:
//                    hero.setVelocityX(0);
//                    break;
//                case D:
//                    hero.setVelocityX(0);
//                    break;
//                case W:
//                    hero.setVelocityY(0);
//                    break;
//                case S:
//                    hero.setVelocityY(0);
//                    break;
//                default:
//                    break;
//            }
//
//        });

        // display the screen
        stage.setScene(currentScene);
        stage.show();
    }

    public Scene initGameplayScene() {
        Group root = new Group();
        gameplayScene = new Scene(root, APP_WIDTH, APP_HEIGHT, Color.GAINSBORO);

        // stuff inside gameplay scene
        Hero hero = new Hero("hero1_idle.jpeg");
        root.getChildren().add(hero);
        hero.setHeight(60);
        return gameplayScene;
    }

    public Scene initTitleScene() {
        Group root = new Group();
        titleScene = new Scene(root, APP_WIDTH, APP_HEIGHT, Color.BURLYWOOD);
        return titleScene;
    }

    public void switchScene() {
        currentScene = gameplayScene;
        gameStage.setScene(currentScene);
    }

    /**
     * Drives Game.
     * @param args command line args
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
