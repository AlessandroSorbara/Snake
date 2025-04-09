package com.snakegame.model;

/**
 * Represents a single segment of the snake, including its position and direction.
 * Useful for modeling the snake's head, body, and tail.
 *
 * @author Alessandro Sorbara
 */
public class Segment {

    private Point position;
    private Direction direction;

    /**
     * Constructs a new segment at the specified position and direction.
     *
     * @param x the x-coordinate of the segment
     * @param y the y-coordinate of the segment
     * @param direction the direction this segment is facing
     */
    public Segment(Point position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    /**
     * Returns the position of the segment.
     *
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets the position of the segment.
     *
     * @param position the new position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Returns the direction of the segment.
     *
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the segment.
     *
     * @param direction the new direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Returns a new Segment representing where this segment would move
     * if it takes one step in its current direction.
     *
     * @return the next segment position
     */
    public Segment nextSegmentInDirection() {
        return switch (direction) {
            case UP -> new Segment(new Point(position.getX(), position.getY() - 1), direction);
            case DOWN -> new Segment(new Point(position.getX(), position.getY() + 1), direction);
            case LEFT -> new Segment(new Point(position.getX() - 1, position.getY() - 1), direction);
            case RIGHT -> new Segment(new Point(position.getX() + 1, position.getY() - 1), direction);
        };
    }
}