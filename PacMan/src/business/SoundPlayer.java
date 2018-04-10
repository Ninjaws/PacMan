package business;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.Map;

public class SoundPlayer {

    private Map<Sound, Clip> sounds;

    public SoundPlayer(Map<Sound, Clip> sounds) {
        this.sounds = sounds;
    }

    public Clip getClip(Sound sound) {
        return sounds.get(sound);
    }


    //TODO Remove when StartUpScreen is integrated into the game
    /**
     * The playSound method plays a wav sound.
     *
     * @param fileName is the filename which must be a wav file.
     */
    public static void playSound(String fileName) {
        try {
            File file = new File("resources\\sounds\\" + fileName);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public enum Sound {
        MAIN_MENU, GAME_MUSIC
    }
}
