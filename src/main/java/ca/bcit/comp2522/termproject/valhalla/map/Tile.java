package ca.bcit.comp2522.termproject.valhalla.map;

import ca.bcit.comp2522.termproject.valhalla.game.DefaultTower;
import javafx.application.Application;

import java.util.ArrayList;

/**
 * A title class to make a map for the game.
 *
 * @author kaioh08
 * @author FonseLULW
 * @version 2022
 */
abstract class Tile extends Application {

    /**
     * ArrayList of towers on the arena.
     */
    public ArrayList<DefaultTower> towers; //ArrayList of towers on the arena

    /**
     * number of columns of grid in the arena.
     */
    private final int numOfColumn;

    /**
     * number of rows of grid in the arena.
     */
    private final int numOfRow; //number of Rows of grid in the arena. This is not the arena width in pixel


    /**
     * Constructor for the Tile class.
     *
     * @param numOfColumn number of columns in the map as int
     * @param numOfRow    number of row in the map as int
     */
    public Tile(final int numOfColumn, final int numOfRow) {
        this.numOfColumn = numOfColumn;
        this.numOfRow = numOfRow;
    }
}
