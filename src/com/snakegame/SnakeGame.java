package com.snakegame;

import com.snakegame.view.*;
import com.snakegame.controller.*;
import com.snakegame.model.Board;

/**
 * Entry point for the Snake game.
 * Initializes the model, view, and controller components.
 */
public class SnakeGame {

    /**
     * The main method to launch the Snake game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Board model = new Board();
        GameFrame view = new GameFrame(model);
        Controller controller = new Controller(model, view);
    }
}