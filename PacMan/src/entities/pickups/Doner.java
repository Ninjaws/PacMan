package entities.pickups;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Doner extends Pickup {

    public Doner(BufferedImage image, Point2D position, int objectWidth, int objectHeight) {
        super(image, position, objectWidth, objectHeight, 150);
    }
}
