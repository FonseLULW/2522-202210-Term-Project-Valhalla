package ca.bcit.comp2522.termproject.valhalla.entities;

/**
 * Hitbox class.
 * @author FonseLULW
 * @version 1.0
 */
public final class Hitbox {
    // x and y values represent the topleft point of the hitbox
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

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public boolean collision(final Hitbox other) {
        /* collision if
         * 1) x intersects:
         *      a) if either other's x or other's x + width is in the range of this's x and this's x + width
         *      or b) if this's x or this's x + width is in the range of other's x + other's x + width
         * and 2) y intersects: just like x intersects but for y and height
         */
        boolean intersectsX = false;
        boolean intersectsY = false;
        return intersectsX && intersectsY;
    }

    //toString, hashCode, equals
}
