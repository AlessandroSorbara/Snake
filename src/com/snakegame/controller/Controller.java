package com.snakegame.controller;

import com.snakegame.model.Board;
import com.snakegame.model.Direction;
import com.snakegame.view.GameFrame;
import javax.swing.*;
import java.awt.event.*;

public class Controller implements KeyListener, ActionListener {

    private static final int DELAY = 100;

    private final Board model;
    private final GameFrame view;
    private final Timer timer;
    private boolean gameStarted;
    private Direction currentDirection;

    public Controller(Board model, GameFrame view) {
        this.model = model;
        this.view = view;
        this.gameStarted = false;
        currentDirection = Direction.LEFT;

        view.addKeyListener(this);
        JPanel panel = view.getGamePanel();
        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        timer = new Timer(DELAY, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.moveSnake(currentDirection);
        view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameStarted) {
            timer.start();
            gameStarted = true;
        }

        Direction newDirection = currentDirection;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP:
                if (currentDirection != Direction.DOWN) newDirection = Direction.UP;
                break;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                if (currentDirection != Direction.UP) newDirection = Direction.DOWN;
                break;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                if (currentDirection != Direction.RIGHT) newDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                if (currentDirection != Direction.LEFT) newDirection = Direction.RIGHT;
                break;
        }

        if (newDirection != currentDirection) {
            currentDirection = newDirection;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}