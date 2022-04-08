package ca.bcit.comp2522.termproject.valhalla.towers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;


/**
 * Default tower class for the game.
 *
 * @author kaioh08
 * @version 2022
 */
@Deprecated
public class BasicTower extends ImageView {

    /**
     * Default upgrade cost of the tower.
     */
    public static int defaultTowerUpgradeCost = 1;

    /**
     * ImageView of the tower.
     */
    public static ImageView imageView = null;

    /**
     * Default build cost of the tower.
     */
    public static int defaultTowerCost = 2;

    /**
     * The directory where the tower asset is stored.
     */
    private static String towerAssetPath = "tower/tower_image.png";

    /**
     * Default range of the tower.
     */
    private static int rangeOfTower; // not determined yet

    /**
     * x position of the tower.
     */
    protected int x;

    /**
     * y position of the tower.
     */
    protected int y;

    /**
     * Attack power of the tower.
     */
    protected int attackPower;

    /**
     * Range of the tower.
     */
    private int range;

    /**
     * Build cost of the tower.
     */
    private int buildCost;

    /**
     * Upgrade cost of the tower.
     */
    private int upgradeCost;

    /**
     * Whether the tower has shot.
     */
    private boolean isShot = false;

    /**
     * Starting attack power of the tower.
     */
    private int startingAttackPower = 3;

    /**
     * Constructor for DefaultTower.
     *
     * @param xCoordinate x position of the tower
     * @param yCoordinate y position of the tower
     */
    public BasicTower(final int xCoordinate, final int yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;

        attackPower = startingAttackPower;
        range = rangeOfTower;
        buildCost = defaultTowerCost;
        upgradeCost = defaultTowerUpgradeCost;

        setImageView(towerAssetPath);
    }

    /**
     * Constructor for Basic Tower.
     *
     * @param xCoordinate          x position of the tower
     * @param yCoordinate          y position of the tower
     * @param towerAttackPower     attack power of the tower
     * @param rangeOfStartingTower range of the tower
     * @param costOfTower          build cost of the tower
     * @param costOfUpgrade        upgrade cost of the tower
     * @param towerAssetPaths      directory where its image is stored at
     */
    public BasicTower(final int xCoordinate, final int yCoordinate, final int towerAttackPower,
                           final int rangeOfStartingTower, final int costOfTower, final int costOfUpgrade,
                           final String towerAssetPaths) {
        x = xCoordinate;
        y = yCoordinate;

        this.attackPower = towerAttackPower;
        range = rangeOfStartingTower;
        buildCost = costOfTower;
        upgradeCost = costOfUpgrade;

        setImageView(towerAssetPaths);
    }

    /**
     * Checks whether a point (x,y) of an enemy is within the range of the tower.
     *
     * @param xPosition x position
     * @param yPosition y position
     * @return boolean returns true if the point is in range of the tower, otherwise false
     */
    public boolean isInRange(final int xPosition, final int yPosition) {
        return range > Math.sqrt(Math.pow(x - xPosition, 2) + Math.pow(y - yPosition, 2));
    }

//    /**
//     * The tower attacks the monster in the range.
//     *
//     * @param enemy a monster
//     * @param arena the arena
//     * @return boolean returns true if the tower successfully shoots the monster in the arena; otherwise, false
//     */
//    public boolean shoot(EnemyClass enemy, AreaClass arena) {
//        if (!isShot) {
//            enemy.setHp(enemy.getHp() - attackPower);
//        }
//        return true;
//    }

    /**
     * Upgrades the tower by increasing its attack power by certain amount.
     */
    public void upgrade() {
        attackPower += 1; // need to discuss how much power the upgrade will give the tower
    }

    /**
     * Setting imageView for tower.
     *
     * @param assetPathOfTower directory where the image is stored at
     */
    public void setImageView(final String assetPathOfTower) {
        Image image = null;

//        try {
        image = new Image(assetPathOfTower);
//        } catch (FileNotFoundException e) {
//            // i need to write the catch block for this
//            e.printStackTrace();
//        }

        imageView = new ImageView(image);
    }

    /**
     * Returns the x position of the tower.
     *
     * @return the x position of the tower as an int
     */
//    public int getX() {
//        return x;
//    }

    /**
     * Return the y position of the tower.
     *
     * @return the y position of the tower as an int
     */
//    public int getY() {
//        return y;
//    }

    /**
     * Return the attack power of the tower.
     *
     * @return the attack power of the tower as an int
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Return the range of the tower.
     *
     * @return returns the range of the tower as an int
     */
    public int getRange() {
        return range;
    }

    /**
     * Return the default building cost of the tower.
     *
     * @return the default build cost of the tower as an int
     */
    public static int getDefaultBuildCost() {
        return defaultTowerCost;
    }

    /**
     * Return the default upgrade cost of the tower.
     *
     * @return the default upgrade cost of the tower as an int
     */
    public static int getDefaultUpgradeCost() {
        return defaultTowerUpgradeCost;
    }

    /**
     * Return the build cost of the tower.
     *
     * @return the build cost of the tower as an int.
     */
    public int getBuildCost() {
        return buildCost;
    }

    /**
     * Return the upgrade cost of the tower.
     *
     * @return the upgrade cost of the tower as an int.
     */
    public int getUpgradeCost() {
        return upgradeCost;
    }

    /**
     * return the imageView of the tower
     * @return ImageView return the imageView of the tower
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Return the class name of the tower.
     *
     * @return the class name of the tower as a String
     */
    public String getTowerType() {
        return "Basic Tower";
    }

    /**
     * Return whether the tower has shot.
     *
     * @return returns true if the tower has shot; otherwise, false
     */
    public boolean getIsShot() {
        return isShot;
    }

    /**
     * Setting isShot for tower.
     *
     * @param canShoot whether the tower has shot
     */
    public void setIsShot(final boolean canShoot) {
        isShot = canShoot;
    }
}

