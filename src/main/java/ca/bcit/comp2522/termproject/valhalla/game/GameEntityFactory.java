package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.compnent.*;
import ca.bcit.comp2522.termproject.valhalla.entities.SpeedComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.KeepOnScreenComponent;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

/**
 * GameEntityFactory.
 */
public class GameEntityFactory implements EntityFactory {

    @Spawns("arrowTower")
    public Entity newArrowTower(final SpawnData data) {
        return entityBuilder(data)
                .type(GameType.TOWER)
                .viewWithBBox(FXGL.texture("tower/tower_image.png"))
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

    @Spawns("space")
    public Entity newSpace(final SpawnData data) {
        Game app = (Game) (FXGL.getApp());
        app.getSpaceInfos().add(new Rectangle(data.getX(), data.getY(), data.<Integer>get("width"),
                data.<Integer>get("height")));
        return entityBuilder(data)
                .type(GameType.SPACE)
                .build();
    }

    @Spawns("empty")
    public Entity newEmpty(final SpawnData data) {
        return entityBuilder(data)
                .type(GameType.EMPTY)
                .collidable()
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(final SpawnData data) {
        int maxHp = 500;
        HealthIntComponent hp = new HealthIntComponent(maxHp);
        ProgressBar hpBar = new ProgressBar(false);
        hpBar.setFill(Color.LIGHTGREEN);
        hpBar.setWidth(48);
        hpBar.setHeight(7);
        hpBar.setTranslateY(-5);
        hpBar.setMaxValue(maxHp);
        hpBar.setCurrentValue(maxHp);
        hpBar.currentValueProperty().bind(hp.valueProperty());

        hp.valueProperty().addListener((ob, ov, nv) -> {
            int value = nv.intValue();
            if (value > maxHp * 0.65) {
                hpBar.setFill(Color.LIGHTGREEN);
            } else if (value > maxHp * 0.25) {
                hpBar.setFill(Color.GOLD);
            } else {
                hpBar.setFill(Color.RED);
            }

        });
        return entityBuilder(data)
                .type(GameType.ENEMY)
                .with(hp)
                .view(hpBar)
                .with(new EnemyComponent(hpBar))
                .with(new CollidableComponent(true))
                .bbox(BoundingShape.box(48, 48))
                .build();
    }

    @Spawns("arrowTowerBullet")
    public Entity newArrowBullet(final SpawnData data) {
        return createBullet(data);
    }

    private Entity createBullet(final SpawnData data) {
        return entityBuilder(data)
                .type(GameType.BULLET)
                .viewWithBBox(FXGL.texture("tower/arrow2.png", 50, 10))
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
