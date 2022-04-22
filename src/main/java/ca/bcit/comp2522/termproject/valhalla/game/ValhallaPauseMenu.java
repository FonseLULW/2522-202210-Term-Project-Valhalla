package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

/**
 * A ValhallaPauseMenu class representing the game's pause menu.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public class ValhallaPauseMenu extends FXGLMenu {

    /**
     * Constructs a new ValhallaPauseMenu.
     */
    public ValhallaPauseMenu() {
        super(MenuType.GAME_MENU);

        addChild(createOverlay());
        addChild(createBackground());
        addChild(new PauseMenu());

    }

    /*
     * Creates the black overlay used in the ValhallaPauseMenu.
     */
    private Node createOverlay() {
        final double opacity = 0.8;
        Node overlay = FXGL.texture("pauseoverlay.png", Game.APP_WIDTH, Game.APP_HEIGHT + 1).darker();
        overlay.setOpacity(opacity);
        return overlay;
    }

    /*
     * Creates the background used in the ValhallaPauseMenu.
     */
    private Node createBackground() {
        return FXGL.texture("pausebg.png", Game.APP_WIDTH, Game.APP_HEIGHT);
    }

    /**
     * Runs when entering or switching to this ValhallaPauseMenu.
     * @param prevState a Scene being entered from
     */
    @Override
    public void onEnteredFrom(@NotNull final Scene prevState) {
        final double bgmVolume = 0.20;
        FXGL.getAudioPlayer().pauseAllMusic();
        MusicPlayer.getSingleton().playGameMenuMusic();
        FXGL.getSettings().setGlobalMusicVolume(bgmVolume);
    }

    /**
     * Runs when exiting this ValhallaPauseMenu.
     * @param nextState a Scene to exit to
     */
    @Override
    public void onExitingTo(@NotNull final Scene nextState) {
        FXGL.getAudioPlayer().pauseAllMusic();
        MusicPlayer.getSingleton().playGameMusic();
    }

    /*
     * A PauseMenu class containing the buttons of the ValhallaPauseMenu.
     */
    private class PauseMenu extends VBox {
        private static final double BUTTON_SPACING = 10.0;
        private static final double WIDTH = Game.APP_WIDTH / 10.0;
        private static final double HEIGHT = Game.APP_HEIGHT / 4.0;
        private static final double POS_X = (Game.APP_WIDTH / 2.0) - WIDTH / 2.0;
        private static final double POS_Y = Game.APP_HEIGHT / 2.0;

        /*
         * Constructs a PauseMenu.
         */
        PauseMenu() {
            super(BUTTON_SPACING);

            ValhallaButton resumeBtn = createResumeBtn();
            ValhallaButton logoutBtn = createLogoutBtn();
            getChildren().addAll(resumeBtn, logoutBtn);

            resumeBtn.setAction(ValhallaPauseMenu.this::fireResume);
            logoutBtn.setAction(() -> {
                System.out.println("Exiting database");
                fireExitToMainMenu();
            });

            setWidth(WIDTH);
            setHeight(HEIGHT);
            setTranslateX(POS_X);
            setTranslateY(POS_Y);
            setAlignment(Pos.CENTER);
        }

        /*
         * Creates a resume button for this PauseMenu.
         */
        private ValhallaButton createResumeBtn() {
            ValhallaButton resume = new ValhallaButton("Resume");
            resume.setMinWidth(WIDTH);
            return resume;
        }

        /*
         * Creates a logout button for this PauseMenu.
         */
        private ValhallaButton createLogoutBtn() {
            ValhallaButton exit = new ValhallaButton("Logout");
            exit.setMinWidth(WIDTH);
            return exit;
        }
    }
}
