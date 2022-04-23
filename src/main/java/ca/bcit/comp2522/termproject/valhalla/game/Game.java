package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.component.BuildingIndicatorComponent;
import ca.bcit.comp2522.termproject.valhalla.component.BulletComponent;
import ca.bcit.comp2522.termproject.valhalla.component.EnemyComponent;
import ca.bcit.comp2522.termproject.valhalla.component.HeroComponent;
import ca.bcit.comp2522.termproject.valhalla.component.PlacedButtonComponent;
import ca.bcit.comp2522.termproject.valhalla.constant.Config;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import ca.bcit.comp2522.termproject.valhalla.constant.GameType;
import ca.bcit.comp2522.termproject.valhalla.data.TowerData;
import ca.bcit.comp2522.termproject.valhalla.constant.TowerType;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.ui.Position;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

/**
 * The Game class.
 * @author FonseLULW
 * @author kaioh
 * @version 1.0
 */
public class Game extends GameApplication {
    /**
     * The Game window's width.
     */
    public static final int APP_WIDTH = 1115;

    /**
     * The Game window's height.
     */
    public static final int APP_HEIGHT = 15 * 50;

    private final LinkedHashMap<Integer, Pair<Point2D, String>> pointInfos = new LinkedHashMap<>();
    private final CutsceneManager cutsceneManager;
    private final ArrayList<Rectangle> spaceInfos;
    private Entity buildIndicator;
    private BuildingIndicatorComponent buildIndicatorComponent;
    private Entity emptyEntity;
    private Entity arrowBtn;
    private Entity hero;

    /**
     * Creates a new Game.
     */
    public Game() {
        cutsceneManager = new CutsceneManager("starting_scene1.PNG");
        spaceInfos = new ArrayList<>();
    }

    /**
     * Initializes settings.
     * @param settings a GameSettings object
     */
    @Override
    protected void initSettings(final GameSettings settings) {
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.setTitle("Valhalla");
        settings.setAppIcon("logo.png");
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        settings.setPreserveResizeRatio(true);
        settings.setManualResizeEnabled(true);
        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        settings.setSceneFactory(new SceneFactory() {
            @NotNull
            @Override
            public FXGLMenu newMainMenu() {
                return new ValhallaMenu();
            }

            @NotNull
            @Override
            public FXGLMenu newGameMenu() {
                return new ValhallaPauseMenu();
            }
        });
    }

    private void hideIndicator() {
        final int offscreen = -1000;
        buildIndicator.setX(offscreen);
        buildIndicator.setY(offscreen);
    }

    /**
     * Initializes Game variables.
     * @param vars a Map of String and Object key value pairs
     */
    @Override
    protected void initGameVars(final Map<String, Object> vars) {
        final int baseHealth = 1000;
        final int maxWaves = 5;
        vars.put("towerType", TowerType.NONE);
        vars.put("wavesSpawned", 0);
        vars.put("baseHealth", baseHealth);
        vars.put("bossSpawned", false);
        vars.put("maxWaves", maxWaves);

    }

    /**
     * Initializes Game input.
     */
    @Override
    protected void initInput() {
        // hero movement
        Input input = getInput();
        input.addAction(new UserAction("up") {
            @Override
            protected void onActionBegin() {
                hero.getComponent(HeroComponent.class).move();
            }

            @Override
            protected void onAction() {
                if (hero != null) {
                    hero.translateY(-hero.getComponent(HeroComponent.class).getSpeed());
                }
            }
        }, KeyCode.W);
        input.addAction(new UserAction("right") {
            @Override
            protected void onActionBegin() {
                hero.getComponent(HeroComponent.class).move();
            }

            @Override
            protected void onAction() {
                if (hero != null) {
                    hero.translateX(hero.getComponent(HeroComponent.class).getSpeed());
                    hero.setScaleX(-1);
                }
            }
        }, KeyCode.D);
        input.addAction(new UserAction("down") {
            @Override
            protected void onActionBegin() {
                hero.getComponent(HeroComponent.class).move();
            }

            @Override
            protected void onAction() {
                if (hero != null) {
                    hero.translateY(hero.getComponent(HeroComponent.class).getSpeed());
                }
            }
        }, KeyCode.S);
        input.addAction(new UserAction("left") {
            @Override
            protected void onActionBegin() {
                hero.getComponent(HeroComponent.class).move();
            }

            @Override
            protected void onAction() {
                if (hero != null) {
                    hero.translateX(-hero.getComponent(HeroComponent.class).getSpeed());
                    hero.setScaleX(1);
                }
            }
        }, KeyCode.A);
        input.addAction(new UserAction("attack") {
            @Override
            protected void onActionBegin() {
                if (hero != null) {
                    hero.getComponent(HeroComponent.class).attackArea();
                    PropertyMap state = FXGL.getWorldProperties();
                }
            }
        }, MouseButton.PRIMARY);

        // towers
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

    /**
     * Initializes Game.
     */
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameEntityFactory());
        FXGL.setLevelFromMap("level1.tmx");

