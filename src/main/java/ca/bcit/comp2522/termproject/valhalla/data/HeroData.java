package ca.bcit.comp2522.termproject.valhalla.data;

import javafx.util.Duration;

/**
 * A HeroData class.
 * @author FonseLULW
 * @author kaioh
 * @version 1.0
 */
public class HeroData {
    private double width;
    private double height;
    private final int damage;
    private final int attackRadius;
    private final Duration attackDelay;
    private String name;
    private final double moveSpeed;

    /**
     * Constructs a new HeroData object.
     * @param name the hero's name as a String
     * @param width the width of the hero on the display as a double
     * @param height the height of the hero on the display as a double
     * @param damage the damage the hero deals as an int
     * @param attackRadius the attack radius of the hero as an int
     * @param attackDelay the attack delay of the hero as a Duration
     * @param moveSpeed the speed of the hero as a double
     */
    public HeroData(final String name, final double width, final double height, final int damage,
                    final int attackRadius, final Duration attackDelay, final double moveSpeed) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.attackRadius = attackRadius;
        this.attackDelay = attackDelay;
        this.moveSpeed = moveSpeed;
    }

    /**
     * Returns the name of the hero as a String.
     * @return the name of the hero as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the hero.
     * @param name a String representing the new name of the hero
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the hero's attack damage as an int.
     * @return the hero's attack damage as an int
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the hero's attack delay as a Duration.
     * @return the hero's attack delay as a Duration
     */
    public Duration getAttackDelay() {
        return attackDelay;
    }

    /**
     * Returns the hero's attack radius as an int.
     * @return the hero's attack radius as an int
     */
    public int getAttackRadius() {
        return attackRadius;
    }

    /**
     * Returns the hero's move speed as a double.
     * @return the hero's move speed as a double
     */
    public double getMoveSpeed() {
        return moveSpeed;
    }

    /**
     * Returns the hero's width as a double.
     * @return the hero's width as a double
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the hero's height as a double.
     * @return the hero's height as a double
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the width of the hero.
     * @param width a double representing the new width
     */
    public void setWidth(final double width) {
        this.width = width;
    }

    /**
     * Sets the height of the hero.
     * @param height a double representing the new height
     */
    public void setHeight(final double height) {
        this.height = height;
    }
}

