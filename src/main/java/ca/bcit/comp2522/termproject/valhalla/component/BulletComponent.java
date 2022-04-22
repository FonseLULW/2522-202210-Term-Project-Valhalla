package ca.bcit.comp2522.termproject.valhalla.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

/**
 * A BulletComponent class.
 *
 * @author kaioh
 * @author FonseLULW
 * @version 1.0
 */
public class BulletComponent extends Component {

    private Point2D initPosition;

    private final int radius;
    private final int damage;

    /**
     * A bullet component.
     * @param radius an int value of the radius
     * @param damage an int value of damage
     */
    public BulletComponent(final int radius, final int damage) {
        this.radius = radius;
        this.damage = damage;
    }

    /**
     * An onAdded function to get the position of the bullet.
     */
    @Override
    public void onAdded() {
        initPosition = entity.getPosition();
    }

    /**
     * An override function that will update bullet.
     * @param tpf a double
     */
    @Override
    public void onUpdate(final double tpf) {
        Point2D position = entity.getPosition();
        if (position.distance(initPosition) > radius) {
            if (entity.isActive()) {
                entity.removeFromWorld();
            }
        }
    }

    /**
     * A getDamage function that will get damage.
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

}

