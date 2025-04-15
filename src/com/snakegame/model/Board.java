package com.snakegame.model;

import java.util.Random;

/**
 * Represents the game board for the Snake game.
 * It handles the snake's movement, apple spawning, boundary checks, and game state management.
 * The board ensures the game logic runs smoothly, including collision detection, scoring, and resetting the game.
 *
 * @author Alessandro Sorbara
 */
public class Board {

    public static final int HEIGHT = 15;
    public static final int WIDTH = 17;

    private Snake snake;
    private Apple apple;
    private GameState gameState;

    /**
     * Constructs a new Board object.
     * Initializes the snake, apple, and game state.
     */
    public Board() {
        this.snake = new Snake();
        this.apple = new Apple();
        this.gameState = new GameState();
    }

    /**
     * Returns the snake.
     *
     * @return the snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Returns the apple.
     *
     * @return the apple
     */
    public Apple getApple() {
        return apple;
    }

    /**
     * Returns the game state.
     *
     * @return game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Checks if a given position is outside the bounds of the game board.
     *
     * @param position the position to check
     * @return true if the position is out of bounds, false otherwise
     */
    private boolean isOutOfBounds(Point position) {
        return position.getX() < 0 || position.getX() >= WIDTH || position.getY() < 0 || position.getY() >= HEIGHT;
    }

    /**
     * Checks if the snake's head is about to collide with the boundary.
     * If a collision is detected, the game is over.
     */
    private void checkBoundaryCollision() {
        if (isOutOfBounds(snake.head().getPosition())) {
            gameState.setGameOver();
        }
    }

    /**
     * Checks if a given position is occupied by either the snake or the apple.
     *
     * @param position the position to check
     * @return true if the position is occupied, false otherwise
     */
    private boolean isPositionOccupied(Point position) {
        if (position.equals(apple.getPosition())) return true;
        for (Segment s : snake) {
            if (position.equals(s.getPosition())) return true;
        }
        return false;
    }

    /**
     * Spawns a new apple at a random position on the board that is not occupied.
     */
    private void spawnApple() {
        Random random = new Random();
        Point newPosition;

        do {
            int x = random.nextInt(Board.WIDTH);
            int y = random.nextInt(Board.HEIGHT);
            newPosition = new Point(x, y);
        } while(isPositionOccupied(newPosition));

        apple.setPosition(newPosition);
    }

    /**
     * Moves the snake in the specified direction.
     * Checks for apple collision, self-collision, and boundary collisions.
     * If the snake eats an apple, it grows and the score is incremented.
     * If the snake collides with itself or the boundary, the game ends.
     *
     * @param direction the direction to move the snake
     */
    public void moveSnake(Direction direction) {
        if (gameState.isGameOver()) {
            return;
        }

        snake.move(direction);

        if (snake.head().getPosition().equals(apple.getPosition())) {
            gameState.incrementScore();
            snake.grow();
            spawnApple();
        }

        if (snake.willCollideItself()) {
            gameState.setGameOver();
        }

        checkBoundaryCollision();
    }

    /**
     * Resets the board, reinitializing the snake, apple, and game state.
     */
    public void resetBoard() {
        snake = new Snake();
        apple = new Apple();
        gameState.reset();
    }
}