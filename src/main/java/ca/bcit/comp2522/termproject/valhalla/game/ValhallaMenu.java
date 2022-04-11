package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.audio.Audio;
import com.almasb.fxgl.audio.AudioType;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

/**
 * A ValhallaMenu class representing the game's main menu.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public class ValhallaMenu extends FXGLMenu {
    private static final double MENU_X = Game.APP_WIDTH / 2.0 - ValhallaButton.BTN_WIDTH / 2;
    private static final double MENU_Y = Game.APP_HEIGHT / 2.0;

    /**
     * Constructs a new ValhallaMenu object.
     */
    public ValhallaMenu() {
        super(MenuType.MAIN_MENU);
        addChild(createBackground());

        // create menu elements here; menu elements must extend javafx Node
        final double spaceBetweenBtns = 25;
        ValhallaButton btnPlay = new ValhallaButton("Play", 0, 0);
        ValhallaButton btnExit = new ValhallaButton("Exit", 0, spaceBetweenBtns);
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
        final Node background = FXGL.texture("background.png", Game.APP_WIDTH, Game.APP_HEIGHT);
        return background;
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
        FXGL.getAudioPlayer().stopAllMusic();
        fireNewGame();
    }

    @Override
    public void onCreate() {
        final double bgmVolume = 0.20;
        FXGL.loopBGM("introX.wav");
        FXGL.getSettings().setGlobalMusicVolume(bgmVolume);
    }
}
