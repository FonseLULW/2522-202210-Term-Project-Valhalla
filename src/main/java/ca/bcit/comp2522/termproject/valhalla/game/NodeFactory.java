package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import ca.bcit.comp2522.termproject.valhalla.entities.SpeedComponent;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.*;
import com.almasb.fxgl.dsl.components.view.TrailParticleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.DoubleComponent;
import com.almasb.fxgl.entity.components.TransformComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsUnitConverter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A NodeFactory class made to make new game entities called nodes and add them to the game environment.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public class NodeFactory implements EntityFactory {
    @Spawns("hero")
    public Entity newHero(final SpawnData data) {
        final double scale = 0.1;
        final double width = 626.0;
        final double height = 899.0;
        final ImageView sprite = new ImageView(new Image("assets/textures/hero1_idle.png",
                width * scale, height * scale, true, true));

        final double speed = 5.0;
        return FXGL.entityBuilder(data)
                .at(0, 0)
                .viewWithBBox(sprite)
                .type(GameType.HERO)
                .with(new DraggableComponent())
                .with(new SpeedComponent(speed))
                .with(new KeepOnScreenComponent())
                .build();
    }
}
