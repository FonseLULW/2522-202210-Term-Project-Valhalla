package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ValhallaMenu extends FXGLMenu {

    public ValhallaMenu() {
        super(MenuType.MAIN_MENU);

        ValhallaButton btnPlay = new ValhallaButton("Play", this::fireNewGame);
        ValhallaButton btnExit = new ValhallaButton("Exit", this::fireExit);
        ValhallaButton btnSettings = new ValhallaButton("Settings", () -> {
            System.out.println("editing game lol");
        });
        btnPlay.setTranslateX(100);
        btnSettings.setTranslateX(200);
        btnExit.setTranslateX(300);

        addChild(createBackground(Game.APP_WIDTH, Game.APP_HEIGHT));
        addChild(btnPlay);
        addChild(btnExit);
        addChild(btnSettings);
    }

    protected Node createBackground(final double width, final double height) {
        return FXGL.texture("menu.png", Game.APP_WIDTH, Game.APP_HEIGHT);
    }

    private static class ValhallaButton extends Button {
        private static final double BTN_WIDTH = 100;
        private final String name;
        private final Runnable runnable;

        public ValhallaButton(final String name, final Runnable runnable) {
            this.name = name;
            this.runnable = runnable;

            setMinWidth(BTN_WIDTH);
            setText(name);
            setOnAction(actionEvent -> runnable.run());
        }
    }
}
