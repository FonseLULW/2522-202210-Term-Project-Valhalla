package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.entities.Hero;
import javafx.application.Application;
import javafx.application.Platform;
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
    private Group currentRoot;
    private Scene currentScene;
    private Group gameplayRoot;
    private GameplayScene gameplayScene;
    private Group titleRoot;
    private TitleScene titleScene;


    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 563;
    private static final String APP_TITLE = "Valhalla";


    @Override
    public void start(final Stage stage) {
        gameStage = stage;
        gameStage.setTitle(APP_TITLE);

        gameplayRoot = new Group();
        titleRoot = new Group();

        gameplayScene = new GameplayScene();
        titleScene = new TitleScene();

//        gameplayScene.initRoot();
        titleScene.initRoot();

        gameplayScene.setOnKeyPressed(gameplayScene::handleEvent);
        gameplayScene.setOnKeyReleased(gameplayScene::handleEvent);
        titleScene.setOnKeyPressed(titleScene::handleEvent);

        gameplayScene.startScene();
//        titleScene.switchScene();

        currentScene = titleScene;

        // this is the game loop
//        final Clock clock = new Clock() {
//            @Override
//            public void handle(final long timestamp) {
//                // move
////                gameplayRoot.getChildren().get(1).move();
//            }
//        };
//        clock.start();

        // display the screen
        gameStage.setScene(currentScene);
        gameStage.show();
    }

    private class GameplayScene extends Scene {
        private Hero hero;

        public GameplayScene() {
            super(gameplayRoot, APP_WIDTH, APP_HEIGHT, Color.GAINSBORO);
            initRoot();
        }

        public void switchScene() {
            hero.setVelocityX(0);
            hero.setVelocityY(0);
            gameStage.setScene(titleScene);
        }

        public void handleEvent(final KeyEvent event) {
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                switch (event.getCode()) {
                    case ESCAPE:
                        switchScene();
                        break;
                    case A:
                        hero.setVelocityX(-5);
                        break;
                    case D:
                        hero.setVelocityX(5);
                        break;
                    case W:
                        hero.setVelocityY(-5);
                        break;
                    case S:
                        hero.setVelocityY(5);
                        break;
                    default:
                        break;
                }
            } else {
                switch (event.getCode()) {
                    case A:
                    case D:
                        hero.setVelocityX(0);
                        break;
                    case W:
                    case S:
                        hero.setVelocityY(0);
                        break;
                    default:
                        break;
                }
            }
        }

        public void initRoot() {
            // stuff inside gameplay screen
            hero = new Hero("hero1_idle.jpeg");
            gameplayRoot.getChildren().add(hero);
            hero.setHeight(60);
        }

        public void startScene() {
            final Clock clock = new Clock() {
                @Override
                public void handle(final long timestamp) {
                    // move
                    hero.move();
                }
            };
            clock.start();
        }
    }

    private class TitleScene extends Scene {
        public TitleScene() {
            super(titleRoot, APP_WIDTH, APP_HEIGHT, Color.BURLYWOOD);
        }

        public void switchScene() {
            gameStage.setScene(gameplayScene);
        }

        public void handleEvent(final KeyEvent event) {
            switch (event.getCode()) {
                case ESCAPE:
                    switchScene();
                    break;
                case X:
                    Platform.exit();
                default:
                    break;
            }
        }

        public void initRoot() {
            // stuff inside title screen
            Text text = new Text(10, 50, "hello!");
            titleRoot.getChildren().add(text);
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
