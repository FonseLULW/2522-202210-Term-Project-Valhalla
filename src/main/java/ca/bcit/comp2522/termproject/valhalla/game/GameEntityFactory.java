package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.component.ArrowTowerComponent;
import ca.bcit.comp2522.termproject.valhalla.component.BuildingIndicatorComponent;
import ca.bcit.comp2522.termproject.valhalla.component.BulletComponent;
import ca.bcit.comp2522.termproject.valhalla.component.EnemyComponent;
import ca.bcit.comp2522.termproject.valhalla.component.HeroComponent;
import ca.bcit.comp2522.termproject.valhalla.component.PlacedButtonComponent;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.KeepOnScreenComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.ProgressBar;
import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getCutsceneService;
import static com.almasb.fxgl.dsl.FXGLForKtKt.runOnce;

/**
 * The GameEntityFactory class.
 * @author FonseLULW
 * @author kaioh
 * @version 1.0
 */
public class GameEntityFactory implements EntityFactory {

    /**
     * Spawns a new arrow tower.
     * @param data SpawnData object for new arrow tower
     * @return a new arrow tower Entity
     */
    @Spawns("arrowTower")
    public Entity newArrowTower(final SpawnData data) {
        return entityBuilder(data)
                .type(GameType.TOWER)
                .viewWithBBox(FXGL.texture("tower/tower_image.png"))
                .with(new ArrowTowerComponent())
                .build();
    }

    /**
     * Spawns a new point.
     * @param data SpawnData object for point
     * @return a new point Entity
     */
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

    /**
     * Spawns a new space.
     * @param data SpawnData object for space
     * @return a new space Entity
     */
    @Spawns("space")
    public Entity newSpace(final SpawnData data) {
        Game app = (Game) (FXGL.getApp());
        app.getSpaceInfos().add(new Rectangle(data.getX(), data.getY(), data.<Integer>get("width"),
                data.<Integer>get("height")));
        return entityBuilder(data)
                .type(GameType.SPACE)
                .build();
    }

    /**
     * Spawns a new empty space.
     * @param data SpawnData object for empty space
     * @return a new empty space Entity
     */
    @Spawns("empty")
    public Entity newEmpty(final SpawnData data) {
        return entityBuilder(data)
                .type(GameType.EMPTY)
                .collidable()
                .build();
    }

