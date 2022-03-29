package ca.bcit.comp2522.termproject.valhalla.entities;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * An abstract Entity class for all things that exist.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public abstract class Entity implements Slayable, Tangible, Dynamic {

    /**
     * An Entity's display representation as an ImageView.
     */
    protected final ImageView sprite;
    protected double velocityX;
    protected double velocityY;
    protected double speed = 10.0;

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
//
//    }

    public Entity(final String filename, final int x, final int y) {
        sprite = new ImageView(new Image("hero1_idle.jpeg"));
        sprite.setX(x);
        sprite.setY(y);
        sprite.setPreserveRatio(true);
        velocityX = 0;
        velocityY = 0;
    }

    public Node getSprite() {
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

    @Override
    public boolean collision(final Entity entity) {
        final ImageView box = entity.sprite;
        return this.sprite.intersects(box.getX(), box.getY(), box.getFitWidth(), box.getFitHeight());
    }

    @Override
    public void move() {
        sprite.setX(sprite.getX() + velocityX);
        sprite.setY(sprite.getY() + velocityY);
    }

    @Override
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    @Override
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}
