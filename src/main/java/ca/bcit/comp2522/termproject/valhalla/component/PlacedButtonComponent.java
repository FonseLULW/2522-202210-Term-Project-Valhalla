package ca.bcit.comp2522.termproject.valhalla.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import ca.bcit.comp2522.termproject.valhalla.constant.TowerType;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * A placedButtonComponent class that extends from Component.
 *
 * @author kaioh
 * @version 1.0
 */
public class PlacedButtonComponent extends Component {
    //TODO DELETE THIS
    private static final AnimationChannel BORDER_ANIMATION = new AnimationChannel(FXGL.image("selected_border.png"),
            5, 80, 80, Duration.seconds(1), 0, 14);
    private AnimatedTexture texture;
    private final TowerType towerType;
    private boolean isSelected;

    public PlacedButtonComponent(final TowerType towerType) {
        this.towerType = towerType;
    }

    @Override
    public void onAdded() {
        texture = new AnimatedTexture(BORDER_ANIMATION);
        texture.setVisible(false);
        entity.getViewComponent().addChild(texture);

        entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            isSelected = !isSelected;
            if (isSelected) {
                FXGL.set("towerType", towerType);
            } else {
                FXGL.set("towerType", TowerType.NONE);
            }
        });
    }

    public void setSelected(final boolean selected) {
        this.isSelected = selected;
        if (selected) {
            texture.setVisible(true);
            texture.loop();
        } else {
            texture.stop();
            texture.setVisible(false);
        }
    }
}
