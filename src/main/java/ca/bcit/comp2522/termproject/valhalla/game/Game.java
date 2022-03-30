package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.entities.Hero;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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
//    private Group currentRoot;
    private Scene currentScene;
//    private Group gameplayRoot;
    private Scene gameplayScene;
//    private Group titleRoot;
    private Scene titleScene;


    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 563;
    private static final String APP_TITLE = "Valhalla";


    @Override
    public void start(final Stage stage) {
        gameStage = stage;
        gameStage.setTitle(APP_TITLE);

        Group gameplayRoot = initGameplayRoot();
        Group titleRoot = initTitleRoot();
        gameplayScene = new Scene(gameplayRoot, APP_WIDTH, APP_HEIGHT, Color.GAINSBORO);
        titleScene = new Scene(titleRoot, APP_WIDTH, APP_HEIGHT, Color.BURLYWOOD);

        gameplayScene.setOnKeyPressed(this::handleEvent);
        titleScene.setOnKeyPressed(this::handleEvent);

        currentScene = titleScene;

        // this is the game loop
        final Clock clock = new Clock() {
            @Override
            public void handle(final long timestamp) {
                // move
//                hero.move();
            }
        };
        clock.start();

        // display the screen
        gameStage.setScene(currentScene);
        gameStage.show();
    }

    public Group initGameplayRoot() {
        Group root = new Group();

        // stuff inside gameplay screen
        Hero hero = new Hero("hero1_idle.jpeg");
        root.getChildren().add(hero);
        hero.setHeight(60);
        return root;
    }

    public Group initTitleRoot() {
        Group root = new Group();

        // stuff inside title screen
        Text text = new Text(10, 50, "hello!");
        root.getChildren().add(text);
        return root;
    }

    public void switchScene() {
        System.out.println("Print");
        if (currentScene.equals(titleScene)) {
            currentScene = gameplayScene;
            gameStage.setScene(gameplayScene);
        } else {
            currentScene = titleScene;
            gameStage.setScene(titleScene);
        }
    }

    public void handleEvent(final KeyEvent event) {
        switch (event.getCode()) {
            case ESCAPE:
                switchScene();
                break;
            default:
                break;
        }
    }

    /**
     * Drives Game.
     * @param args command line args
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
