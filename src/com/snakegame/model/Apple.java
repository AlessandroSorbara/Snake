package com.snakegame.model;

import java.util.Random;

/**
 * Represents an apple on the game board.
 * The apple can be eaten by the snake, and its position is tracked using a Point.
 *
 * @author Alessandro Sorbara
 */
public class Apple {

    private Point position;

    private static final Random RANDOM = new Random();

    /**
     * Constructs a new apple at the specified position.
     *
     * @param position the position of the apple on the game board
     */
    public Apple(Point position) {
        this.position = position;
    }

    /**
     * Returns the position of the apple on the game board.
     *
     * @return the position of the apple
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets a new position for the apple on the game board.
     *
     * @param position the new position of the apple
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    private boolean isPositionOccupied(Point point) {
        // TODO
        return false;
    }

    public void relocate() {
        // TODO
    }
}