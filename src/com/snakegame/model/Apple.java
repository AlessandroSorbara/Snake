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
     * Constructs the first apple in a default position.
     */
    public Apple() {
        this.position = new Point(2,7);
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
}