package ca.bcit.comp2522.termproject.valhalla.constant;

import ca.bcit.comp2522.termproject.valhalla.data.HeroData;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import ca.bcit.comp2522.termproject.valhalla.data.TowerData;
import javafx.util.Duration;

/**
 * An abstract class of config of the game.
 *
 * @author kaioh
 * @author FonseLULW
 * @version 1.0
 */
public abstract class Config {
    /**
     * The lower bound of the tower's damage.
     */
    public static final int LOWER_BOUND = 10;
    /**
     * The upper bound of the tower's damage.
     */
    public static final int UPPER_BOUND = 15;

    /**
     * The tower's data.
     */
    public static final TowerData ARROW_TOWER_DATA = new TowerData("arrowTower", 43, 68, 1,
            580, 467, Duration.seconds(0.7), FXGL.image("tower/tower_image.png")) {
        @Override
        public int getDamage() {
            return FXGLMath.random(LOWER_BOUND, UPPER_BOUND);
        }
    };

    /**
     * The Hero's data.
     */
    public static final HeroData HERO_DATA = new HeroData("arrowTower", 60.0, 90.0, 120,
            50, Duration.seconds(0.2), 2.0);
}
