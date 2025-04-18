package com.snakegame.view.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for loading and managing sprite images used in the Snake game.
 * This class provides a static method to load all the required sprites from the
 * resources directory and return them as a map for easy access by name.
 *
 * @author Alessandro Sorbara
 */
public class SpriteLoader {

    /**
     * Loads all the required sprite images from the resources.
     *
     * @return a map containing the names and corresponding BufferedImage objects of the sprites
     */
    public static Map<String, BufferedImage> loadSprites() {

        Map<String, BufferedImage> map = new HashMap<>();

        try {
            map.put("apple", loadImage("/sprites/apple.png"));
            map.put("body_bottomleft", loadImage("/sprites/body_bottomleft.png"));
            map.put("body_bottomright", loadImage("/sprites/body_bottomright.png"));
            map.put("body_horizontal", loadImage("/sprites/body_horizontal.png"));
            map.put("body_topleft", loadImage("/sprites/body_topleft.png"));
            map.put("body_topright", loadImage("/sprites/body_topright.png"));
            map.put("body_vertical", loadImage("/sprites/body_vertical.png"));
            map.put("head_down", loadImage("/sprites/head_down.png"));
            map.put("head_left", loadImage("/sprites/head_left.png"));
            map.put("head_right", loadImage("/sprites/head_right.png"));
            map.put("head_up", loadImage("/sprites/head_up.png"));
            map.put("tail_down", loadImage("/sprites/tail_down.png"));
            map.put("tail_left", loadImage("/sprites/tail_left.png"));
            map.put("tail_right", loadImage("/sprites/tail_right.png"));
            map.put("tail_up", loadImage("/sprites/tail_up.png"));
            map.put("game_over", loadImage("/sprites/lost.png"));
            map.put("game_won", loadImage("/sprites/won.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * Loads a single image from the given resource path.
     *
     * @param path the resource path of the image to load (e.g., "/sprites/head_up.png")
     * @return the loaded BufferedImage
     * @throws IOException if the image cannot be read or the resource is not found
     */
    private static BufferedImage loadImage(String path) throws IOException {
        InputStream stream = SpriteLoader.class.getResourceAsStream(path);

        if (stream == null) {
            throw new FileNotFoundException("Sprite not found: " + path);
        }

        return ImageIO.read(stream);
    }
}