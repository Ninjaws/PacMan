package data;

import entities.GameObject;
import entities.PacMan;
import tiled.Map;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
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
        addGameObjects();
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();

        return instance;
    }


    private void addGameObjects() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource("/textures/pacman.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameObject pacMan = new PacMan(image, 52,52, new Point2D.Double(10*32,12*32));

        gameObjects.add(pacMan);
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
