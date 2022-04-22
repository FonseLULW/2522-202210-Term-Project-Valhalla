package ca.bcit.comp2522.termproject.valhalla.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import ca.bcit.comp2522.termproject.valhalla.constant.Config;
import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import ca.bcit.comp2522.termproject.valhalla.data.TowerData;
import javafx.geometry.Point2D;

import java.util.List;

/**
 * A ArrowTowerComponent class to manage the ArrowTower.
 *
 * @author kaioh08
 * @author FonseLULW
 * @version 1.0
 */
public class ArrowTowerComponent extends Component {

    private LocalTimer shootTimer;
    private final TowerData towerData = Config.ARROW_TOWER_DATA;

    /**
     * To add building sound.
     */
    @Override
    public void onAdded() {
        shootTimer = FXGL.newLocalTimer();
        shootTimer.capture();
        FXGL.play("building.wav");
    }

    /**
     * An onUpdate method to override tower data.
     *
     * @param tpf a double
     */
    @Override
    public void onUpdate(final double tpf) {
        if (shootTimer.elapsed(towerData.getAttackDelay())) {
            List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(GameType.ENEMY);
            int bulletNum = 0;
            for (Entity value : entitiesByType) {
                if (bulletNum > 0) {
                    break;
                }
                Point2D ep = value.getPosition();
                Point2D tp = entity.getPosition();
                if (ep.distance(tp) <= towerData.getAttackRadius()) {
                    shoot(value);
                    shootTimer.capture();
                    bulletNum++;
                }
            }

        }
    }

    private void shoot(final Entity enemy) {
        final double twentyFive = 25.0;
        final double five = 5.0;
        Point2D position = getEntity().getPosition();
        Point2D direction = enemy.getPosition().subtract(position);

        Entity bullet = FXGL.spawn(towerData.getName() + "Bullet",
                new SpawnData(entity.getCenter().subtract(twentyFive, five))
                .put("radius", towerData.getAttackRadius())
                .put("damage", towerData.getDamage()));

        bullet.addComponent(new ProjectileComponent(direction, towerData.getBulletSpeed()));

    }
}
