package ca.bcit.comp2522.termproject.valhalla.data;

import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * TowerData class.
 * @author kaioh
 * @author FonseLULW
 * @version 1.0
 */
public class TowerData {
    private int width;
    private int height;
    private final int damage;
    private final int attackRadius;
    private final Duration attackDelay;
    private final Image towerIcon;
    private String name;
    private final int bulletSpeed;

    /**
     * Constructs TowerData.
     * @param name a name as a String
     * @param width an int specifying width
     * @param height an int specifying height
     * @param damage an int specifying damage
     * @param attackRadius an int specifying attack radius
     * @param bulletSpeed an int specifying bullet speed
     * @param attackDelay a Duration specifying attack delay
     * @param towerIcon an Image for the tower
     */
    public TowerData(final String name, final int width, final int height, final int damage, final int attackRadius,
                     final int bulletSpeed, final Duration attackDelay, final Image towerIcon) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.attackRadius = attackRadius;
        this.bulletSpeed = bulletSpeed;
        this.attackDelay = attackDelay;
        this.towerIcon = towerIcon;
    }

    /**
     * Returns the bullet speed as an int.
     * @return the bullet speed as an int
     */
    public int getBulletSpeed() {
        return bulletSpeed;
    }

    /**
     * Returns the name as a String.
     * @return the name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * @param name a String for name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the tower image as an Image.
     * @return the tower image as an Image
     */
    public Image getTowerIcon() {
        return towerIcon;
    }

    /**
     * Returns the damage as an int.
     * @return the damage as an int
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the attack delay as a Duration.
     * @return the attack delay as a Duration
     */
    public Duration getAttackDelay() {
        return attackDelay;
    }

    /**
     * Returns the attack radius as an int.
     * @return the attack radius as an int
     */
    public int getAttackRadius() {
        return attackRadius;
    }

    /**
     * Returns the width as an int.
     * @return the width as an int
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height as an int.
     * @return the height as an int
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the width.
     * @param width an int for width
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Sets the height.
     * @param height an int for height
     */
    public void setHeight(final int height) {
        this.height = height;
    }
}
