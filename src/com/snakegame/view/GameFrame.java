package com.snakegame.view;

import com.snakegame.model.Board;
import javax.swing.*;

/**
 * GameFrame is the main window for the Snake game.
 * It initializes and displays the GamePanel.
 *
 * @author Alessandro Sorbara
 */
public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    /**
     * Constructs the main game window with a fixed size, title, and centered position.
     * Adds the GamePanel to the frame and makes it visible.
     *
     * @param model the game model to be rendered in the GamePanel
     */
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

    /**
     * Returns the GamePanel associated with this frame.
     *
     * @return the GamePanel instance
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}