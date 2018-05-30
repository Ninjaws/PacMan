package application.game.business;

import javax.sound.sampled.Clip;
import java.util.Map;

public class SoundPlayer {

    private Map<Sound, Clip> sounds;

    public SoundPlayer(Map<Sound, Clip> sounds) {
        this.sounds = sounds;
    }

    public Clip getClip(Sound sound) {
        return sounds.get(sound);
    }


    public enum Sound {
        MAIN_MENU, GAME_MUSIC, PACMAN_MOVEMENT,
    }
}
