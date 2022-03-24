package ca.bcit.comp2522.termproject.valhalla.entities;

import javafx.scene.image.ImageView;

/**
 * An abstract Entity class for all things that exist.
 * @author FonseLULW
 * @version 1.0
 */
public abstract class Entity implements Slayable {
    /**
     * An Entity's display representation as an ImageView.
     */
    protected final ImageView sprite;

    // could maybe be decomposed?
    protected int maxHP;
    protected int currentHP;
    protected int level;
    protected int damage;
    protected int defence;
    protected double range;
    protected double speed;
    protected String name;

    public Entity(final String filename, final int x, final int y) {
        sprite = new ImageView("file:assets/img/" + filename);
        sprite.setX(x);
        sprite.setY(y);
        sprite.setPreserveRatio(true);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setWidth(final int width) {
        sprite.setFitHeight(0);
        sprite.setFitWidth(width);
    }

    public void setHeight(final int height) {
        sprite.setFitWidth(0);
        sprite.setFitHeight(height);
    }

    public void setX(final int x) {
        sprite.setX(x);
    }

    public void setY(final int y) {
        sprite.setY(y);
    }

    // maybe a new interface??
    public abstract void attack();
}
