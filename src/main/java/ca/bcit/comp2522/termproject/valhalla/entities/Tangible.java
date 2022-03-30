package ca.bcit.comp2522.termproject.valhalla.entities;

import javafx.scene.Node;

/**
 * A Tangible interface for all that can be touched.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public interface Tangible {
//    Node getSprite();
    void setWidth(int width);
    void setHeight(int height);
    boolean collision(Entity entity);
}
