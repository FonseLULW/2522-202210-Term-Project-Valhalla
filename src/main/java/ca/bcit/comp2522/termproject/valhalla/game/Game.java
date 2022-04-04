package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.compnent.BulletComponent;
import ca.bcit.comp2522.termproject.valhalla.compnent.EnemyComponent;
import ca.bcit.comp2522.termproject.valhalla.constant.Config;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import ca.bcit.comp2522.termproject.valhalla.compnent.BuildingIndicatorComponent;
import ca.bcit.comp2522.termproject.valhalla.compnent.PlacedButtonComponent;
import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import ca.bcit.comp2522.termproject.valhalla.data.TowerData;
import ca.bcit.comp2522.termproject.valhalla.constant.TowerType;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Game.
 */
public class Game extends GameApplication {
    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 563;
    private final LinkedHashMap<Integer, Pair<Point2D, String>> pointInfos = new LinkedHashMap<>();
    private Entity buildIndicator;
    private BuildingIndicatorComponent buildIndicatorComponent;
    private Entity emptyEntity;
    private PlacedButtonComponent arrowBtn;

    ArrayList<Rectangle> spaceInfos = new ArrayList<>();

    @Override
    protected void initSettings(final GameSettings settings) {
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.setTitle("Valhalla");
        settings.setAppIcon("Logo.png");
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(false);
        settings.setPreserveResizeRatio(true);
        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new ValhallaMenu();
            }

//            @Override
//            public FXGLMenu newGameMenu() {
//                return new ValhallaMenu();
//            }
        });
    }

    private void hideIndicator() {
        buildIndicator.setX(-1000);
        buildIndicator.setY(-1000);
    }

    @Override
    protected void initGameVars(final Map<String, Object> vars) {
        vars.put("towerType", TowerType.NONE);

    }

    @Override
    protected void initInput() {
        FXGL.getInput().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            TowerType towerType = FXGL.geto("towerType");
            if (towerType == TowerType.NONE) {
                return;
            }
            trackMouse(towerType);
        });

        FXGL.getInput().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                FXGL.set("towerType", TowerType.NONE);
                hideIndicator();
                return;
            }
            TowerType towerType = FXGL.geto("towerType");
            if (towerType == TowerType.NONE) {
                return;
            }
            buildTower(towerType);
        });

    }

    private void trackMouse(final TowerType towerType) {
        TowerData data = getTowerData(towerType);
        if (data == null) {
            return;
        }

        int w = data.getWidth();
        int h = data.getHeight();

        Point2D p = FXGL.getInput().getMousePositionWorld();
        double x = p.getX() - w / 2.0;
        double y = p.getY() - h / 2.0;
        buildIndicator.setX(x);
        buildIndicator.setY(y);
        boolean flag = false;

        for (Rectangle r : spaceInfos) {
            if (r.getX() <= x && r.getWidth() + r.getX() >= x + w && r.getY() <= y && r.getHeight() + r.getY() >= y + h) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            buildIndicatorComponent.canBuild(false);
            return;
        }

        emptyEntity.setX(x);
        emptyEntity.setY(y);

        List<Entity> towers = FXGL.getGameWorld().getEntitiesByType(GameType.TOWER);

        BoundingBoxComponent emptyBox = emptyEntity.getBoundingBoxComponent();

        boolean canGenerate = true;
        for (Entity tower : towers) {
            if (emptyBox.isCollidingWith(tower.getBoundingBoxComponent())) {
                canGenerate = false;
                break;
            }
        }
        buildIndicatorComponent.canBuild(canGenerate);
    }


    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new NodeFactory());
        FXGL.spawn("hero", 60, 60);

        FXGL.getGameScene().setBackgroundColor(Color.web("#16232B"));
        FXGL.image("slugman_1.png");
        FXGL.image("slugman_2.png");
        FXGL.image("slugman_3.png");
        FXGL.image("slugman_1.png");
        FXGL.setLevelFromMap("/resources/assets.textures/level1.tmx");
        FXGL.getGameWorld().addEntityFactory(new GameEntityFactory());

        List<Entity> tempEntities = FXGL.getGameWorld().getEntitiesByType(GameType.SPACE, GameType.POINT);
        FXGL.getGameWorld().removeEntities(tempEntities);
        buildIndicator = FXGL.spawn("buildIndicator");
        hideIndicator();
        buildIndicatorComponent = buildIndicator.getComponent(BuildingIndicatorComponent.class);

        emptyEntity = FXGL.spawn("empty");
        emptyEntity.getBoundingBoxComponent().clearHitBoxes();
        emptyEntity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(30, 30)));
        emptyEntity.setX(-100);
        emptyEntity.setY(-100);

        FXGL.runOnce(() -> {
            FXGL.run(() -> {
                FXGL.spawn("enemy", pointInfos.get(0).getKey());
            }, Duration.seconds(1), 20);
        }, Duration.seconds(5));

        FXGL.run(() -> {
            FXGL.run(() -> {
                FXGL.spawn("enemy", pointInfos.get(0).getKey());
            }, Duration.seconds(1), 20);
        }, Duration.seconds(30), 20);

        FXGL.spawn("placeBox");

        arrowBtn = FXGL.spawn("placedButton", new SpawnData(1016, 360)
                .put("imgName", "tower_image.png")
                .put("width", 43.0)
                .put("height", 68.0)
                .put("towerType", TowerType.ARROW)).getComponent(PlacedButtonComponent.class);

        FXGL.getop("towerType").addListener((ob, ov, nv) -> {
            if (TowerType.ARROW == nv) {
                selectedPlaceBtn(true);
            }
            if (TowerType.NONE == nv) {
                selectedPlaceBtn(false);
            }
        });

    }

    public LinkedHashMap<Integer, Pair<Point2D, String>> getPointInfos() {
        return pointInfos;
    }

    public ArrayList<Rectangle> getSpaceInfos() {
        return spaceInfos;
    }

    private void buildTower(final TowerType towerType) {
        TowerData towerData = getTowerData(towerType);
        if (towerData == null) {
            return;
        }

        int widthOfTower = towerData.getWidth();
        int heightOfTower = towerData.getHeight();

        Point2D pointGame = FXGL.getInput().getMousePositionWorld();
        double xCoordinate = pointGame.getX() - widthOfTower / 2.0;
        double yCoordinate = pointGame.getY() - heightOfTower / 2.0;

        boolean flag = false;
        for (Rectangle r : spaceInfos) {
            if (r.getX() <= xCoordinate && r.getWidth() + r.getX() >= xCoordinate + widthOfTower && r.getY() <= yCoordinate && r.getHeight() + r.getY() >= yCoordinate + heightOfTower) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return;
        }
        emptyEntity.setX(xCoordinate);
        emptyEntity.setY(yCoordinate);
        buildIndicator.setX(xCoordinate);
        buildIndicator.setY(yCoordinate);
        List<Entity> towers = FXGL.getGameWorld().getEntitiesByType(GameType.TOWER);
        BoundingBoxComponent emptyBox = emptyEntity.getBoundingBoxComponent();
        boolean canGenerate = true;
        for (Entity tower : towers) {
            if (emptyBox.isCollidingWith(tower.getBoundingBoxComponent())) {
                canGenerate = false;
                break;
            }
        }

        buildIndicatorComponent.canBuild(canGenerate);
        if (canGenerate) {
            FXGL.spawn(towerData.getName(), xCoordinate, yCoordinate);
            FXGL.set("towerType", TowerType.NONE);
            hideIndicator();
        }

    }

    private void selectedPlaceBtn(final boolean arrow) {
        arrowBtn.setSelected(arrow);
    }

    @Override
    protected void onPreInit() {
        FXGL.getSettings().setGlobalSoundVolume(0.5);
        FXGL.getSettings().setGlobalMusicVolume(0.5);
        // FXGL.loopBGM("find free music and we can play music on loop");
    }

    private TowerData getTowerData(final TowerType towerType) {
        TowerData data;
        if (towerType == TowerType.ARROW) {
            data = Config.ARROW_TOWER_DATA;
        } else {
            return null;
        }
        buildIndicatorComponent.resetIndicator(data.getTowerIcon(), data.getAttackRadius());
        emptyEntity.getBoundingBoxComponent().clearHitBoxes();
        emptyEntity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(data.getWidth(), data.getHeight())));
        return data;
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameType.BULLET, GameType.ENEMY) {
            @Override
            protected void onCollisionBegin(final Entity bullet, final Entity enemy) {
                EnemyComponent enemyComponent = enemy.getComponent(EnemyComponent.class);
                if (enemyComponent.isDead()) {
                    return;
                }
                BulletComponent component = bullet.getComponent(BulletComponent.class);
                bullet.removeFromWorld();
                enemyComponent.attacked(component.getDamage());
            }

        });
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
