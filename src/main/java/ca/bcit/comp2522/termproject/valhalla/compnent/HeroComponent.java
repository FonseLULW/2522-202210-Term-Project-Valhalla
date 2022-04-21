package ca.bcit.comp2522.termproject.valhalla.compnent;

import ca.bcit.comp2522.termproject.valhalla.constant.Config;
import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import ca.bcit.comp2522.termproject.valhalla.data.HeroData;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.time.LocalTimer;
import javafx.animation.Interpolator;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.image;

public class HeroComponent extends Component {
    LocalTimer attackTimer;
    HeroData heroData = Config.HERO_DATA;
    AnimationChannel animAttack;
    AnimatedTexture texture;

    private double speed;

    public HeroComponent() {
        animAttack = new AnimationChannel(List.of(
        new Image("assets/textures/hero_attack1.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack2.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack3.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack3.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack3.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack3.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack3.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack3.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack2.png", heroData.getWidth(), heroData.getHeight(), true, true),
        new Image("assets/textures/hero_attack1.png", heroData.getWidth(), heroData.getHeight(), true, true)
        ), heroData.getAttackDelay());

        texture = new AnimatedTexture(animAttack);
        texture.setInterpolator(Interpolator.EASE_BOTH);
    }

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
        entity.getViewComponent().addChild(texture);
        entity.setScaleOrigin(new Point2D(heroData.getWidth() / 2, heroData.getHeight() / 2));
    }

    public void attackArea() {
        if (attackTimer.elapsed(heroData.getAttackDelay())) {
            texture.play();
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

    @Override
    public void onRemoved() {

    }
}
