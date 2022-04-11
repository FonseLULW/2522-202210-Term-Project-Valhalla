package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;

/**
 * A ValhallaButton class representing buttons custom to Valhalla.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public class ValhallaButton extends Button {
    /**
     * The button width.
     */
    public static final double BTN_WIDTH = 100;
    /**
     * The button height.
     */
    public static final double BTN_HEIGHT = 20;
    private Runnable action;

    /**
     * Constructs a new ValhallaButton object.
     * @param label a String displayed on this ValhallaButton
     * @param x a double representing the x-coordinates of this ValhallaButton
     * @param y a double representing the y-coordinates of this ValhallaButton
     * @param action a Runnable that will be executed when this ValhallaButton is pressed
     */
    public ValhallaButton(final String label, final double x, final double y, final Runnable action) {
        super(label);
        this.action = action;

        setMinWidth(BTN_WIDTH);
        setMinHeight(BTN_HEIGHT);
        setOnAction(actionEvent -> {
            FXGL.play("menubtn.wav");
            this.action.run();
        });
        setTranslateX(x);
        setTranslateY(y);
    }

    /**
     * Constructs a new ValhallaButton object.
     * @param label a String displayed on this ValhallaButton
     * @param x a double representing the x-coordinates of this ValhallaButton
     * @param y a double representing the y-coordinates of this ValhallaButton
     */
    public ValhallaButton(final String label, final double x, final double y) {
        this(label, x, y, null);
    }

    public ValhallaButton(final String label) {
        this(label, 0, 0);
    }

    /**
     * Sets the action of this ValhallaButton.
     * @param action a Runnable that will be executed when this ValhallaButton is pressed
     */
    public void setAction(final Runnable action) {
        this.action = action;
    }
}
