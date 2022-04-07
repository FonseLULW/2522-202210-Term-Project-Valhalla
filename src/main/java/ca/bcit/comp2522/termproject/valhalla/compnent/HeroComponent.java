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
    int num;

    @Override
    public void onAdded() {
        attackTimer = FXGL.newLocalTimer();
        attackTimer.capture();
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
//
//        Point2D position = getEntity().getPosition();
//        Point2D direction = attackedEntity.getPosition().subtract(position);
//
//        Entity bullet = FXGL.spawn(heroData.getName() + "Bullet", new SpawnData(entity.getCenter().subtract(50 / 2.0, 10 / 2.0))
//                .put("radius", heroData.getAttackRadius())
//                .put("damage", heroData.getDamage()));
//
//        bullet.addComponent(new ProjectileComponent(direction, heroData.getAttackSpeed()));
    }
}
