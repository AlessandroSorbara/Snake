package com.snakegame;

import com.snakegame.view.*;
import com.snakegame.controller.*;
import com.snakegame.model.Board;

public class SnakeGame {

    public static void main(String[] args) {

        Board model = new Board();
        GameFrame view = new GameFrame(model);
        Controller controller = new Controller(model, view);
    }
}