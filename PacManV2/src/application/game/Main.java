package application.game;

import application.game.data.Game;
import application.game.presentation.frames.PacManFrame;
import application.game.tiled.Map;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        launchGame();
    }

    public static void launchGame(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int screenWidth = 900;
        int screenHeight = 900;
        Game.getInstance().setScreenDimensions(screenWidth, screenHeight);
        Game.getInstance().setMap(new Map("/game/maps/testMap.json"));
        Game.getInstance().setSounds();
        Game.getInstance().setGameObjects();

        PacManFrame pacManFrame = PacManFrame.getInstance();
    }
}
