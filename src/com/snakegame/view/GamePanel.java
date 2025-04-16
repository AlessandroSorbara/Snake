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
        BufferedImage appleSprite = sprites.get("apple");
        g.drawImage(appleSprite, pos.getX() * TILE_SIZE, pos.getY() * TILE_SIZE, null);
    }

    /**
     * Draws the snake on the board segment by segment using appropriate sprites.
     * Handles direction-based sprite selection for head, body turns, and tail.
     *
     * @param g the Graphics context to draw on
     * @param snake the Snake object to render
     */
    private void drawSnake(Graphics g, Snake snake) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            Segment s = snake.getBody().get(i);
            Point pos = s.getPosition();
            BufferedImage snakeSegmentSprite = null;

            if (i == snake.getBody().indexOf(snake.head())) {
                switch (s.getDirection()) {
                    case UP -> snakeSegmentSprite = sprites.get("head_up");
                    case DOWN -> snakeSegmentSprite = sprites.get("head_down");
                    case LEFT -> snakeSegmentSprite = sprites.get("head_left");
                    case RIGHT -> snakeSegmentSprite = sprites.get("head_right");
                }
            } else if (i == snake.getBody().indexOf(snake.tail())) {
                switch (s.getDirection()) {
                    case UP -> snakeSegmentSprite = sprites.get("tail_down");
                    case DOWN -> snakeSegmentSprite = sprites.get("tail_up");
                    case LEFT -> snakeSegmentSprite = sprites.get("tail_right");
                    case RIGHT -> snakeSegmentSprite = sprites.get("tail_left");
                }
            } else {
                Direction prev = snake.getBody().get(i - 1).getDirection();
                Direction next = snake.getBody().get(i + 1).getDirection();

                if ((prev.equals(Direction.RIGHT) && next.equals(Direction.DOWN)) || (prev.equals(Direction.UP) && next.equals(Direction.LEFT))) {
                    snakeSegmentSprite = sprites.get("body_bottomleft");
                } else if ((prev.equals(Direction.LEFT) && next.equals(Direction.DOWN)) || (prev.equals(Direction.UP) && next.equals(Direction.RIGHT))) {
                    snakeSegmentSprite = sprites.get("body_bottomright");
                } else if ((prev.equals(Direction.DOWN) && next.equals(Direction.LEFT)) || (prev.equals(Direction.RIGHT) && next.equals(Direction.UP))) {
                    snakeSegmentSprite = sprites.get("body_topleft");
                } else if ((prev.equals(Direction.DOWN) && next.equals(Direction.RIGHT)) || (prev.equals(Direction.LEFT) && next.equals(Direction.UP))) {
                    snakeSegmentSprite = sprites.get("body_topright");
                } else if ((prev.equals(Direction.LEFT) && next.equals(Direction.LEFT)) || (prev.equals(Direction.RIGHT) && next.equals(Direction.RIGHT))) {
                    snakeSegmentSprite = sprites.get("body_horizontal");
                } else if ((prev.equals(Direction.UP) && next.equals(Direction.UP)) || (prev.equals(Direction.DOWN) && next.equals(Direction.DOWN))) {
                    snakeSegmentSprite = sprites.get("body_vertical");
                }
            }

            g.drawImage(snakeSegmentSprite, pos.getX() * TILE_SIZE, pos.getY() * TILE_SIZE, null);
        }
    }
}