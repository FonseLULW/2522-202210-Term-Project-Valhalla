package ca.bcit.comp2522.termproject.valhalla.compnent;

import ca.bcit.comp2522.termproject.valhalla.game.Game;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.LinkedHashMap;

public class EnemyComponent extends Component {

    private HealthIntComponent hp;
    private LinkedHashMap<Integer, Pair<Point2D, String>> pointInfos;
    private Point2D nextWaypoint;
    private double speed;
    int index;
    private final ProgressBar hpBar;
    private final AnimatedTexture texture;
    private final AnimationChannel walkingRight = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 5 * 48, 48 * 3), 5, 48, 48, Duration.seconds(.5), 0, 14);
    private final AnimationChannel walkingLeft = new AnimationChannel(FXGL.image("enemy/slugman_2.png", 5 * 48, 48 * 3), 5, 48, 48, Duration.seconds(.5), 0, 14);
    private final AnimationChannel walkingUp = new AnimationChannel(FXGL.image("enemy/slugman_3.png", 5 * 48, 48 * 3), 5, 48, 48, Duration.seconds(.5), 0, 14);
    private final AnimationChannel walkingDown = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 5 * 48, 48 * 3), 5, 48, 48, Duration.seconds(.5), 0, 14);

    private boolean dead;

    public EnemyComponent(final ProgressBar hpBar) {
        this.hpBar = hpBar;
        texture = new AnimatedTexture(walkingRight);
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
            entity.getViewComponent().removeChild(hpBar);
            texture.setOnCycleFinished(() -> entity.removeFromWorld());
        }

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        Game app = (Game) (FXGL.getApp());
        pointInfos = app.getPointInfos();
        nextWaypoint = pointInfos.get(index).getKey();
        walkingAnimation();
    }

    private void walkingAnimation() {
        String dir = pointInfos.get(index).getValue();
        if ("right".equals(dir)) {
            texture.loopAnimationChannel(walkingRight);
        } else if ("left".equals(dir)) {
            texture.loopAnimationChannel(walkingLeft);
        } else if ("up".equals(dir)) {
            texture.loopAnimationChannel(walkingUp);
        } else if ("down".equals(dir)) {
            texture.loopAnimationChannel(walkingDown);
        }
    }

    @Override
    public void onUpdate(final double tpf) {
        if (index >= pointInfos.size() || dead) {
            return;
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
            }
        }
    }
}
