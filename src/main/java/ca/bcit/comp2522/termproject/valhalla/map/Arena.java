package ca.bcit.comp2522.termproject.valhalla.map;

import java.util.ArrayList;
import java.util.Random;

/**
 * Arena class.
 *
 * @author kaioh
 * @author FonseLULW
 * @version 1
 */
public class Arena {

    /**
     * Provides true if the arena is a valid arena, false otherwise. Made for testing isGreen.
     */
    public boolean isValidArena;

    /**
     * The number of frames that have passed, analogous to time.
     */
    private int time;

    /**
     * number of columns of grid in the arena.
     */
    private final int numOfColumn;

    /**
     * number of rows of grid in the arena.
     */
    private final int numOfRow;


    /**
     * Provides true if the grid at grid coordinate [row][Column] is green.
     */
    private boolean[][] isGreen;

    /**
     * The amount of money the player has.
     */
    private int money;

    /**
     * Constructor for Arena class.
     *
     * @param numOfColumn The number of Columns on the arena
     * @param numOfRow    The numbers of Rows on the arena
     * @param isGreen     A 2D array where isGreen[row][column]
     *                    is true if grid at grid coordinate (row,column) is a green grid
     */
    public Arena(final int numOfColumn, final int numOfRow, final boolean[][] isGreen) {
        this.numOfColumn = numOfColumn;
        this.numOfRow = numOfRow;

        time = 0;

        isValidArena = true;

        if (numOfRow != isGreen.length) {
            System.out.println("Arena constructor invalid input: Column Size Mismatch");
            isValidArena = false;
        }

        if (numOfColumn != isGreen[0].length) {
            System.out.println("Arena constructor invalid input: Row Size Mismatch");
            isValidArena = false;
        }

        int rowSize = isGreen[0].length;
        for (boolean[] booleans : isGreen) {
            if (booleans.length != rowSize) {
                System.out.println("Arena constructor invalid input: non-uniform Row size");
                isValidArena = false;
            }
        }

        if (isValidArena) {
            this.isGreen = new boolean[numOfRow][numOfColumn];

            for (int i = 0; i < numOfRow; i++) {
                System.arraycopy(isGreen[i], 0, this.isGreen[i], 0, numOfColumn);
            }
        }
        System.out.println("Constructor Ends");

    }


    /**
     * Returns true if grid at grid coordinate (row,column) is green.
     *
     * @param row    row number of the grid
     * @param column column number of grid
     * @return returns true if grid at grid coordinate (row,column) is green
     */
    public boolean isGreenGrid(final int row, final int column) {
        return isGreen[row][column];
    }


    /**
     * Sets the money value of Arena to money.
     *
     * @param moneyGold the value to set money in Arena to
     */
    public void setMoney(final int moneyGold) {
        if (moneyGold < 0) {
            System.out.println("ERROR: attempt to set Money to negative value. Value of money is unchanged");
        }
        this.money = moneyGold;
    }

    /**
     * Getter for time in the Arena(Frames).
     *
     * @return time in Arena(Frames)
     */
    public int getTime() {
        return time;
    }

    /**
     * Increment the value of time in the Arena by one.
     */
    public void incrementTime() {
        time++;
    }

}
