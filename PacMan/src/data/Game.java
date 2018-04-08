package data;

import entities.GameObject;
import entities.PacMan;
import entities.Ghost;
import tiled.Map;
import tiled.ObjectLayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Ian Vink
 */

public class Game {

    private static Game instance;

    private Map map;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    private int screenWidth;
    private int screenHeight;

    private Game() {

    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();

        return instance;
    }


    public void setGameObjects() {

        ObjectLayer objLayer = (ObjectLayer) map.getLayers().stream()
                .filter(layer -> layer instanceof ObjectLayer)
                .findFirst()
                .get();


        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource("/textures/pacman.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameObject pacMan = new PacMan(image, objLayer.getStartPosPacMan(), 52, 52);

        gameObjects.add(pacMan);


        image = null;
        try {
            image = ImageIO.read(getClass().getResource("/textures/ghost.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Point startPosGhost : objLayer.getStartPosGhosts()) {
            gameObjects.add(new Ghost(image, startPosGhost, 52, 52));
        }

    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setScreenDimensions(int width, int height) {
        this.screenHeight = height;
        this.screenWidth = width;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
