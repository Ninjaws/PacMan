package application.game.entities.pickups;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Coin extends Pickup {
    public Coin(BufferedImage image, Point2D position, int objectWidth, int objectHeight, int points, boolean active) {
        super(image, position, objectWidth, objectHeight, points, active);
    }


}
