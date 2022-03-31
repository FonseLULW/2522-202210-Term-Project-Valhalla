package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.achievement.Achievement;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;

public class Game extends GameApplication {
    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 563;

    @Override
    protected void initSettings(final GameSettings settings) {
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.setTitle("Valhalla");
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(false);
        settings.setPreserveResizeRatio(true);
        settings.setDefaultCursor(new CursorInfo("heejo_idle.png", 0, 0));
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new ValhallaMenu();
            }

//            @Override
//            public FXGLMenu newGameMenu() {
//                return new ValhallaMenu();
//            }
        });
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new NodeFactory());

        FXGL.spawn("hero", 60, 60);

    }

    public static void main(final String[] args) {
        launch(args);
    }
}
