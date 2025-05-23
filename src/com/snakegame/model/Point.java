package com.snakegame.model;

/**
 * Represents a 2D point or position on the game board.
 * Used for storing the position of segments, apples, or any object in the game world.
 *
 * @author Alessandro Sorbara
 */
public class Point {

    private int x;
    private int y;

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
     * Utility method that returns a new point that represents
     * the sum of two given points given in input.
     * Very usefull in determining snake movement.
     *
     * @param p1 first point
     * @param p2 second point
     * @return the points sum
     */
    public static Point add(Point p1, Point p2) {
        return new Point(p1.getX() + p2.getX(), p1.getY() + p2.getY());
    }

    /**
     * Utility method that returns a new point that represents
     * the subtraction of two given points given in input.
     * Very usefull in determining snake segments direction.
     *
     * @param p1 first point
     * @param p2 second point
     * @return the points subtraction
     */
    public static Point sub(Point p1, Point p2) {
        return new Point(p1.getX() - p2.getX(), p1.getY() - p2.getY());
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
        return x == p.getX() && y == p.getY();
    }
}