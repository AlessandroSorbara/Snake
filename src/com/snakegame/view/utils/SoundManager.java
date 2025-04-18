package com.snakegame.view.utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Utility class for handling and playing WAV sound effects in the Snake game.
 *
 * @author Alessandro Sorbara
 */
public class SoundManager {

    /**
     * Plays a sound file located in the sounds/ directory of the resources.
     *
     * @param soundName the filename of the sound to play (e.g. "crunch.wav")
     */
    public void playSound(String soundName) {
        try {
            URL soundURL = getClass().getResource("/sounds/" + soundName);
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
}