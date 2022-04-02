package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.*;
import com.almasb.fxgl.dsl.components.view.TrailParticleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A NodeFactory class made to make new game entities called nodes and add them to the game environment.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public class NodeFactory implements EntityFactory {
    @Spawns("hero")
    public Entity newHero(final SpawnData data) {
        return FXGL.entityBuilder(data)
                .view("hero1_idle.png")
                .with(new ProjectileComponent(new Point2D(1, 0), 150))
                .with(new DraggableComponent())
                .scale(0.1, 0.1)
                .build();
    }
}
