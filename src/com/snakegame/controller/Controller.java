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
    private Direction inputDirection;

    public Controller(Board model, GameFrame view) {
        this.model = model;
        this.view = view;
        inputDirection = Direction.LEFT;

        view.addKeyListener(this);
        JPanel panel = view.getGamePanel();
        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        timer = new Timer(DELAY, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.moveSnake(inputDirection);
        view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!model.getGameState().isGameStarted()) {
            timer.start();
            model.getGameState().start();
        }

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
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}