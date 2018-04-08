package entities.pickups;

import entities.GameObject;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class Pickup extends GameObject {
    public Pickup(BufferedImage image, Point2D position) {
        super(image, position);
    }
}
