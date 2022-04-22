package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.runOnce;

public class CutsceneManager {
    private final String cutsceneBackgroundFilename;

    public CutsceneManager(final String cutsceneBackgroundFilename) {
        this.cutsceneBackgroundFilename = cutsceneBackgroundFilename;
    }

    public void playCutscene(final String cutsceneFilename, final Runnable afterCutscene) {
        runOnce(() -> {
            var cutsceneBackground = FXGL.texture(cutsceneBackgroundFilename, Game.APP_WIDTH, Game.APP_HEIGHT);
            FXGL.getGameScene().addUINode(cutsceneBackground);
            var lines = getAssetLoader().loadText(cutsceneFilename);
            var cutscene = new Cutscene(lines);
            FXGL.getCutsceneService().startCutscene(cutscene, () -> {
                FXGL.getGameScene().removeUINode(cutsceneBackground);
                if (afterCutscene != null) {
                    afterCutscene.run();
                }
            });
            return null;
        }, Duration.seconds(1));
    }

    public void playCutscene(final String cutsceneFilename) {
        playCutscene(cutsceneFilename, null);
    }
}
