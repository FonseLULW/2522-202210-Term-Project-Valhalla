package ca.bcit.comp2522.termproject.valhalla.game;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class LoginForm extends AnchorPane {
    public static final double TOP_PADDING = 10.0;
    public static final double BOTTOM_PADDING = 5.0;
    public static final double LEFT_PADDING = 10.0;
    public static final double RIGHT_PADDING = 10.0;
    public static final double ROW_HEIGHT = 20.0;

    public static final double WIDTH = 300 + LEFT_PADDING + RIGHT_PADDING;
    public static final double HEIGHT = 100 + TOP_PADDING + BOTTOM_PADDING;

    public LoginForm() {
        super();
        setStyle("-fx-background-color: black");
        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);

        FormRow username = new FormRow("Username: ", "Heejo", 1);
        FormRow password = new FormRow("Password: ", "LetMeIn", 1);

        setLeftAnchor(username, LEFT_PADDING);
        setTopAnchor(username, TOP_PADDING);
        setLeftAnchor(password, LEFT_PADDING);
        setTopAnchor(password, TOP_PADDING * 2 + ROW_HEIGHT + BOTTOM_PADDING);
        getChildren().addAll(username, password);

    }

    public static class FormRow extends HBox {
        private static final int LABEL_RELATIVE_WIDTH = 4;
        private static final int FIELD_RELATIVE_WIDTH = 2;

        private final Label label;
        private TextField field;

        FormRow(final String label, final String placeholder, final double space) {
            super(0);
            double width = WIDTH - LEFT_PADDING - RIGHT_PADDING;
            setPrefWidth(width);
            setStyle("-fx-background-color: #af4c4c");
            this.label = new Label(label);
            this.field = new TextField(placeholder);
            this.field.setPrefHeight(ROW_HEIGHT);
            this.label.setMinHeight(ROW_HEIGHT);
            this.label.setLabelFor(this.field);
            this.field.setPrefWidth(width / FIELD_RELATIVE_WIDTH);
            this.label.setPrefWidth(width / LABEL_RELATIVE_WIDTH);
            setSpacing(width - this.label.getPrefWidth() - this.field.getPrefWidth());
            getChildren().addAll(this.label, this.field);
        }
    }
}
