package data;

import business.Recoloring;
import business.SoundPlayer;
import business.SpriteSheet;
import com.sun.scenario.Settings;
import entities.active_objects.ActiveGameObject;
import entities.GameObject;
import entities.active_objects.PacMan;
import entities.active_objects.Ghost;
import sun.audio.AudioPlayer;
import tiled.Map;
import tiled.ObjectLayer;
import tiled.TileLayer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ian Vink
 */

public class Game {

    private static Game instance;

    private Map map;

    private SoundPlayer soundPlayer;

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

        java.util.Map<SpriteSheet.Animation, Integer> pacManAnimations = new HashMap<>();
        pacManAnimations.put(SpriteSheet.Animation.MOVE_UP, 2);
        pacManAnimations.put(SpriteSheet.Animation.MOVE_LEFT, 1);
        pacManAnimations.put(SpriteSheet.Animation.MOVE_DOWN, 3);
        pacManAnimations.put(SpriteSheet.Animation.MOVE_RIGHT, 0);

        GameObject pacMan = new PacMan(Recoloring.colorImage(image, Color.YELLOW), objLayer.getStartPosPacMan(), 25, 25,
                52, 52, pacManAnimations, 75, 0.17);

        gameObjects.add(pacMan);


        image = null;
        try {
            image = ImageIO.read(getClass().getResource("/textures/ghostSpriteSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage deadImage = null;
        try {
            deadImage = ImageIO.read(getClass().getResource("/textures/ghostEyesSpriteSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Color[] ghostColors = {Color.RED, Color.PINK, Color.CYAN, Color.ORANGE};

        java.util.Map<SpriteSheet.Animation, Integer> ghostAnimations = new HashMap<>();
        ghostAnimations.put(SpriteSheet.Animation.MOVE_UP, 2);
        ghostAnimations.put(SpriteSheet.Animation.MOVE_LEFT, 0);
        ghostAnimations.put(SpriteSheet.Animation.MOVE_DOWN, 3);
        ghostAnimations.put(SpriteSheet.Animation.MOVE_RIGHT, 1);

        for (int i = 0; i < objLayer.getStartPosGhosts().size(); i++) {

            BufferedImage recoloredImage = Recoloring.colorImage(image, ghostColors[i]);
            BufferedImage combined = new BufferedImage(recoloredImage.getWidth(), recoloredImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = combined.createGraphics();
            g2d.drawImage(recoloredImage, new AffineTransform(), null);
            g2d.drawImage(deadImage, new AffineTransform(), null);

            g2d.dispose();

            gameObjects.add(new Ghost(combined, deadImage, objLayer.getStartPosGhosts().get(i),
                    25, 25, 56, 56, ghostAnimations, 100, 0.5));
        }
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public void setSounds() {

        java.util.Map<SoundPlayer.Sound, Clip> sounds = new HashMap<>();

        sounds.put(SoundPlayer.Sound.MAIN_MENU, getClip("/sounds/testSound.wav", 0.5f));
        sounds.put(SoundPlayer.Sound.GAME_MUSIC, getClip("/sounds/pacman_gamemusic.wav", 0.03f));
        sounds.put(SoundPlayer.Sound.PACMAN_MOVEMENT, getClip("/sounds/pacman_eatingsound.wav", 0.6f));

        soundPlayer = new SoundPlayer(sounds);
    }

    private Clip getClip(String soundName, float volume) {
        try {
            File file = new File(getClass().getResource(soundName).toURI());
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);

            FloatControl clipVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clipVolume.setValue(floatToDecibel(volume));
            return clip;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    /**
     * Converts a value from 0 to 1 to decibels
     *
     * @param volume A value from 0 to 1
     * @return The amount of decibels that match with the given number
     */
    private float floatToDecibel(float volume) {
        if (volume < 0)
            volume = 0;
        else if (volume > 1)
            volume = 1;

        float decibelVolume = 20f * (float) Math.log10(volume);

        return decibelVolume;
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

    public List<ActiveGameObject> getActiveGameObjects() {
        return gameObjects.stream()
                .filter(object -> object instanceof ActiveGameObject)
                .map(activeobj -> (ActiveGameObject) activeobj)
                .collect(Collectors.toList());
    }

    public PacMan getPacMan() {
        return gameObjects.stream()
                .filter(object -> object instanceof PacMan)
                .map(pacman -> (PacMan) pacman)
                .findFirst()
                .orElse(null);
    }

    public List<Ghost> getGhosts() {
        return gameObjects.stream()
                .filter(object -> object instanceof Ghost)
                .map(ghost -> (Ghost) ghost)
                .collect(Collectors.toList());
    }


    public TileLayer getDataLayer() {
        return map.getLayers().stream()
                .filter(layer -> layer instanceof TileLayer)
                .map(tilelayer -> (TileLayer) tilelayer)
                .filter(tileLayer -> tileLayer.getName().equals("data"))
                .findFirst()
                .orElse(null);
    }

    private ObjectLayer getObjectsLayer() {
        return map.getLayers().stream()
                .filter(layer -> layer instanceof ObjectLayer)
                .map(objectlayer -> (ObjectLayer) objectlayer)
                .filter(objectlayer -> objectlayer.getName().equals("objects layer"))
                .findFirst()
                .orElse(null);
    }

    public Loop getLoop(Point mapPos) {
        return getObjectsLayer().getLoops().stream()
                .filter(loop -> loop.getEntrance().equals(mapPos) || loop.getExit().equals(mapPos))
                .findFirst()
                .orElse(null);
    }

    public List<Point> getScatterCorners() {
        return getObjectsLayer().getScatterCorners();
    }
}
