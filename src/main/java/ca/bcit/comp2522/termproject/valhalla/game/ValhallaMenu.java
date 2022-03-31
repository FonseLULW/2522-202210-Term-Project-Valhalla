package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;

public class ValhallaMenu extends FXGLMenu {

    public ValhallaMenu() {
        super(MenuType.MAIN_MENU);
        addChild(createBackground());

        // create menu elements here; menu elements must extend javafx Node
        ValhallaButton btnExit = new ValhallaButton("Exit", 100, 200);
        ValhallaButton btnPlay = new ValhallaButton("Play", 100, 100);

        // create login form
        LoginForm loginForm = new LoginForm();
        loginForm.setTranslateX(50);
        loginForm.setTranslateY(50);
        loginForm.addField(LoginForm.Fields.USERNAME, "Username: ");
        loginForm.addField(LoginForm.Fields.PASSWORD, "Password: ");
        loginForm.setVisible(false);

        // set the action of buttons
        btnPlay.setAction(() -> {
            loginForm.setVisible(true);
        });
        btnExit.setAction(this::fireExit);
        loginForm.getSubmit().setAction(this::fireNewGame);
        loginForm.getCancel().setAction(() -> {
            loginForm.setVisible(false);
        });

        // add menu elements to display here
        addChild(btnPlay);
        addChild(btnExit);
        addChild(loginForm);
    }

    protected Node createBackground() {
        return FXGL.texture("menu.png", Game.APP_WIDTH, Game.APP_HEIGHT);
    }
}
