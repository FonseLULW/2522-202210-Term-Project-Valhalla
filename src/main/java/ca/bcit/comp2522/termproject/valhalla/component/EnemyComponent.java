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

public class EnemyComponent extends Component {

    private HealthIntComponent hp;
    private LinkedHashMap<Integer, Pair<Point2D, String>> pointInfos;
    private Point2D nextWaypoint;
    private double speed;
    int index;
    private final ProgressBar hpBar;
    private final AnimatedTexture texture;
    private final AnimationChannel walkingRight
    = new AnimationChannel(List.of(
                new Image("assets/textures/enemy/slugman_1.png", 48, 48, true, true),
                new Image("assets/textures/enemy/slugman_2.png", 48, 48, true, true),
                new Image("assets/textures/enemy/slugman_3.png", 48, 48, true, true),
                new Image("assets/textures/enemy/slugman_2.png", 48, 48, true, true)
        ), Duration.seconds(0.3));
//            = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 48, 48), 5, 48, 48, Duration.seconds(1), 0, 14);
//    private final AnimationChannel walkingLeft = new AnimationChannel(FXGL.image("enemy/slugman_2.png", 48, 48), 5, 48, 48, Duration.seconds(1), 0, 14);
//    private final AnimationChannel walkingUp = new AnimationChannel(FXGL.image("enemy/slugman_3.png", 48, 48), 5, 48, 48, Duration.seconds(1), 0, 14);
//    private final AnimationChannel walkingDown = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 48, 48), 5, 48, 48, Duration.seconds(1), 0, 14);

    private boolean dead;

    public EnemyComponent(final ProgressBar hpBar) {
        this.hpBar = hpBar;
        texture = new AnimatedTexture(walkingRight);
//        texture.setInterpolator(Interpolator.EASE_BOTH);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(final boolean dead) {
        this.dead = dead;
    }

    public void attacked(final int damage) {
        hp.damage(damage);
        if (hp.isZero()) {
            dead = true;
            FXGL.play("slugmandefeat.wav");
            entity.getViewComponent().removeChild(hpBar);
            entity.removeFromWorld();
//            texture.setOnCycleFinished(() -> entity.removeFromWorld());
        }

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        entity.setScaleOrigin(new Point2D(48.0 / 2, 48.0 / 2));

        Game app = (Game) (FXGL.getApp());
        pointInfos = app.getPointInfos();
        nextWaypoint = pointInfos.get(index).getKey();

        texture.setInterpolator(Interpolator.EASE_IN);
//        walkingAnimation();
    }

    private void walkingAnimation() {
//        String dir = pointInfos.get(index).getValue();
//        if ("right".equals(dir)) {
            texture.loop();
//        } else if ("left".equals(dir)) {
//            texture.loopAnimationChannel(walkingLeft);
//        } else if ("up".equals(dir)) {
//            texture.loopAnimationChannel(walkingUp);
//        } else if ("down".equals(dir)) {
//            texture.loopAnimationChannel(walkingDown);
//        }
    }

    @Override
    public void onUpdate(final double tpf) {
        if (index >= pointInfos.size() || dead) {
            return;
        }
        String dir = pointInfos.get(index).getValue();
        if ("right".equals(dir) || "up".equals(dir)) {
            texture.setScaleX(-1);
        } else {
            texture.setScaleX(1);
        }

        speed = tpf * 30 * 2;

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
                    vars.setValue("baseHealth", vars.getInt("baseHealth") - rng.nextInt(15, 50));
                }
                entity.removeFromWorld();
            }
        }

    }
}
