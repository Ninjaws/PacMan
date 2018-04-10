import data.Game;
import presentation.frames.PacManFrame;
import tiled.Map;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int screenWidth = 900;
        int screenHeight = 900;
        Game.getInstance().setScreenDimensions(screenWidth, screenHeight);
        Game.getInstance().setMap(new Map("/maps/testMap.json"));
        Game.getInstance().setSounds();
        Game.getInstance().setGameObjects();

        PacManFrame pacManFrame = new PacManFrame();
    }
}
