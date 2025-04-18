package com.snakegame.controller;

import com.snakegame.model.Board;
import com.snakegame.model.Direction;
import com.snakegame.model.Snake;
import com.snakegame.view.GameFrame;
import com.snakegame.view.utils.SoundManager;
import javax.swing.*;
import java.awt.event.*;

/**
 * Controller class that handles keyboard input and updates the game loop for the Snake game.
 * It listens for key presses to change the snake's direction and uses a timer to move the snake periodically.
 *
 * @author Alessandro Sorbara
 */
public class Controller implements KeyListener, ActionListener {

    private static final int DELAY = 100;
    private final Timer timer;

    private final SoundManager soundManager;

    private final Board model;
    private final GameFrame view;

    private Direction inputDirection;
    private boolean directionChanged = false;

    /**
     * Creates a new Controller with the given game model and view.
     * Initializes key listeners and prepares the game timer.
     *
     * @param model the game model containing logic and state
     * @param view the game view responsible for rendering
     */
    public Controller(Board model, GameFrame view) {
        this.model = model;
        this.view = view;
        inputDirection = null;

        view.addKeyListener(this);
        JPanel panel = view.getGamePanel();
        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        timer = new Timer(DELAY, this);
        soundManager = new SoundManager();
    }

    /**
     * Called periodically by the timer to advance the game state.
     * Moves the snake and repaints the game view.
     * Also checks if the game ended, producing a sound effect.
     *
     * @param e the ActionEvent triggered by the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.getGameState().isGameOver()) {
            timer.stop();
            soundManager.playSound("game_over.wav");
        }

        view.repaint();
        model.moveSnake(inputDirection);
        directionChanged = false;
    }

    /**
     * Handles key presses to start the game or change the snake's direction.
     * Prevents reversal into the opposite direction.
     *
     * @param e the KeyEvent representing the key press
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState().isGameOver()) {
            model.resetBoard();
            inputDirection = Direction.LEFT;
            timer.start();
            return;
        }

        if (!model.getGameState().isGameStarted()) {
            timer.start();
            model.getGameState().start();
        }

        if (directionChanged) return;
        Direction newDirection = inputDirection;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP:
                if (inputDirection != Direction.DOWN) newDirection = Direction.UP;
                break;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                if (inputDirection != Direction.UP) newDirection = Direction.DOWN;
                break;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                if (inputDirection != Direction.RIGHT) newDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                if (inputDirection != Direction.LEFT) newDirection = Direction.RIGHT;
                break;
        }

        if (newDirection != inputDirection) {
            inputDirection = newDirection;
            directionChanged = true;
        }
    }

    /**
     * Unused method required by the KeyListener interface.
     *
     * @param e the KeyEvent
     */
    @Override public void keyReleased(KeyEvent e) {}

    /**
     * Unused method required by the KeyListener interface.
     *
     * @param e the KeyEvent
     */
    @Override public void keyTyped(KeyEvent e) {}
}