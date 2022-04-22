package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.runOnce;

/**
 * A CutsceneManager class.
 * @author FonseLULW
 * @author kaioh
 * @version 1.0
 * @param cutsceneBackgroundFilename a String representing the filename of the background displayed during the cutscene.
 */
public record CutsceneManager(String cutsceneBackgroundFilename) {
    /**
     * Constructs a new CutsceneManager.
     *
     * @param cutsceneBackgroundFilename a String representing the filename of the cutscene background
     */
    public CutsceneManager {
    }

    /**
     * Plays the cutscene.
     *
     * @param cutsceneFilename a String representing a txt file containing the cutscene script formatted
     *                         according to FXGL's cutscene format
     * @param afterCutscene    a Runnable that runs after the cutscene ends
     */
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

    /**
     * Plays the cutscene.
     *
     * @param cutsceneFilename a String representing a txt file containing the cutscene script formatted
     *                         according to FXGL's cutscene format
     */
    public void playCutscene(final String cutsceneFilename) {
        playCutscene(cutsceneFilename, null);
    }
}
