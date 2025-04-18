package com.snakegame.model;

import com.snakegame.view.utils.SoundManager;

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

    private SoundManager soundManager;

    /**
     * Constructs a new Board object.
     * Initializes the snake, apple, and game state.
     */
    public Board() {
        this.snake = new Snake();
        this.apple = new Apple();
        this.gameState = new GameState();

        soundManager = new SoundManager();
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
     * Checks if the head of the snake hits the body or if it
     * is out of the game boundaries, ending the game.
     */
    private void checkFail() {
        if (snake.head().getX() < 0 || snake.head().getX() >= WIDTH || snake.head().getY() < 0 || snake.head().getY() >= HEIGHT) gameState.lose();
        for (int i = 1; i < snake.getBody().size(); i++) if (snake.getBody().get(i).equals(snake.head())) gameState.lose();
    }

    /**
     * Checks if a given position is occupied by either the snake or the apple.
     *
     * @param position the position to check
     * @return true if the position is occupied, false otherwise
     */
    private boolean isPositionOccupied(Point position) {
        if (position.equals(apple.getPosition())) return true;
        for (Point s : snake) if (position.equals(s)) return true;
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
     * If the snake covers all the board, the game has been won.
     *
     * @param direction the direction to move the snake
     */
    public void moveSnake(Direction direction) {
        snake.move(direction);

        if (snake.head().equals(apple.getPosition())) {
            int clip = new Random().nextInt(18) + 1;
            soundManager.playSound(String.valueOf(clip) + ".wav");
            snake.grow();
            gameState.incrementScore();
            spawnApple();
        }

        checkFail();
    }

    /**
     * Resets the board, reinitializing the snake, apple, and game state.
     */
    public void resetBoard() {
        snake.initializeSnake();
        apple = new Apple();
        gameState.reset();
    }
}