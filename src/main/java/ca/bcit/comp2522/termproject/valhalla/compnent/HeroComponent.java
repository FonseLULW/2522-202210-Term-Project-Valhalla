package ca.bcit.comp2522.termproject.valhalla.compnent;

import ca.bcit.comp2522.termproject.valhalla.constant.Config;
import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import ca.bcit.comp2522.termproject.valhalla.data.HeroData;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import javafx.geometry.Point2D;

import java.util.List;

public class HeroComponent extends Component {
    LocalTimer attackTimer;
    HeroData heroData = Config.HERO_DATA;

    private double speed;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    @Override
    public void onAdded() {
        attackTimer = FXGL.newLocalTimer();
        attackTimer.capture();
        speed = heroData.getMoveSpeed();
    }

    public void attackArea() {
        if (attackTimer.elapsed(heroData.getAttackDelay())) {
            List<Entity> enemies = FXGL.getGameWorld().getEntitiesByType(GameType.ENEMY);
            for (Entity enemy : enemies) {
                Point2D ep = enemy.getPosition();
                Point2D tp = entity.getPosition();
                if (ep.distance(tp) <= heroData.getAttackRadius()) {
                    attack(enemy);
                    attackTimer.capture();
                }
            }
        }
    }

    private void attack(Entity attackedEntity) {
        attackedEntity.getComponent(EnemyComponent.class).attacked(heroData.getDamage());
    }
}
