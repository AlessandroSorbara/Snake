package com.snakegame.view;

import com.snakegame.model.Board;
import javax.swing.*;

public class GameWindow extends JFrame {

    private GamePanel gamePanel;

    public GameWindow() {
        setTitle("Snake");
        setSize(696, 639);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Board board = new Board();
        gamePanel = new GamePanel(board);
        add(gamePanel);

        setVisible(true);
    }
}