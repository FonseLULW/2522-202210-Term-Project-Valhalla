package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.audio.Audio;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

public class ValhallaPauseMenu extends FXGLMenu {
    public ValhallaPauseMenu() {
        super(MenuType.GAME_MENU);

        addChild(createOverlay());
        addChild(createBackground());
        addChild(new PauseMenu());

    }

    private Node createOverlay() {
        final double opacity = 0.8;
        Node overlay = FXGL.texture("pauseoverlay.png", Game.APP_WIDTH, Game.APP_HEIGHT + 1).darker();
        overlay.setOpacity(opacity);
        return overlay;
    }

    private Node createBackground() {
        return FXGL.texture("pausebg.png", Game.APP_WIDTH, Game.APP_HEIGHT);
    }

    @Override
    public void onEnteredFrom(@NotNull final Scene prevState) {
        final double bgmVolume = 0.20;
        FXGL.getAudioPlayer().pauseAllMusic();
        FXGL.loopBGM("pause.mp3");
        FXGL.getSettings().setGlobalMusicVolume(bgmVolume);
    }

    @Override
    public void onExitingTo(@NotNull final Scene nextState) {
        FXGL.getAudioPlayer().pauseAllMusic();
        FXGL.loopBGM("bensound-instinct.mp3");
    }

    private class PauseMenu extends VBox {
        private static final double BUTTON_SPACING = 10.0;
        private static final double WIDTH = Game.APP_WIDTH / 10.0;
        private static final double HEIGHT = Game.APP_HEIGHT / 4.0;
        private static final double POS_X = (Game.APP_WIDTH / 2.0) - WIDTH / 2.0;
        private static final double POS_Y = Game.APP_HEIGHT / 2.0;

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

        private ValhallaButton createResumeBtn() {
            ValhallaButton resume = new ValhallaButton("Resume");
            resume.setMinWidth(WIDTH);
            return resume;
        }

        private ValhallaButton createLogoutBtn() {
            ValhallaButton exit = new ValhallaButton("Logout");
            exit.setMinWidth(WIDTH);
            return exit;
        }
    }
}
