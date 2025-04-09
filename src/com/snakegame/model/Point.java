package com.snakegame.model;

/**
 * Represents a 2D point or position on the game board.
 * Used for storing the position of segments, apples, or any object in the game world.
 *
 * @author Alessandro Sorbara
 */
public class Point {

    private final int x;
    private final int y;

    /**
     * Constructs a new point with the given x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the point.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Checks if this point is equal to another point.
     * Two points are equal if their x and y coordinates are the same.
     *
     * @param obj the object to compare to
     * @return true if the points are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point p)) return false;
        return x == p.x && y == p.y;
    }
}