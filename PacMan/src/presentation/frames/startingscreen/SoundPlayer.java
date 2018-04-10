package presentation.frames.startingscreen;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class SoundPlayer {
    /**
     * This test runs a audio.
     * @param args
     */
    public static void main(String[] args) {
        SoundPlayer soundPlayer = new SoundPlayer();
        SoundPlayer.playSound("testsound.wav");
    }

    /**
     * This constructor is only needed for a test.
     */
    public SoundPlayer(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Test Sound Clip");
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    /**
     * The playSound method plays a wav sound.
     * @param fileName is the filename which must be a wav file.
     */
    public static void playSound(String fileName){
        try{
            File file = new File("resources\\sounds\\" + fileName);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
