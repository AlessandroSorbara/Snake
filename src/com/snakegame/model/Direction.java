package com.snakegame.model;

/**
 * Represents the four cardinal directions a segment or snake can face or move in.
 *
 * @author Alessandro Sorbara
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    /**
     * Determines whether the given direction is opposite to this direction.
     * For example, UP is opposite to DOWN, and LEFT is opposite to RIGHT.
     *
     * @param other the direction to compare with this one
     * @return true if the given direction is opposite to this one; false otherwise
     */
    public boolean isOpposite(Direction other) {
        return switch (this) {
            case UP -> other == DOWN;
            case DOWN -> other == UP;
            case LEFT -> other == RIGHT;
            case RIGHT -> other == LEFT;
        };
    }
}