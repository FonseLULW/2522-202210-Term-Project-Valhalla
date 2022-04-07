package ca.bcit.comp2522.termproject.valhalla.constant;

import ca.bcit.comp2522.termproject.valhalla.data.HeroData;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import ca.bcit.comp2522.termproject.valhalla.data.TowerData;
import javafx.util.Duration;

public interface Config {
    int LOWER_BOUND = 10;
    int UPPER_BOUND = 15;

    TowerData ARROW_TOWER_DATA = new TowerData("arrowTower", 43, 68, 1, 580, 467, Duration.seconds(0.7), FXGL.image("tower/tower_image.png")) {
        @Override
        public int getDamage() {
            return FXGLMath.random(LOWER_BOUND, UPPER_BOUND);
        }
    };

    HeroData HERO_DATA = new HeroData("arrowTower", 63, 90, 50, 150, 467, Duration.seconds(0.8), FXGL.image("hero1_idle.png"));
}
