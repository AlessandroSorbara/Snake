package com.snakegame.model;

import java.util.ArrayList;

public class Snake {

    private ArrayList<Point> snake;
    private Direction direction;
    private boolean alive;

    public Snake(Point head) {
        this.snake = new ArrayList<Point>();
        this.snake.add(head);
        this.snake.add(new Point(head.getX() + 1, head.getY() + 1));
        this.direction = Direction.RIGHT;
        this.alive = true;
    }
}