package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ValhallaMenu extends FXGLMenu {

    public ValhallaMenu() {
        super(MenuType.MAIN_MENU);
        addChild(createBackground());

        // create menu elements here; menu elements must extend javafx Node
        ValhallaButton btnExit = new ValhallaButton("Exit", 100, 200);
        ValhallaButton btnPlay = new ValhallaButton("Play", 100, 100);

        // create login form
        LoginForm loginForm = new LoginForm();
        ValhallaButton btnSubmit = new ValhallaButton("Submit", 0, 0);
        AnchorPane.setLeftAnchor(btnSubmit, LoginForm.LEFT_PADDING);
        AnchorPane.setBottomAnchor(btnSubmit, LoginForm.RIGHT_PADDING);
        loginForm.getChildren().add(btnSubmit);
        loginForm.setVisible(false);

        // set the action of buttons
        btnPlay.setAction(() -> {
            loginForm.setVisible(true);
        });
        btnExit.setAction(this::fireExit);
        btnSubmit.setAction(this::fireNewGame);

        // add menu elements to display here
        addChild(btnPlay);
        addChild(btnExit);
        addChild(loginForm);
    }

    protected Node createBackground() {
        return FXGL.texture("menu.png", Game.APP_WIDTH, Game.APP_HEIGHT);
    }

    private static class ValhallaButton extends Button {
        private static final double BTN_WIDTH = 100;
        private final String label;
        private Runnable action;

        public ValhallaButton(final String label, final double x, final double y, final Runnable action) {
            this.label = label;
            this.action = action;

            setMinWidth(BTN_WIDTH);
            setText(this.label);
            setOnAction(actionEvent -> this.action.run());
            setTranslateX(x);
            setTranslateY(y);
        }

        public ValhallaButton(final String label, final double x, final double y) {
            this(label, x, y, null);
        }

        public void setAction(final Runnable action) {
            this.action = action;
        }
    }
}
