package ca.bcit.comp2522.termproject.valhalla.component;

import ca.bcit.comp2522.termproject.valhalla.game.Game;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.animation.Interpolator;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/**
 * An EnemyComponent class.
 *
 * @author kaioh
 * @author FonseLULW
 * @version 1.0
 */
public class EnemyComponent extends Component {
    private static final String DEFAULT_FILENAME = "slugman";
    private static final int DEFAULT_LOWER_DAMAGE = 15;
    private static final int DEFAULT_UPPER_DAMAGE = 50;

    /**
     * Declaring the value of the index.
     */
    int index;
    private final HealthIntComponent hp;
    private LinkedHashMap<Integer, Pair<Point2D, String>> pointInfos;
    private Point2D nextWaypoint;
    private final ProgressBar hpBar;
    private final AnimatedTexture texture;

    private boolean dead;
    private final int lowerDamage;
    private final int upperDamage;

    /**
     * Spawns the new enemy.
     * @param hp a healthIntComponent
     * @param hpBar a progressbar
     * @param enemyName the name of the type of the enemy
     * @param damageLow the lower bound of this enemy's damage
     * @param damageHigh the upper bound of this enemy's damage
     */
    public EnemyComponent(final HealthIntComponent hp, final ProgressBar hpBar,
                          final String enemyName, final int damageLow, final int damageHigh) {
        this.hp = hp;
        this.hpBar = hpBar;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final double durationTime = 0.3;
        AnimationChannel walkingRight = new AnimationChannel(List.of(
                initImages(enemyName, 1),
                initImages(enemyName, two),
                initImages(enemyName, three),
                initImages(enemyName, four)
        ), Duration.seconds(durationTime));
        texture = new AnimatedTexture(walkingRight);
        this.lowerDamage = damageLow;
        this.upperDamage = damageHigh;
    }

    public EnemyComponent(final HealthIntComponent hp, final ProgressBar hpBar) {
        this(hp, hpBar, DEFAULT_FILENAME, DEFAULT_LOWER_DAMAGE, DEFAULT_UPPER_DAMAGE);
    }

    private Image initImages(final String enemyName, final int indexNum) {
        final int size = 48;
        return new Image("assets/textures/enemy/"
                + enemyName + "_" + indexNum + ".png", size, size, true, true);
    }

    /**
     * Checks if the enemy is dead.
     * @return boolean value
     */
    public boolean isDead() {
        return dead;
    }

//    /**
//     * Sets the entity status to dead.
//     * @param dead a boolean value
//     */
//    public void setDead(final boolean dead) {
//        this.dead = dead;
//    }

    /**
     * Updates the hp bar of the enemy.
     * @param damage an int value of a damage
     */
    public void attacked(final int damage) {
        hp.damage(damage);
        if (hp.isZero()) {
            dead = true;
            FXGL.play("slugmandefeat.wav");
            entity.getViewComponent().removeChild(hpBar);
            entity.removeFromWorld();
        }

    }

    /**
     * Adds enemy to the game.
     */
    @Override
    public void onAdded() {
        final double originSetter = 48.0;
        entity.getViewComponent().addChild(texture);
        entity.setScaleOrigin(new Point2D(originSetter / 2, originSetter / 2));

        Game app = (Game) (FXGL.getApp());
        pointInfos = app.getPointInfos();
        nextWaypoint = pointInfos.get(index).getKey();

        texture.setInterpolator(Interpolator.EASE_IN);
    }

    private void walkingAnimation() {
            texture.loop();
    }

    /**
     * Updates the damage to the enemy.
     * @param tpf a double
     */
    @Override
    public void onUpdate(final double tpf) {
        final int thirty = 30;
        if (index >= pointInfos.size() || dead) {
            return;
        }
        String dir = pointInfos.get(index).getValue();
        if ("right".equals(dir) || "up".equals(dir)) {
            texture.setScaleX(-1);
        } else {
            texture.setScaleX(1);
        }
        double speed = tpf * thirty * 2;
        Point2D velocity = nextWaypoint.subtract(entity.getPosition())
                .normalize()
                .multiply(speed);
        entity.translate(velocity);
        if (nextWaypoint.distance(entity.getPosition()) < speed) {
            entity.setPosition(nextWaypoint);
            walkingAnimation();
            index++;
            if (index < pointInfos.size()) {
                nextWaypoint = pointInfos.get(index).getKey();
            } else {
                Random rng = new Random();
                PropertyMap vars = FXGL.getWorldProperties();
                if (vars.getInt("baseHealth") > 0) {
                    vars.setValue("baseHealth",
                            vars.getInt("baseHealth") - rng.nextInt(lowerDamage, upperDamage));
                }
                entity.removeFromWorld();
            }
        }

    }
}
