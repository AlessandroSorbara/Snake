package com.snakegame.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the snake.
 * It handles the snake's movement, growth, direction changes, and collision checks.
 *
 * @author Alessandro Sorbara
 */
public class Snake implements Iterable<Point> {

    private List<Point> body;
    private boolean grows;

    /**
     * Constructs a new Snake object and initializes its body.
     */
    public Snake() {
        this.body = new LinkedList<Point>();
        initializeSnake();
        grows = false;
    }

    /**
     * Initializes the snake's body with three segments.
     */
    private void initializeSnake() {
        body.add(new Point(12, 7));
        body.add(new Point(13, 7));
        body.add(new Point(14, 7));
    }

    /**
     * Returns the body of the snake as a linked list of points.
     *
     * @return the body of the snake
     */
    public List<Point> getBody() {
        return body;
    }

    /**
     * Returns the head of the snake (the first point in the list).
     *
     * @return The head point of the snake.
     */
    public Point head() {
        return body.get(0);
    }

    /**
     * Returns the tail of the snake (the last point in the list).
     *
     * @return The tail segment of the snake.
     */
    public Point tail() {
        return body.get(body.size() - 1);
    }

    /**
     * Moves the snake in the specified direction.
     * If the snake is set to grow, it adds a new head segment in the given direction
     * without removing the tail. Otherwise, it removes the tail to maintain the same length.
     *
     * @param direction the direction in which to move the snake.
     */
    public void move(Direction direction) {
        Point delta = switch (direction) {
            case UP -> new Point(0, -1);
            case DOWN -> new Point(0, 1);
            case LEFT -> new Point(-1, 0);
            case RIGHT -> new Point(1, 0);
        };

        Point newHead = Point.add(head(), delta);

        if (!grows) body.remove(tail());
        else grows = false;

        body.add(0, newHead);
    }

    /**
     * Sets the snake to grow on the next move.
     * This is typically called when the snake eats food, causing its length to increase.
     */
    public void grow() {
        grows = true;
    }

    /**
     * Returns an iterator over the segments of the snake's body.
     * This allows the snake to be used in enhanced for-loops.
     *
     * @return An iterator over the snake's body segments.
     */
    @Override
    public Iterator<Point> iterator() {
        return body.iterator();
    }
}