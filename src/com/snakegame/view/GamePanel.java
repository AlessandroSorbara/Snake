package com.snakegame.view;

import com.snakegame.model.*;
import com.snakegame.model.Point;
import com.snakegame.view.utils.SpriteLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class GamePanel extends JPanel {

    public static final int TILE_SIZE = 40;
    private static final Color DARK_GREEN = new Color(162, 209, 73);
    private static final Color LIGHT_GREEN = new Color(170, 215, 81);

    private Board board;
    private Map<String, BufferedImage> sprites;

    public GamePanel(Board board) {
        this.board = board;
        this.sprites = SpriteLoader.loadSprites();
        setPreferredSize(new Dimension(680, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);

        drawApple(g, board.getApple());
        drawSnake(g, board.getSnake());
    }

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

    private void drawApple(Graphics g, Apple apple) {
        Point pos = apple.getPosition();
        BufferedImage appleSprite = sprites.get("apple");
        g.drawImage(appleSprite, pos.getX() * TILE_SIZE, pos.getY() * TILE_SIZE, null);
    }

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