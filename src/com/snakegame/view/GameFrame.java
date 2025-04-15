package com.snakegame.view;

import com.snakegame.model.Board;
import javax.swing.*;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    public GameFrame(Board model) {
        setTitle("Snake");
        setSize(696, 639);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        this.gamePanel = new GamePanel(model);
        add(gamePanel);

        setVisible(true);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}