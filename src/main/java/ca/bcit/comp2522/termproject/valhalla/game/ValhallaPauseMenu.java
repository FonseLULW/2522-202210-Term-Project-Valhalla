package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ValhallaPauseMenu extends FXGLMenu {
    public ValhallaPauseMenu() {
//    private static final double HEIGHT = Game.APP_HEIGHT / 2.0;
        super(MenuType.GAME_MENU);

        addChild(createOverlay());
        addChild(createBackground());
        addChild(new PauseMenu());

    }

    private Node createOverlay() {
        Node overlay = FXGL.texture("pauseoverlay.png", Game.APP_WIDTH, Game.APP_HEIGHT + 1).darker();
        overlay.setOpacity(0.8);
        return overlay;
    }

    private Node createBackground() {
        Node bg = FXGL.texture("pausebg.png", Game.APP_WIDTH, Game.APP_HEIGHT);
        return bg;
    }

    private class PauseMenu extends VBox {
        private static final Insets PADDING = new Insets(5.0, 0, 5.0, 0);
        private static final double BUTTON_SPACING = 10.0;
        private static final double WIDTH = Game.APP_WIDTH / 10.0;
        private static final double HEIGHT = Game.APP_HEIGHT / 4.0;
        private static final double POS_X = (Game.APP_WIDTH / 2.0) - WIDTH / 2.0;
        private static final double POS_Y = Game.APP_HEIGHT / 2.0;

        public PauseMenu() {
            super(BUTTON_SPACING);

            ValhallaButton resumeBtn = createResumeBtn();
            ValhallaButton logoutBtn = createLogoutBtn();
            getChildren().addAll(resumeBtn, logoutBtn);
            setWidth(WIDTH);
            setHeight(HEIGHT);
            setTranslateX(POS_X);
            setTranslateY(POS_Y);
            setAlignment(Pos.CENTER);
            setStyle("-fx-background-color: #fa0000");
//            setOpacity(0.9);
        }

        private ValhallaButton createResumeBtn() {
            // create resume btn
            ValhallaButton resume = new ValhallaButton("Resume");
            resume.setMinWidth(WIDTH);
            return resume;
        }

        private ValhallaButton createLogoutBtn() {
            // create LogoutBtn
            ValhallaButton exit = new ValhallaButton("Logout");
            exit.setMinWidth(WIDTH);
            return exit;
        }
    }
}
