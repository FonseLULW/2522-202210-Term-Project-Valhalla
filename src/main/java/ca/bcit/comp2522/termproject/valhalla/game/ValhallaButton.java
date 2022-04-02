package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.sql.Array;

public class ValhallaButton extends Button {
    public static final double BTN_WIDTH = 100;
    public static final double BTN_HEIGHT = 20;
    private final String label;
    private Runnable action;

    public ValhallaButton(final String label, final double x, final double y, final Runnable action) {
        super(label);
        this.label = label;
        this.action = action;

        setMinWidth(BTN_WIDTH);
        setMinHeight(BTN_HEIGHT);
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
