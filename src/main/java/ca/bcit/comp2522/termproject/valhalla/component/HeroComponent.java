package ca.bcit.comp2522.termproject.valhalla.component;

import ca.bcit.comp2522.termproject.valhalla.constant.Config;
import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import ca.bcit.comp2522.termproject.valhalla.data.HeroData;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
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
    AnimationChannel animWalk;
    AnimationChannel animIdle;
    AnimatedTexture texture;

    private double speed;

    public HeroComponent() {
        Image attack1 = new Image("assets/textures/hero_attack1.png", heroData.getWidth(), heroData.getHeight(), true, true);
        Image attack2 = new Image("assets/textures/hero_attack2.png", heroData.getWidth(), heroData.getHeight(), true, true);
        Image attack3 = new Image("assets/textures/hero_attack3.png", heroData.getWidth(), heroData.getHeight(), true, true);
        Image attack4 = new Image("assets/textures/hero_attack4.png", heroData.getWidth(), heroData.getHeight(), true, true);
        Image idle = new Image("assets/textures/hero_idle.png", heroData.getWidth(), heroData.getHeight(), true, true);
        Image walk1 = new Image("assets/textures/hero_walk1.png", heroData.getWidth(), heroData.getHeight(), true, true);
        Image walk2 = new Image("assets/textures/hero_walk2.png", heroData.getWidth(), heroData.getHeight(), true, true);
        Image walk3 = new Image("assets/textures/hero_walk3.png", heroData.getWidth(), heroData.getHeight(), true, true);

        animAttack = new AnimationChannel(List.of(idle, attack1, attack1, attack2, attack2, attack2, attack3,
                attack3, attack3, attack4, idle), heroData.getAttackDelay());
        animWalk = new AnimationChannel(List.of(idle, walk1, walk2, walk3, idle), Duration.seconds(0.8));

        texture = new AnimatedTexture(animWalk);
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

    public void move() {
        texture.loopAnimationChannel(animWalk);
    }

    public void attackArea() {
        if (attackTimer.elapsed(heroData.getAttackDelay())) {
            texture.playAnimationChannel(animAttack);
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