    /**
     * Spawns a new enemy.
     * @param data SpawnData object for enemy
     * @return a new enemy Entity
     */
    @Spawns("enemy")
    public Entity newEnemy(final SpawnData data) {
        final int maxHp = 500;
        HealthIntComponent hp = new HealthIntComponent(maxHp);
        ProgressBar hpBar = new ProgressBar(false);
        hpBar.setFill(Color.LIGHTGREEN);

        final int width = 48;
        final int height = 7;
        hpBar.setWidth(width);
        hpBar.setHeight(height);

        final int offsetY = -5;
        hpBar.setTranslateY(offsetY);
        hpBar.setMaxValue(maxHp);
        hpBar.setCurrentValue(maxHp);
        hpBar.currentValueProperty().bind(hp.valueProperty());

        final double sixtyFivePercentHealth = 0.65;
        final double quarterHealth = 0.25;
        hp.valueProperty().addListener((ob, ov, nv) -> {
            int value = nv.intValue();
            if (value > maxHp * sixtyFivePercentHealth) {
                hpBar.setFill(Color.LIGHTGREEN);
            } else if (value > maxHp * quarterHealth) {
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
                .bbox(BoundingShape.box(width, height))
                .build();
    }

    /**
     * Spawns a new arrow projectile.
     * @param data SpawnData object for arrow projectile
     * @return a new arrow projectile Entity
     */
    @Spawns("arrowTowerBullet")
    public Entity newArrowBullet(final SpawnData data) {
        return createBullet(data);
    }

    private Entity createBullet(final SpawnData data) {
        final int width = 50;
        final int height = 10;

        return entityBuilder(data)
                .type(GameType.BULLET)
                .viewWithBBox(FXGL.texture("tower/arrow2.png", width, height))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new BulletComponent(data.get("radius"), data.get("damage")))
                .build();
    }

    /**
     * Spawns a new build indicator.
     * @param data SpawnData object for build indicator
     * @return a new build indicator Entity
     */
    @Spawns("buildIndicator")
    public Entity newBuildIndicator(final SpawnData data) {
        final int zIndex = 100;
        return entityBuilder(data)
                .with(new BuildingIndicatorComponent())
                .zIndex(zIndex)
                .build();
    }

    /**
     * Spawns a new placed button.
     * @param data SpawnData object for placed button
     * @return a new placed button Entity
     */
    @Spawns("placedButton")
    public Entity newPlacedButton(final SpawnData data) {
        final int offset = 80;
        Texture texture = FXGL.texture((String) data.get("imgName"), data.get("width"), data.get("height"));
        texture.setTranslateX((offset - texture.getWidth()) / 2.0);
        texture.setTranslateY((offset - texture.getHeight()) / 2.0);

        final int towerBorderSize = 105;
        Texture bgTexture = FXGL.texture("towerBorder.png", towerBorderSize, towerBorderSize);
        bgTexture.setTranslateX((offset - bgTexture.getWidth()) / 2);
        bgTexture.setTranslateY((offset - bgTexture.getHeight()) / 2);
        return entityBuilder(data)
                .view(new Rectangle(offset, offset, Color.web("#D5D5D511")))
                .view(bgTexture)
                .view(texture)
                .with(new PlacedButtonComponent(data.get("towerType")))
                .build();
    }

    /**
     * Spawns a new place box.
     * @param data SpawnData object for place box
     * @return a new place box Entity
     */
    @Spawns("placeBox")
    public Entity newPlaceBox(final SpawnData data) {
        final int locationX = 1000;
        return entityBuilder(data)
                .at(locationX, 0)
                .build();
    }

    /**
     * Spawns a new masking rectangle.
     * @param data SpawnData object for masking rectangle
     * @return a new masking rectangle Entity
     */
    @Spawns("maskRectangle")
    public Entity newMaskRectangle(final SpawnData data) {
        final int width = 115;
        Rectangle mask = new Rectangle(width, Game.APP_HEIGHT, Color.web("#16232B"));
        return entityBuilder(data)
                .view(mask)
                .at(Game.APP_WIDTH - width, 0)
                .build();
    }

    /**
     * Spawns a new hero.
     * @param data SpawnData object for hero
     * @return a new hero Entity
     */
    @Spawns("hero")
    public Entity newHero(final SpawnData data) {
        final double bboxWidth = 60.0;
        final double bboxHeight = 90.0;
        return FXGL.entityBuilder(data)
                .at(Game.APP_WIDTH / 2.0, Game.APP_HEIGHT / 2.0)
                .bbox(new HitBox(BoundingShape.box(bboxWidth, bboxHeight)))
                .type(GameType.HERO)
                .with(new KeepOnScreenComponent())
                .with(new HeroComponent())
                .build();
    }

    /**
     * Spawns hejo.
     * @param data SpawnData object for hejo
     * @return a hejo Entity
     */
    @Spawns("hejo")
    public Entity newHejo(final SpawnData data) {
        final int maxHp = 10000;
        HealthIntComponent hp = new HealthIntComponent(maxHp);
        ProgressBar hpBar = new ProgressBar(false);
        hpBar.setFill(Color.LIGHTGREEN);

        final int width = 48;
        final int height = 21;
        final int offsetY = -5;
        hpBar.setWidth(width);
        hpBar.setHeight(height);
        hpBar.setTranslateY(offsetY);
        hpBar.setMaxValue(maxHp);
        hpBar.setCurrentValue(maxHp);
        hpBar.currentValueProperty().bind(hp.valueProperty());

        final double sixtyFivePercent = 0.65;
        final double quarterPercent = 0.25;
        hp.valueProperty().addListener((ob, ov, nv) -> {
            int value = nv.intValue();
            if (value > maxHp * sixtyFivePercent) {
                hpBar.setFill(Color.LIGHTGREEN);
            } else if (value > maxHp * quarterPercent) {
                hpBar.setFill(Color.GOLD);
            } else {
                hpBar.setFill(Color.RED);
            }

            if (value <= 0) {
                var background = FXGL.texture("starting_scene1.PNG", Game.APP_WIDTH, Game.APP_HEIGHT);
                runOnce(() -> {
                    FXGL.getGameScene().addUINode(background);
                    var lines = getAssetLoader().loadText("endcutscene.txt");
                    var cutscene = new Cutscene(lines);
                    PropertyMap vars = FXGL.getWorldProperties();
                    vars.setValue("gameWon", true);
                    getCutsceneService().startCutscene(cutscene);
                    return null;
                }, Duration.seconds(1));
            }
        });
        return entityBuilder(data)
                .type(GameType.ENEMY)
                .with(hp)
                .view(hpBar)
                .with(new EnemyComponent(hpBar))
                .with(new CollidableComponent(true))
                .bbox(BoundingShape.box(width, height))
                .build();
    }
}
