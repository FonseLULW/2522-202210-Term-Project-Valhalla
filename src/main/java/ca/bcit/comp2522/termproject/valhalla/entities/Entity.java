package ca.bcit.comp2522.termproject.valhalla.entities;

import javafx.scene.image.ImageView;

/**
 * An abstract Entity class for all things that exist.
 * @author FonseLULW
 * @version 1.0
 */
public abstract class Entity implements Slayable {
    // should be protected
    private final ImageView sprite;

    // could maybe be decomposed?
    private int maxHP;
    private int currentHP;
    private int level;
    private int damage;
    private int defence;
    private double range;
    private double speed;
    private String name;

    public Entity(final String filename, final int x, final int y) {
        sprite = new ImageView("file:assets/img/" + filename);
        sprite.setPreserveRatio(true);
    }

    public Entity(final String filename) {
        this(filename, 0, 0);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setWidth(final int width) {
        sprite.setFitWidth(width);
    }

    public void setHeight(final int height) {
        sprite.setFitHeight(height);
    }

    // maybe a new interface??
    public abstract void attack();
    public abstract void move();
}
