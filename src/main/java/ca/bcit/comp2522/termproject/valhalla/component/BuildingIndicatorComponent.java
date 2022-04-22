package ca.bcit.comp2522.termproject.valhalla.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import ca.bcit.comp2522.termproject.valhalla.constant.Config;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 * The BuildingIndicatorComponent class.
 *
 * @author kaioh
 * @author FonseLULW
 * @version 1.0
 */
public class BuildingIndicatorComponent extends Component {

    private final RadialGradient okFill;
    private final RadialGradient disabledFill;
    private final Texture texture;
    private final Circle circle;

    /**
     * A BuildingIndicatorComponent that shows the indicator when tower is placed.
     */
    public BuildingIndicatorComponent() {
        final int imageHeight = 51;
        final int imageWidth = 29;
        final double zeroPointfour = 0.4;
        final double zeroPointFive = 0.5;
        final double zeroPointNine = 0.9;
        final double zeroPointThreeEight = 0.38;
        final double zeroPointThreeNine = 0.39;
        Image towerIcon = FXGL.image("tower/tower_image.png", imageWidth, imageHeight);
        texture = new Texture(towerIcon);

        okFill = new RadialGradient(
                0.0, 0.0, zeroPointFive, zeroPointFive, zeroPointFive, true, CycleMethod.NO_CYCLE,
                new Stop(zeroPointNine, new Color(1.0, 0.0, 0.0, 0.0)),
                new Stop(1.0, new Color(0.0, 1.0, zeroPointThreeNine, zeroPointfour)));
        disabledFill = new RadialGradient(
                0.0, 0.0, zeroPointFive, zeroPointFive, zeroPointFive, true, CycleMethod.NO_CYCLE,
                new Stop(zeroPointNine, new Color(1.0, 0.0, 0.0, 0.0)),
                new Stop(1.0, new Color(1.0, 0.0, 0.0, zeroPointThreeEight)));
        circle = new Circle(Config.ARROW_TOWER_DATA.getAttackRadius(), disabledFill);
        circle.setTranslateX(texture.getWidth() / 2.0);
        circle.setTranslateY(texture.getHeight() / 2.0);

    }

    /**
     * An onAdded function.
     */
    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        entity.getViewComponent().addChild(circle);
    }

    /**
     * A function to check if the tower can be built on that space.
     * @param canBuild a boolean value
     */
    public void canBuild(final boolean canBuild) {
        if (canBuild) {
            circle.setFill(okFill);
        } else {
            circle.setFill(disabledFill);
        }
    }

    /**
     * A function to reset the building indicator.
     * @param towerIcon an Image of tower
     * @param radius a double of the range of tower
     */
    public void resetIndicator(final Image towerIcon, final double radius) {
        texture.setImage(towerIcon);
        circle.setTranslateX(texture.getWidth() / 2.0);
        circle.setTranslateY(texture.getHeight() / 2.0);
        circle.setRadius(radius);
    }
}
