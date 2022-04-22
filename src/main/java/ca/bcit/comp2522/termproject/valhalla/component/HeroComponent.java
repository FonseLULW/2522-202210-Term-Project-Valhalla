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

/**
 * A HeroComponent class.
 * @author FonseLULW
 * @author kaioh
 * @version 1.0
 */
public class HeroComponent extends Component {
    private static LocalTimer attackTimer;

    private final HeroData heroData;
    private final AnimationChannel animAttack;
    private final AnimationChannel animWalk;
    private final AnimatedTexture texture;
    private double speed;

    /**
     * Constructs a new HeroComponent.
     */
    public HeroComponent() {
        heroData = Config.HERO_DATA;
        Image attack1 = initImage("assets/textures/hero_attack1.png");
        Image attack2 = initImage("assets/textures/hero_attack2.png");
        Image attack3 = initImage("assets/textures/hero_attack3.png");
        Image attack4 = initImage("assets/textures/hero_attack4.png");
        Image idle = initImage("assets/textures/hero_idle.png");
        Image walk1 = initImage("assets/textures/hero_walk1.png");
        Image walk2 = initImage("assets/textures/hero_walk2.png");
        Image walk3 = initImage("assets/textures/hero_walk3.png");

        animAttack = new AnimationChannel(List.of(idle, attack1, attack1, attack2, attack2, attack2, attack3,
                attack3, attack3, attack4, idle), heroData.getAttackDelay());
        final double animWalkDuration = 0.8;
        animWalk = new AnimationChannel(List.of(idle, walk1, walk2, walk3, idle), Duration.seconds(animWalkDuration));

        texture = new AnimatedTexture(animWalk);
        texture.setInterpolator(Interpolator.EASE_BOTH);
    }

    /*
     * Initializes an image to be used in animations.
     */
    private Image initImage(final String filepath) {
        return new Image(filepath, heroData.getWidth(), heroData.getHeight(), true, true);
    }

    /**
     * Returns the speed of this HeroComponent as a double.
     * @return  the speed of this HeroComponent as a double
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Runs on creation of this HeroComponent.
     */
    @Override
    public void onAdded() {
        attackTimer = FXGL.newLocalTimer();
        attackTimer.capture();
        speed = heroData.getMoveSpeed();
        entity.getViewComponent().addChild(texture);
        entity.setScaleOrigin(new Point2D(heroData.getWidth() / 2, heroData.getHeight() / 2));
    }

    /**
     * Moves the animation of this HeroComponent.
     */
    public void move() {
        texture.loopAnimationChannel(animWalk);
    }

    /**
     * Performs an area of effect attack.
     */
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

    /*
     * Deals damage to each enemy caught in the area of effect attack.
     * @param attackedEntity an enemy Entity
     */
    private void attack(final Entity attackedEntity) {
        attackedEntity.getComponent(EnemyComponent.class).attacked(heroData.getDamage());
    }
}
