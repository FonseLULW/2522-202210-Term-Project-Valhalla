package ca.bcit.comp2522.termproject.valhalla.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class BulletComponent extends Component {

    private Point2D initPosition;

    private final int radius;
    private final int damage;

    public BulletComponent(final int radius, final int damage) {
        this.radius = radius;
        this.damage = damage;
    }

    @Override
    public void onAdded() {
        initPosition = entity.getPosition();
    }

    @Override
    public void onUpdate(final double tpf) {
        Point2D position = entity.getPosition();
        if (position.distance(initPosition) > radius) {
            if (entity.isActive()) {
                entity.removeFromWorld();
            }
        }
    }

    public int getDamage() {
        return damage;
    }

}

