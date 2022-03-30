package ca.bcit.comp2522.termproject.valhalla.entities;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An abstract Entity class for all things that exist.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public abstract class Entity extends ImageView implements Slayable, Tangible, Dynamic {

    /**
     * An Entity's display representation as an ImageView.
     */
//    protected final Image sprite;
    protected double velocityX;
    protected double velocityY;
    protected double speed = 10.0;
    protected String name;
    protected int maxHP;
    protected int currentHP;
    protected int level;
    protected int damage;
    protected int defence;
    protected double range;

    public Entity(final String filename, final int x, final int y) {
        super(filename);
        setX(x);
        setY(y);
        setPreserveRatio(true);
        velocityX = 0;
        velocityY = 0;
    }

//    public Node getSprite() {
//        return sprite;
//    }

    public void setWidth(final int width) {
        setFitHeight(0);
        setFitWidth(width);
    }

    public void setHeight(final int height) {
        setFitWidth(0);
        setFitHeight(height);
    }

    @Override
    public boolean collision(final Entity entity) {
        return true;
//        final ImageView box = entity.sprite;
//        return this.sprite.intersects(box.getX(), box.getY(), box.getFitWidth(), box.getFitHeight());
    }

    @Override
    public void move() {
        setX(getX() + velocityX);
        setY(getY() + velocityY);
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
