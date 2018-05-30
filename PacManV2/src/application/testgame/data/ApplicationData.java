package application.testgame.data;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationData implements Serializable {

    private List<GameObject> gameObjects;

    public ApplicationData() {
        gameObjects = new ArrayList<>();

        BufferedImage playerImage = null;
        try {
            playerImage = ImageIO.read(getClass().getResource("/game/textures/donerSprite.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        gameObjects.add(new GameObject(playerImage, new Point2D.Double(32, 32), new Point2D.Double(50, 50),"1"));
        gameObjects.add(new GameObject(playerImage, new Point2D.Double(32, 32), new Point2D.Double(300, 300),"2"));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameObject getGameObject(String objectName) {
        return gameObjects.stream()
                .filter(gameobject -> gameobject.getPlayerName().equals(objectName))
                .findFirst()
                .orElse(null);

    }
}
