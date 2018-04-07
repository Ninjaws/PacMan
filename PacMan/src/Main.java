import data.Game;
import presentation.frames.PacManFrame;
import tiled.Map;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }


        Game.getInstance().setMap(new Map("/maps/testMap.json"));

        PacManFrame pacManFrame = new PacManFrame();
    }
}
