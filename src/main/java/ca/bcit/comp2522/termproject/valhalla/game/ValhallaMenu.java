package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ValhallaMenu extends FXGLMenu {
    private static final double MENU_X = Game.APP_WIDTH / 2.0 - ValhallaButton.BTN_WIDTH / 2;
    private static final double MENU_Y = Game.APP_HEIGHT / 2.0;

    public ValhallaMenu() {
        super(MenuType.MAIN_MENU);
        addChild(createBackground());
        // create menu elements here; menu elements must extend javafx Node
        ValhallaButton btnPlay = new ValhallaButton("Play", 0, 0);
        ValhallaButton btnExit = new ValhallaButton("Exit", 0, 25);
        VBox menu = createMenu();
        menu.getChildren().addAll(btnPlay, btnExit);

        // create login form
        final double offset = 150;
        LoginForm loginForm = new LoginForm();
        loginForm.setTranslateX(MENU_X + offset);
        loginForm.setTranslateY(MENU_Y);
        loginForm.addField(LoginForm.Fields.USERNAME, "Username: ");
        loginForm.addField(LoginForm.Fields.PASSWORD, "Password: ");
        loginForm.setVisible(false);

        // set the action of buttons
        btnPlay.setAction(() -> {
            loginForm.setVisible(true);
        });
        loginForm.getCancel().setAction(() -> {
            loginForm.setVisible(false);
        });
        btnExit.setAction(this::fireExit);
        loginForm.getSubmit().setAction(() -> {
            if (loginForm.validate()) {
                loadGame();
            }
        });

        // add menu elements to display here
        addChild(menu);
        addChild(loginForm);
    }

    private Node createBackground() {
        return FXGL.texture("background.png", Game.APP_WIDTH, Game.APP_HEIGHT);
    }

    private VBox createMenu() {
        VBox menu = new VBox();
        final double spaceBetweenElements = 20;
        menu.setSpacing(spaceBetweenElements);
        menu.setTranslateX(MENU_X);
        menu.setTranslateY(MENU_Y);
        return menu;
    }

    private void loadGame() {
        // if new player fireNewGame
        // else get load file of user and fireLoad
        fireNewGame();
    }
}
