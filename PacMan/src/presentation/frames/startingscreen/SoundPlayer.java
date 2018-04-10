package presentation.frames.startingscreen;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class SoundPlayer {
    public static void main(String[] args) {

        SoundPlayer soundPlayer = new SoundPlayer();
        SoundPlayer.playSound("testsound.wav");
    }

    public SoundPlayer(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Test Sound Clip");
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

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
