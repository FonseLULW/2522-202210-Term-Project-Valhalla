package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.compnent.*;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.ProgressBar;
import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class GameEntityFactory implements EntityFactory {

    @Spawns("arrowTower")
    public Entity newArrowTower(final SpawnData data) {
        return entityBuilder(data)
                .type(GameType.TOWER)
                .viewWithBBox(FXGL.texture("arrow.png"))
                .with(new ArrowTowerComponent())
                .build();
    }

    @Spawns("point")
    public Entity newPoint(final SpawnData data) {
        int index = data.get("index");
        String dir = data.get("dir");
        Game app = (Game) (FXGL.getApp());
        app.getPointInfos().put(index, new Pair<>(new Point2D(data.getX(), data.getY()), dir));
        return entityBuilder(data)
                .type(GameType.POINT)
                .build();
    }

    @Spawns("arrowTowerBullet")
    public Entity newArrowBullet(final SpawnData data) {
        return createBullet(data);
    }

    private Entity createBullet(final SpawnData data) {
        return entityBuilder(data)
                .type(GameType.BULLET)
                .viewWithBBox(FXGL.texture("res", 50, 10))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new BulletComponent(data.get("radius"), data.get("damage")))
                .build();
    }

    @Spawns("buildIndicator")
    public Entity newBuildIndicator(final SpawnData data) {
        return entityBuilder(data)
                .with(new BuildingIndicatorComponent())
                .zIndex(100)
                .build();
    }

    @Spawns("placedButton")
    public Entity newPlacedButton(final SpawnData data) {
        Texture texture = FXGL.texture((String) data.get("imgName"), data.get("width"), data.get("height"));
        texture.setTranslateX((80 - texture.getWidth()) / 2.0);
        texture.setTranslateY((80 - texture.getHeight()) / 2.0);

        Texture bgTexture = FXGL.texture("towerBorder.png", 105, 105);
        bgTexture.setTranslateX((80 - bgTexture.getWidth()) / 2);
        bgTexture.setTranslateY((80 - bgTexture.getHeight()) / 2);
        return entityBuilder(data)
                .view(new Rectangle(80, 80, Color.web("#D5D5D511")))
                .view(bgTexture)
                .view(texture)
                .with(new PlacedButtonComponent(data.get("towerType")))
                .build();
    }

    @Spawns("placeBox")
    public Entity newPlaceBox(final SpawnData data) {
        return entityBuilder(data)
                .at(1000, 0)
                .build();
    }
}
