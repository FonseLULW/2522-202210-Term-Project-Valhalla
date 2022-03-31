package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ValhallaMenu extends FXGLMenu {

    public ValhallaMenu() {
        super(MenuType.MAIN_MENU);

        ValhallaButton btnPlay = new ValhallaButton("Play", () -> {
            System.out.println("playing game lol");
        });

        addChild(createBackground(Game.APP_WIDTH, Game.APP_HEIGHT));
        addChild(btnPlay);
    }

    protected Node createBackground(double w, double h) {
        return FXGL.texture("menu.png", Game.APP_WIDTH, Game.APP_HEIGHT);
    }

    private class ValhallaButton extends Button {
        private String name;
        private Runnable runnable;

        public ValhallaButton(final String name, final Runnable runnable) {
            this.name = name;
            this.runnable = runnable;

            setMinWidth(100);
            setText(name);
        }
    }
}
