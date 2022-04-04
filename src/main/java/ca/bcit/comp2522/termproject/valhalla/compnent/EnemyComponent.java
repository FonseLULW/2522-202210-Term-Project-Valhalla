package ca.bcit.comp2522.termproject.valhalla.compnent;

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
    private final AnimationChannel animDie;
    private boolean dead;

    public EnemyComponent(final ProgressBar hpBar) {
        this.hpBar = hpBar;
        AnimationChannel walkingRight = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 5 * 48, 48 * 3), 5, 48, 48, Duration.seconds(.5), 0, 14);
        AnimationChannel walkingLeft = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 5 * 48, 48 * 3), 5, 48, 48, Duration.seconds(.5), 0, 14);
        AnimationChannel walkingUp = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 5 * 48, 48 * 3), 5, 48, 48, Duration.seconds(.5), 0, 14);
        AnimationChannel walkingDown = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 5 * 48, 48 * 3), 5, 48, 48, Duration.seconds(.5), 0, 14);
        animDie = new AnimationChannel(FXGL.image("enemy/slugman_1.png", 5 * 48, 2 * 48), 5, 48, 48, Duration.seconds(.25), 0, 8);

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
            texture.loopAnimationChannel(animDie);
            texture.setOnCycleFinished(() -> entity.removeFromWorld());
        }

    }
}
