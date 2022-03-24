package ca.bcit.comp2522.termproject.valhalla.entities;

import java.util.Objects;

/**
 * Hitbox class.
 * @author FonseLULW
 * @version 1.0
 */
public final class Hitbox {
    // x and y values represent the top-left point of the hitbox
    private int x;
    private int y;
    private final int width;
    private final int height;

    /**
     * Constructs a new Hitbox object.
     * @param x the x-coordinate of the left edge of this Hitbox.
     * @param y the y-coordinate of the top edge of this Hitbox.
     * @param width the width of this Hitbox.
     * @param height the height of this Hitbox.
     */
    public Hitbox(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns an int representing the x-coordinate of this Hitbox's left edge.
     * @return an int representing the x-coordinate of this Hitbox's left edge
     */
    public int x() {
        return x;
    }

    /**
     * Returns an int representing the y-coordinate of this Hitbox's top edge.
     * @return an int representing the y-coordinate of this Hitbox's top edge
     */
    public int y() {
        return y;
    }

    /**
     * Returns the width of this Hitbox as an int.
     * @return the width of this Hitbox as an int
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this Hitbox as an int.
     * @return the height of this Hitbox as an int
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the x-coordinate of this Hitbox's left edge.
     * @param x the new x-coordinate of this Hitbox's left edge
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this Hitbox's top edge.
     * @param y the new y-coordinate of this Hitbox's top edge
     */
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * Returns true if a collision happens between this Hitbox and another Hitbox.
     * A collision happens when two hitboxes intersect at any point including their boundaries
     * @param other a Hitbox object
     * @return true if there is a collision, else false
     */
    public boolean collision(final Hitbox other) {
        /* collision if
         * 1) x intersects:
         *      a) if either other's x or other's x + width is in the range of this's x and this's x + width
         *      or b) if this's x or this's x + width is in the range of other's x + other's x + width
         * and 2) y intersects: just like x intersects but for y and height
         */
        boolean intersectsX = false;
        boolean intersectsY = true;
        return intersectsX && intersectsY;
    }

    /**
     * Returns a String representation of this Hitbox.
     * @return a String representation of this Hitbox
     */
    @Override
    public String toString() {
        return "Hitbox{"
                + "x=" + x
                + ", y=" + y
                + ", width=" + width
                + ", height=" + height
                + '}';
    }

    /**
     * Returns true if Object o is equal to this Hitbox.
     * @param o an Object
     * @return a boolean true if Object o is equal to this Hitbox, else false
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hitbox hitbox = (Hitbox) o;
        return x == hitbox.x && y == hitbox.y && width == hitbox.width && height == hitbox.height;
    }

    /**
     * Returns an int representing the hash code of this Hitbox.
     * @return an int representing the hash code of this Hitbox
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