        List<Entity> tempEntities = FXGL.getGameWorld().getEntitiesByType(GameType.SPACE, GameType.POINT);
        FXGL.getGameWorld().removeEntities(tempEntities);
        buildIndicator = FXGL.spawn("buildIndicator");
        hideIndicator();
        buildIndicatorComponent = buildIndicator.getComponent(BuildingIndicatorComponent.class);

        final int squareSize = 30;
        final int emptyPos = -100;
        emptyEntity = FXGL.spawn("empty");
        emptyEntity.getBoundingBoxComponent().clearHitBoxes();
        emptyEntity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(squareSize, squareSize)));
        emptyEntity.setX(emptyPos);
        emptyEntity.setY(emptyPos);

        final int enemyLimit = 20;
        final int startDelay = 5;
        FXGL.runOnce(() -> {
            FXGL.run(() -> {
                FXGL.spawn("enemy", pointInfos.get(0).getKey());
            }, Duration.seconds(1), enemyLimit);
            PropertyMap state = FXGL.getWorldProperties();
            state.setValue("wavesSpawned", state.getInt("wavesSpawned") + 1);
        }, Duration.seconds(startDelay));

        final int waveDelay = 30;
        FXGL.run(() -> {
            FXGL.run(() -> {
                FXGL.spawn("enemy", pointInfos.get(0).getKey());
            }, Duration.seconds(1), enemyLimit);
            PropertyMap state = FXGL.getWorldProperties();
            state.setValue("wavesSpawned", state.getInt("wavesSpawned") + 1);
        }, Duration.seconds(waveDelay), FXGL.getWorldProperties().getInt("maxWaves") - 1);

        final int zIndex = 20;
        FXGL.spawn("maskRectangle").setZIndex(zIndex);
        FXGL.spawn("placeBox");

        final int arrowX = 1016;
        final int arrowY = 360;
        final double arrowWidth = 43.0;
        final double arrowHeight = 68.0;
        arrowBtn = FXGL.spawn("placedButton", new SpawnData(arrowX, arrowY)
                .put("imgName", "tower/tower_image.png")
                .put("width", arrowWidth)
                .put("height", arrowHeight)
                .put("towerType", TowerType.ARROW));
        arrowBtn.getComponent(PlacedButtonComponent.class);
        arrowBtn.setZIndex(zIndex + 1);

        FXGL.getop("towerType").addListener((ob, ov, nv) -> {
            if (TowerType.ARROW == nv) {
                selectedPlaceBtn(true);
            }
            if (TowerType.NONE == nv) {
                selectedPlaceBtn(false);
            }
        });
        PropertyMap vars = FXGL.getWorldProperties();
        vars.intProperty("wavesSpawned").addListener((ob, ov, nv) -> {
            if (nv.intValue() == FXGL.getWorldProperties().getInt("maxWaves")) {
                FXGL.spawn("hejo", pointInfos.get(0).getKey());
                FXGL.getWorldProperties().setValue("bossSpawned", true);
                MusicPlayer.getSingleton().playGameMusic();
            }
        });

        final int heroLocation = 60;
        hero = FXGL.spawn("hero", heroLocation, heroLocation);
        cutsceneManager.playCutscene("cutscene.txt");
        MusicPlayer.getSingleton().playGameMusic();
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

    /**
     * Returns the point infos as a LinkedHashMap.
     * @return the point infos as a LinkedHashMap
     */
    public LinkedHashMap<Integer, Pair<Point2D, String>> getPointInfos() {
        return pointInfos;
    }

    /**
     * Returns the space infos as an ArrayList of Rectangle.
     * @return the space infos as an ArrayList of Rectangle
     */
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
        arrowBtn.getComponent(PlacedButtonComponent.class).setSelected(arrow);
    }

    /**
     * Runs before the Game initialization.
     */
    @Override
    protected void onPreInit() {
        final double volume = 0.5;
        FXGL.getSettings().setGlobalSoundVolume(volume);
        FXGL.getSettings().setGlobalMusicVolume(volume);
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

    /**
     * Initializes the FXGL physics engine components.
     */
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

    /**
     * Initializes the UI.
     */
    @Override
    protected void initUI() {
        // wave counter
        final double barWidth = 240;
        final double barHeight = 10;
        final int x = 5;
        final int offsetHeight = 50;
        ProgressBar wavesBar = new ProgressBar(true);
        wavesBar.setFill(Color.DEEPSKYBLUE);
        wavesBar.setLabelPosition(Position.RIGHT);
        wavesBar.setLabelVisible(true);
        wavesBar.setTranslateX(x);
        wavesBar.setTranslateY(APP_HEIGHT - offsetHeight);
        wavesBar.setMaxValue(x);
        wavesBar.setLabelFill(Color.DARKBLUE);
        PropertyMap state = FXGL.getWorldProperties();
        wavesBar.currentValueProperty().bind(state.intProperty("wavesSpawned"));

        state.intProperty("wavesSpawned").addListener((ob, ov, nv) -> {
            if (nv.intValue() >= FXGL.getWorldProperties().getInt("maxWaves")) {
                wavesBar.setFill(Color.CRIMSON);
                FXGL.getNotificationService().setBackgroundColor(Color.CRIMSON);
                FXGL.getNotificationService().setTextColor(Color.LIGHTSKYBLUE);
                FXGL.getAudioPlayer().pauseAllMusic();
                FXGL.getNotificationService().pushNotification("You are going to have a very bad time...");
                wavesBar.setFill(Color.LIGHTSKYBLUE);
            }
        });
        FXGL.addUINode(wavesBar);

        Text wavesText = new Text(wavesBar.getTranslateX() + barWidth,
                wavesBar.getTranslateY() + barHeight, "waves initiated");
        wavesText.setFill(Color.DARKBLUE);
        FXGL.addUINode(wavesText);

        // base health bar
        final int translateX = 5;
        final int translateY = 10;
        ProgressBar baseHealthBar = new ProgressBar(true);
        baseHealthBar.setMaxValue(state.getInt("baseHealth"));
        baseHealthBar.setMinValue(0);
        baseHealthBar.setLabelVisible(true);
        baseHealthBar.setLabelFill(Color.MEDIUMVIOLETRED);
        baseHealthBar.setFill(Color.MEDIUMVIOLETRED);
        baseHealthBar.setLabelPosition(Position.RIGHT);
        baseHealthBar.setTranslateX(translateX);
        baseHealthBar.setTranslateY(APP_HEIGHT - offsetHeight + barHeight + translateY);
        baseHealthBar.currentValueProperty().bind(state.intProperty("baseHealth"));

        final int lowHealth = 200;
        state.intProperty("baseHealth").addListener((ob, ov, nv) -> {
            if (nv.intValue() <= 0) {
                showGameOver();
            } else if (nv.intValue() <= lowHealth) {
                baseHealthBar.setFill(Color.RED);
            }
        });
        FXGL.addUINode(baseHealthBar);

        final int offsetText = 50;
        Text baseHealthBarText = new Text(baseHealthBar.getTranslateX() + barWidth + offsetText,
                baseHealthBar.getTranslateY() + barHeight, "base health");
        baseHealthBarText.setFill(Color.MEDIUMVIOLETRED);
        FXGL.addUINode(baseHealthBarText);

    }

    /**
     * Runs when game lost.
     */
    public void showGameOver() {
        MusicPlayer.getSingleton().playSadMusic();
        FXGL.getGameWorld().removeEntity(hero);
        cutsceneManager.setCutsceneBackgroundFilename("starting_scene2.png");
        cutsceneManager.playCutscene("badendcutscene.txt", () -> {
            FXGL.getWindowService().gotoMainMenu();
            cutsceneManager.setCutsceneBackgroundFilename("starting_scene1.png");
        });
        hero = null;
    }

    /**
     * Drives the Game.
     * @param args unused
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
