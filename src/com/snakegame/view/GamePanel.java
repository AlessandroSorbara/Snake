package com.snakegame.view;

import com.snakegame.model.*;
import com.snakegame.model.Point;
import com.snakegame.view.utils.SpriteLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * GamePanel is the visual component responsible for rendering the Snake game board.
 * It draws the background, snake, and apple using sprite images.
 *
 * @author Alessandro Sorbara
 */
public class GamePanel extends JPanel {

    public static final int TILE_SIZE = 40;
    private static final Color DARK_GREEN = new Color(162, 209, 73);
    private static final Color LIGHT_GREEN = new Color(170, 215, 81);

    private Board board;
    private Map<String, BufferedImage> sprites;

    /**
     * Constructs a GamePanel with the specified game model.
     * Loads the sprites and sets the preferred size of the panel.
     *
     * @param model the game model to be rendered
     */
    public GamePanel(Board model) {
        this.board = model;
        this.sprites = SpriteLoader.loadSprites();
        setPreferredSize(new Dimension(680, 600));
    }

    /**
     * Called automatically when the panel is repainted.
     * Delegates drawing tasks to helper methods for background, apple, and snake.
     *
     * @param g the Graphics context to draw on
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        drawApple(g, board.getApple());
        drawSnake(g, board.getSnake());

        if (board.getGameState().isGameOver()) drawGameState(g, "game_over");
        if (board.getGameState().isGameWon()) drawGameState(g, "game_won");
    }

    /**
     * Draws a checkerboard-style green background for the game grid.
     *
     * @param g the Graphics context to draw on
     */
    private void drawBackground(Graphics g) {
        for (int y = 0; y < board.HEIGHT; y++) {
            for (int x = 0; x < board.WIDTH; x++) {
                if ((x + y) % 2 == 0) {
                    g.setColor(LIGHT_GREEN);
                } else {
                    g.setColor(DARK_GREEN);
                }
                g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    /**
     * Draws the apple on the board using its position and sprite.
     *
     * @param g the Graphics context to draw on
     * @param apple the Apple object to render
     */
    private void drawApple(Graphics g, Apple apple) {
        Point pos = apple.getPosition();
        BufferedImage sprite = sprites.get("apple");
        g.drawImage(sprite, pos.getX() * TILE_SIZE, pos.getY() * TILE_SIZE, null);
    }

    /**
     * Draws the snake on the board point by point using appropriate sprites.
     * Handles direction-based sprite selection for head, body turns and tail.
     *
     * @param g the Graphics context to draw on
     * @param snake the Snake object to render
     */
    private void drawSnake(Graphics g, Snake snake) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            Point s = snake.getBody().get(i);
            BufferedImage sprite = null;

            if (i == 0) {
                Point headRelation = Point.sub(snake.getBody().get(1), snake.head());
                if (headRelation.equals(new Point(1, 0))) sprite = sprites.get("head_left");
                else if (headRelation.equals(new Point(-1, 0))) sprite = sprites.get("head_right");
                else if (headRelation.equals(new Point(0, 1))) sprite = sprites.get("head_up");
                else if (headRelation.equals(new Point(0, -1))) sprite = sprites.get("head_down");

            } else if (i == snake.getBody().size() - 1) {
                Point tailRelation = Point.sub(snake.getBody().get(snake.getBody().size() - 2), snake.tail());

                if (tailRelation.equals(new Point(1, 0))) sprite = sprites.get("tail_left");
                else if (tailRelation.equals(new Point(-1, 0))) sprite = sprites.get("tail_right");
                else if (tailRelation.equals(new Point(0, 1))) sprite = sprites.get("tail_up");
                else if (tailRelation.equals(new Point(0, -1))) sprite = sprites.get("tail_down");

            } else {
                Point prev = Point.sub(snake.getBody().get(i + 1), s);
                Point next = Point.sub(snake.getBody().get(i - 1), s);

                if (prev.getY() == next.getY()) sprite = sprites.get("body_horizontal");
                else if (prev.getX() == next.getX()) sprite = sprites.get("body_vertical");

                else if ((prev.getX() == -1 && next.getY() == -1) || (prev.getY() == -1 && next.getX() == -1)) sprite = sprites.get("body_topleft");
                else if ((prev.getX() == -1 && next.getY() == 1) || (prev.getY() == 1 && next.getX() == -1)) sprite = sprites.get("body_bottomleft");
                else if ((prev.getX() == 1 && next.getY() == -1) || (prev.getY() == -1 && next.getX() == 1)) sprite = sprites.get("body_topright");
                else if ((prev.getX() == 1 && next.getY() == 1) || (prev.getY() == 1 && next.getX() == 1)) sprite = sprites.get("body_bottomright");
            }

            g.drawImage(sprite, s.getX() * TILE_SIZE, s.getY() * TILE_SIZE, null);
        }
    }

    private void drawScore(Graphics g, GameState game) {

    }

    private void drawGameState(Graphics g, String state) {
        BufferedImage sprite = sprites.get(state);

        int scaledWidth = 230;
        int scaledHeight = 150;

        int x = (getWidth() - scaledWidth) / 2;
        int y = (getHeight() - scaledHeight) / 2 - 50;

        g.drawImage(sprite, x, y, scaledWidth, scaledHeight, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));

        String message = "Press ENTER to restart";
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(message);
        int textX = (getWidth() - textWidth) / 2;
        int textY = y + 20;

        g.drawString(message, textX, textY);
    }
}