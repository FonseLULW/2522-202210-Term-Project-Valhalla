package ca.bcit.comp2522.termproject.valhalla.data;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class TowerData {
    private int width;
    private int height;
    private int damage;
    private int attackRadius;
    private Duration attackDelay;
    private Image towerIcon;
    private String name;
    private int bulletSpeed;

    public TowerData() {
    }

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

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Image getTowerIcon() {
        return towerIcon;
    }

    public int getDamage() {
        return damage;
    }

    public Duration getAttackDelay() {
        return attackDelay;
    }

    public int getAttackRadius() {
        return attackRadius;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
