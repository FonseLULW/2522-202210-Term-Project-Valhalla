package ca.bcit.comp2522.termproject.valhalla.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class LoginForm extends AnchorPane {
    public enum Fields { USERNAME, PASSWORD }
    public static final double ROW_HEIGHT = 20.0;
    public static final double ROW_WIDTH = 300;
    public static final Insets PADDING = new Insets(10.0, 10.0, 5.0, 10.0);

    private static int fieldAmount = 0;
    private static double rowsHeight = ROW_HEIGHT * fieldAmount;
    private static double width = ROW_WIDTH + PADDING.getLeft() + PADDING.getRight();
    private static double height = rowsHeight + PADDING.getTop() + PADDING.getBottom() + ValhallaButton.BTN_HEIGHT + 120;

    private final VBox rows;
    private final ValhallaButton submit;
    private final ValhallaButton cancel;

    public LoginForm() {
        super();
        setPadding(PADDING);
        setStyle("-fx-background-color: black");
        setPrefWidth(width);
        setPrefHeight(height);

        // make the container for fields
        rows = new VBox();
        rows.setPrefWidth(ROW_WIDTH);
        rows.setMinWidth(ROW_WIDTH);
        rows.setPrefHeight(ROW_HEIGHT);
        rows.setMinHeight(rowsHeight);
        setLeftAnchor(rows, PADDING.getLeft());
        setTopAnchor(rows, PADDING.getTop());
        setRightAnchor(rows, PADDING.getRight());

        // make buttons
        submit = initSubmit();
        cancel = initCancel();
        getChildren().addAll(rows, submit, cancel);
    }

    private ValhallaButton initSubmit() {
        final ValhallaButton btnSubmit;
        btnSubmit = new ValhallaButton("Submit", 0, 0);
        setBottomAnchor(btnSubmit, LoginForm.PADDING.getBottom());
        setLeftAnchor(btnSubmit, LoginForm.PADDING.getLeft());
        return btnSubmit;
    }

    private ValhallaButton initCancel() {
        final ValhallaButton btnCancel;
        btnCancel = new ValhallaButton("Cancel", 0, 0);
        setBottomAnchor(btnCancel, LoginForm.PADDING.getBottom());
        setRightAnchor(btnCancel, LoginForm.PADDING.getRight());
        return btnCancel;
    }

    public ValhallaButton getSubmit() {
        return submit;
    }

    public ValhallaButton getCancel() {
        return cancel;
    }

    public void addField(final Fields field, final String label) {
        FieldFactory factory = new FieldFactory();
        HBox row = factory.getField(field, label);
        row.setPrefHeight(ROW_HEIGHT);
        row.setMinHeight(ROW_HEIGHT);
        row.setPrefWidth(ROW_WIDTH);
        row.setMinWidth(ROW_WIDTH);
        row.setAlignment(Pos.CENTER);
        row.setStyle("-fx-background-color: #af4c4c");

        rows.getChildren().add(row);
        rows.setSpacing(1.0);
        setTopAnchor(rows, PADDING.getTop());

        fieldAmount++;
    }

    public boolean validate() {
        try {
            DatabaseManager db = new DatabaseManager("comp2522", "root", "eggonomics");
//            db.search("USERS", rows.getChildren().get())
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Error: SQL jar file cannot be found on Project Structure");
        } catch (SQLException e) {
            System.out.println("Error: Wrong username or password passed in constructor");
        }
        return true;
    }

    private static class FieldFactory {
        private static final double FIELD_RELATIVE_WIDTH = 0.5;
        private static final double LABEL_RELATIVE_WIDTH = 1.0 - FIELD_RELATIVE_WIDTH;

        FieldFactory() { }

        public HBox getField(final Fields field, final String label) {
            TextField input;
            switch (field) {
                case USERNAME -> {
                    input = new TextField("Username");
                }
                case PASSWORD -> {
                    input = new PasswordField();
                }
                default -> {
                    throw new IllegalArgumentException("Field is not supported");
                }
            }
            // maybe extract func
            input.setPrefHeight(ROW_HEIGHT);
            input.setMinHeight(ROW_HEIGHT);
            input.setPrefWidth(ROW_WIDTH * FIELD_RELATIVE_WIDTH);
            input.setMinWidth(ROW_WIDTH * FIELD_RELATIVE_WIDTH);

            Label tag = new Label(label);
            tag.setLabelFor(input);
            tag.setPrefHeight(ROW_HEIGHT);
            tag.setMinHeight(ROW_HEIGHT);
            tag.setPrefWidth(ROW_WIDTH * LABEL_RELATIVE_WIDTH);
            tag.setMinWidth(ROW_WIDTH * LABEL_RELATIVE_WIDTH);

            return new HBox(tag, input);
        }
    }
}
