package ca.bcit.comp2522.termproject.valhalla.towers;

import ca.bcit.comp2522.termproject.valhalla.game.gameController;
import javafx.scene.image.ImageView;

/**
 * A TowerView class that contains imageVIew for display on GUI.
 *
 * @author kaioh
 * @version 1.0
 */
@Deprecated
public class TowerView {

    private final BasicTower tower;

    private final ImageView imageView;

    public TowerView(final BasicTower towerVariable) {
        tower = towerVariable;
        imageView = towerVariable.getImageView();

        if (imageView != null) {
            imageView.setFitWidth(gameController.gridWidth);
            imageView.setFitHeight(gameController.gridHeight);
        }
        setImageView(tower.getX(), tower.getY());
    }


    /**
     * Setting the imageView position.
     *
     * @param xCoordinate x position
     * @param yCoordinate y position
     */
    public void setImageView(final double xCoordinate, final double yCoordinate) {

        if (imageView != null) {

            imageView.setX(xCoordinate);
            imageView.setY(yCoordinate);

        }
    }

    public BasicTower getTower() {
        return tower;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
