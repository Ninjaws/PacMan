package entities.pickups;

import entities.GameObject;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class Pickup extends GameObject {
    private int score;
    public Pickup(BufferedImage image, Point2D position, int objectWidth, int objectHeight, int score) {
        super(image, position, objectWidth, objectHeight);
        this.score = score;
    }
}
