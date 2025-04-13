package com.snakegame.model;

public class Board {

    public static final int HEIGHT = 15;
    public static final int WIDTH = 17;
    private Snake snake;
    private Apple apple;
    private GameState gameState;

    public Board(GameState gameState) {
        this.snake = new Snake();
        this.apple = new Apple();
        this.gameState = gameState;
    }

    
}