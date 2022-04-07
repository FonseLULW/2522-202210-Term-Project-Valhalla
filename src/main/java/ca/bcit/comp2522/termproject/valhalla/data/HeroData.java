package ca.bcit.comp2522.termproject.valhalla.data;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class HeroData {
    private int width;
    private int height;
    private int damage;
    private int attackRadius;
    private Duration attackDelay;
    private Image heroIcon;
    private String name;
    private int attackSpeed;

    public HeroData() {
    }

    public HeroData(final String name, final int width, final int height, final int damage, final int attackRadius,
                     final int attackSpeed, final Duration attackDelay, final Image heroIcon) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.attackRadius = attackRadius;
        this.attackSpeed = attackSpeed;
        this.attackDelay = attackDelay;
        this.heroIcon = heroIcon;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Image getHeroIcon() {
        return heroIcon;
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

