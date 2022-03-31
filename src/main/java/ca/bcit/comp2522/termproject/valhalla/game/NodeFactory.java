package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
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
                .build();
    }
}
