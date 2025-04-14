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
public class Snake implements Iterable<Segment> {

    private List<Segment> body;

    /**
     * Constructs a new Snake object and initializes its body.
     */
    public Snake() {
        this.body = new LinkedList<Segment>();
        initializeSnake();
    }

    /**
     * Initializes the snake's body with three segments.
     */
    private void initializeSnake() {
        body.add(new Segment(new Point(12, 7), Direction.LEFT));
        body.add(new Segment(new Point(13, 7), Direction.LEFT));
        body.add(new Segment(new Point(14, 7), Direction.LEFT));
    }

    /**
     * Returns the body of the snake as a linked list of segments.
     *
     * @return the body of the snake
     */
    public List<Segment> getBody() {
        return body;
    }

    /**
     * Returns the head of the snake (the first segment in the list).
     *
     * @return The head segment of the snake.
     */
    public Segment head() {
        return body.get(0);
    }

    /**
     * Returns the tail of the snake (the last segment in the list).
     *
     * @return The tail segment of the snake.
     */
    public Segment tail() {
        return body.get(body.size() - 1);
    }

    /**
     * Moves the snake in the specified direction.
     * The head's direction is updated, and each subsequent segment follows the previous one.
     * The tail is removed to simulate movement.
     *
     * @param direction The new direction for the snake's head.
     */
    public void move(Direction direction) {
        Segment head = head();
        head.setDirection(direction);

        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setDirection(body.get(i - 1).getDirection());
        }

        Segment newHead = head.nextSegmentInDirection();
        body.add(0, newHead);
        body.remove(tail());
    }

    /**
     * Grows the snake by adding a new segment to the tail in the direction the tail is facing.
     */
    public void grow() {
        Segment tail = tail();
        Point tailPos = tail.getPosition();
        Direction tailDir = tail.getDirection();

        Point newTailPos = switch (tailDir) {
            case UP -> new Point(tailPos.getX(), tailPos.getY() + 1);
            case DOWN -> new Point(tailPos.getX(), tailPos.getY() - 1);
            case LEFT -> new Point(tailPos.getX() + 1, tailPos.getY());
            case RIGHT -> new Point(tailPos.getX() - 1, tailPos.getY());
        };

        Segment newTail = new Segment(newTailPos, tailDir);
        body.add(newTail);
    }

    /**
     * Checks if the snake can change its direction to the specified direction.
     * The snake cannot do 180° movements.
     *
     * @param direction The direction to check.
     * @return true if the snake can change direction, false otherwise.
     */
    public boolean canChangeDirection(Direction direction) {
        return !head().getDirection().isOpposite(direction);
    }

    /**
     * Checks if the snake will collide with itself on the next move.
     *
     * @return true if the snake will collide with itself, false otherwise.
     */
    public boolean willCollideItself() {
        Point nextHeadPos = head().nextSegmentInDirection().getPosition();

        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).getPosition().equals(nextHeadPos)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns an iterator over the segments of the snake's body.
     * This allows the snake to be used in enhanced for-loops.
     *
     * @return An iterator over the snake's body segments.
     */
    @Override
    public Iterator<Segment> iterator() {
        return body.iterator();
    }
}