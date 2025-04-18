package com.snakegame.model;

/**
 * The GameState class represents the state of the game, including the current score and whether the game is over.
 * It provides methods to manipulate and retrieve the score, as well as to check and update the game over status.
 *
 * @author Alessandro Sorbara
 */
public class GameState {

    private int score;
    private boolean gameStarted;
    private boolean gameOver;
    private boolean gameWon;

    /**
     * Constructs a new GameState with an initial score of 0 and gameOver set to false.
     */
    public GameState() {
        this.score = 0;
        this.gameStarted = false;
        this.gameOver = false;
        this.gameWon = false;
    }

    /**
     * Returns the current score of the game.
     *
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Increments the score by 1.
     * This method is called when the snake eats an apple.
     */
    public void incrementScore() {
        score++;
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }


    /**
     * Checks if the game is won.
     *
     * @return true if the game is won, false otherwise
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Checks if the game is started.
     *
     * @return true if the game is started, false otherwise
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Sets the game started flag to true, indicating that the game is started.
     */
    public void start() {
        gameStarted = true;
    }

    /**
     * Sets the game over flag to true, indicating that the game has ended and the player lost.
     */
    public void lose() {
        gameOver = true;
    }

    /**
     * Sets the game won flag to true, indicating that the game has ended and the player won.
     */
    public void win() {
        gameWon = true;
    }

    /**
     * Resets the game state, setting the score to 0, gameOver and gameWon to false.
     * This method is useful for starting a new game.
     */
    public void reset() {
        this.score = 0;
        this.gameOver = false;
        this.gameWon = false;
    }
}