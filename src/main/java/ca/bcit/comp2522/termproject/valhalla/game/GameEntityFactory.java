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
//                .with(new TransformComponent())
                .with(new EnemyComponent(hpBar))
                .with(new CollidableComponent(true))
                .bbox(BoundingShape.box(48, 48))
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
        return entityBuilder(data)
                .type(GameType.BULLET)
                .viewWithBBox(FXGL.texture("tower/arrow2.png", 50, 10))
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
        return entityBuilder(data)
                .with(new BuildingIndicatorComponent())
                .zIndex(100)
                .build();
    }

    /**
     * Spawns a new placed button.
     * @param data SpawnData object for placed button
     * @return a new placed button Entity
     */
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

    /**
     * Spawns a new place box.
     * @param data SpawnData object for place box
     * @return a new place box Entity
     */
    @Spawns("placeBox")
    public Entity newPlaceBox(final SpawnData data) {
        return entityBuilder(data)
                .at(1000, 0)
                .build();
    }

    /**
     * Spawns a new masking rectangle.
     * @param data SpawnData object for masking rectangle
     * @return a new masking rectangle Entity
     */
    @Spawns("maskRectangle")
    public Entity newMaskRectangle(final SpawnData data) {
        Rectangle mask = new Rectangle(115, Game.APP_HEIGHT, Color.web("#16232B"));
        return entityBuilder(data)
                .view(mask)
                .at(Game.APP_WIDTH-115, 0)
                .build();
    }

    /**
     * Spawns a new hero.
     * @param data SpawnData object for hero
     * @return a new hero Entity
     */
    @Spawns("hero")
    public Entity newHero(final SpawnData data) {
        final double scale = 0.1;
        final double width = 626.0;
        final double height = 899.0;
        final ImageView sprite = new ImageView(new Image("assets/textures/hero1_idle.png",
                width * scale, height * scale, true, true));

        final double speed = 5.0;
        return FXGL.entityBuilder(data)
                .at(Game.APP_WIDTH / 2.0, Game.APP_HEIGHT / 2.0)
                .bbox(new HitBox(BoundingShape.box(60.0, 90.0)))
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
        hpBar.setWidth(48);
        hpBar.setHeight(21);
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
                .bbox(BoundingShape.box(88, 120))
                .build();
    }
}
