package ca.bcit.comp2522.termproject.valhalla.game;

import ca.bcit.comp2522.termproject.valhalla.map.Arena;
import ca.bcit.comp2522.termproject.valhalla.towers.TowerView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;


public class gameController {

    /**
     * True if a monster has reach the endpoint on the Arena, false otherwise.
     */
    public static boolean gameOver = false;

    /**
     * Has a width of the grid.
     */
    public static int gridWidth = 40;

    /**
     * Has height of the grid.
     */
    public static int gridHeight = 40;

    /**
     * An Arena object that represents the Arena of the game.
     */
    public Arena arena;

    /**
     * Defines the width of the arena.
     */
    static final int arenaWidth = 480;

    /**
     * Defines the height of the arena.
     */
    static final int arenaHeight = 480;

    private static TowerView selectedTowerImageView = null;
    private ArrayList<TowerView> towerViewList = new ArrayList<TowerView>();


    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonNextFrame;

    @FXML
    private AnchorPane paneArena;

    @FXML
    private Label labelBasicTower;

    /**
     * Default Constructor for MyController Class.
     */
    public gameController() {
    }
}
