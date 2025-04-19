package com.snakegame.view.utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Utility class for handling and playing WAV sound effects in the Snake game.
 *
 * @author Alessandro Sorbara
 */
public class SoundManager {

    /**
     * Plays a sound file.
     *
     * @param soundName the filename of the sound to play
     * @param brainrot activates brainrot sounds if true
     */
    public void playSound(String soundName, boolean brainrot) {
        try {
            URL soundURL = getSound(soundName, brainrot);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + soundName);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the URL of a sound from the Resources Root.
     *
     * @param soundName the filename of the sound to play
     * @param brainrot activates brainrot sounds if true
     */
    private URL getSound(String soundName, boolean brainrot) {
        if (!brainrot) return getClass().getResource("/sounds/" + soundName);
        else return getClass().getResource("/sounds/brainrot/" + String.valueOf(new Random().nextInt(20) + 1) + ".wav");
    }
}