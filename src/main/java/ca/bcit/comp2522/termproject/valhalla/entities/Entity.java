package ca.bcit.comp2522.termproject.valhalla.entities;

import javafx.scene.image.ImageView;

/**
 * An abstract Entity class for all things that exist.
 * @author FonseLULW
 * @version 1.0
 */
public abstract class Entity implements Slayable, Dynamic {
    /**
     * An Entity's display representation as an ImageView.
     */
    protected final ImageView sprite;

//    /**
//     * An Entity's name.
//     */
//    protected String name;
//
//    /**
//     * An Entity's Stats.
//     */
//    class Stats {
//        private int maxHP;
//        private int currentHP;
//        private int level;
//        private int damage;
//        private int defence;
//        private double range;
//        private double speed;
//    }

    public Entity(final String filename, final int x, final int y) {
        sprite = new ImageView("file:assets/img/" + filename);
        sprite.setX(x);
        sprite.setY(y);
        sprite.setPreserveRatio(true);
    }

    public void setWidth(final int width) {
        sprite.setFitHeight(0);
        sprite.setFitWidth(width);
    }

    public void setHeight(final int height) {
        sprite.setFitWidth(0);
        sprite.setFitHeight(height);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean collision(final Entity entity) {
        final ImageView box = entity.sprite;
        return this.sprite.intersects(box.getX(), box.getY(), box.getFitWidth(), box.getFitHeight());
    }

// maybe a new interface??
//    public abstract void attack();
}
