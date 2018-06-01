package application.game.entities.pickups;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Coin extends Pickup implements Serializable {
    public Coin(BufferedImage image, Point2D position, int objectWidth, int objectHeight, int points, boolean active) {
        super(image, position, objectWidth, objectHeight, points, active);
    }


}
