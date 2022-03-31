package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;

public class Game extends GameApplication {
    @Override
    protected void initSettings(final GameSettings gameSettings) {

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
