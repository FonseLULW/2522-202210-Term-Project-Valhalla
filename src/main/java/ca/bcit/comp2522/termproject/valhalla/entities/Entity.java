package ca.bcit.comp2522.termproject.valhalla.entities;

import javafx.scene.image.ImageView;

/**
 * An abstract Entity class for all things that exist.
 * @author FonseLULW
 * @version 1.0
 */
public abstract class Entity implements Slayable{
    // should all be protected
    private ImageView sprite;
    private Hitbox hitbox;

    // could maybe be decomposed?
    private int maxHP;
    private int currentHP;
    private int level;
    private int damage;
    private int defence;
    private double range;
    private double speed;
    private String name;

    // maybe a new interface??
    public abstract void attack();
    public abstract void move();
}