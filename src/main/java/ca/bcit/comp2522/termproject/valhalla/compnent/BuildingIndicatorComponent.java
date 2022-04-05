package ca.bcit.comp2522.termproject.valhalla.compnent;

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

public class BuildingIndicatorComponent extends Component {

    private final RadialGradient okFill;
    private final RadialGradient disabledFill;
    private final Texture texture;
    private final Circle circle;

    public BuildingIndicatorComponent() {
        Image towerIcon = FXGL.image("tower/tower_image.png", 29, 51);
        texture = new Texture(towerIcon);

        okFill = new RadialGradient(
                0.0, 0.0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0.9, new Color(1.0, 0.0, 0.0, 0.0)),
                new Stop(1.0, new Color(0.0, 1.0, 0.39, 0.4)));
        disabledFill = new RadialGradient(
                0.0, 0.0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0.9, new Color(1.0, 0.0, 0.0, 0.0)),
                new Stop(1.0, new Color(1.0, 0.0, 0.0, 0.38)));
        circle = new Circle(Config.ARROW_TOWER_DATA.getAttackRadius(), disabledFill);
        circle.setTranslateX(texture.getWidth() / 2.0);
        circle.setTranslateY(texture.getHeight() / 2.0);

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        entity.getViewComponent().addChild(circle);
    }

    public void canBuild(final boolean canBuild) {
        circle.setFill(canBuild ? okFill : disabledFill);
    }

    public void resetIndicator(final Image towerIcon, final double radius) {
        texture.setImage(towerIcon);
        circle.setTranslateX(texture.getWidth() / 2.0);
        circle.setTranslateY(texture.getHeight() / 2.0);
        circle.setRadius(radius);
    }
}
