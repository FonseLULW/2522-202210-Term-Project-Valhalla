package ca.bcit.comp2522.termproject.valhalla.compnent;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import ca.bcit.comp2522.termproject.valhalla.constant.TowerType;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class PlacedButtonComponent extends Component {
    private static final AnimationChannel AC_BORDER = new AnimationChannel(FXGL.image("selected_border.png"), 5, 80, 80, Duration.seconds(1), 0, 14);
    private AnimatedTexture texture;
    private final TowerType towerType;
    private boolean selected;

    public PlacedButtonComponent(final TowerType towerType) {
        this.towerType = towerType;
    }

    @Override
    public void onAdded() {
        texture = new AnimatedTexture(AC_BORDER);
        texture.setVisible(false);
        entity.getViewComponent().addChild(texture);

        entity.getViewComponent().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            selected = !selected;
            if (selected) {
                FXGL.set("towerType", towerType);
            } else {
                FXGL.set("towerType", TowerType.NONE);
            }
        });
    }
}
