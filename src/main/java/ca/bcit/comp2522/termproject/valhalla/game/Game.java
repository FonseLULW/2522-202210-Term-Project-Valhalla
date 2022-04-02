package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.achievement.Achievement;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;

import ca.bcit.comp2522.termproject.valhalla.data.TowerData;
import ca.bcit.comp2522.termproject.valhalla.constant.TowerType;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;


public class Game extends GameApplication {
    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 563;

    @Override
    protected void initSettings(final GameSettings settings) {
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.setTitle("Valhalla");
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        settings.setAppIcon("slugman_1.png");
        settings.setPreserveResizeRatio(true);
        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new ValhallaMenu();
            }

            @Override
            public FXGLMenu newGameMenu() {
                return new ValhallaMenu();
            }
        });
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new NodeFactory());
        FXGL.spawn("hero", 60, 60);
    }

    @Override
    protected void initInput() {

    }

    private void buildTower(final TowerType towerType) {
    }

    private TowerData getTowerData(final TowerType towerType) {
        return null; // need to add
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
