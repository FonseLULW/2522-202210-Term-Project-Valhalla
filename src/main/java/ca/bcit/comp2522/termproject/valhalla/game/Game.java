package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.entities.SpeedComponent;
import com.almasb.fxgl.achievement.Achievement;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.*;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.dsl.FXGL;

import ca.bcit.comp2522.termproject.valhalla.data.TowerData;
import ca.bcit.comp2522.termproject.valhalla.constant.TowerType;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;

import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;


public class Game extends GameApplication {
    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 563;

    private Entity hero;
    @Override
    protected void initSettings(final GameSettings settings) {
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.setTitle("Valhalla");
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        settings.setVersion("1.0-SNAPSHOT");
        settings.setAppIcon("slugman_1.png");
        settings.setPreserveResizeRatio(true);
        settings.setCloseConfirmation(false);
        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new ValhallaMenu();
            }

            @Override
            public FXGLMenu newGameMenu() {
                return new ValhallaPauseMenu();
            }
        });
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new NodeFactory());
        FXGL.getGameScene().setBackgroundRepeat("map.png");
        hero = FXGL.spawn("hero");
//        hero.getBottomY();
//        System.out.println(hero.getBottomY());
    }

    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("up") {
            @Override
            protected void onAction() {
                hero.translateY(-hero.getComponent(SpeedComponent.class).getSpeed());
            }
        }, KeyCode.W);
        input.addAction(new UserAction("right") {
            @Override
            protected void onAction() {
                hero.translateX(hero.getComponent(SpeedComponent.class).getSpeed());
            }
        }, KeyCode.D);
        input.addAction(new UserAction("down") {
            @Override
            protected void onAction() {
                hero.translateY(hero.getComponent(SpeedComponent.class).getSpeed());
            }
        }, KeyCode.S);
        input.addAction(new UserAction("left") {
            @Override
            protected void onAction() {
                hero.translateX(-hero.getComponent(SpeedComponent.class).getSpeed());
            }
        }, KeyCode.A);

    }

//    @Override
//    protected void initPhysics() {
//        super.initPhysics();
//    }

    private void buildTower(final TowerType towerType) {
    }

    private TowerData getTowerData(final TowerType towerType) {
        return null; // need to add
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
