package application.game.entities.pickups;

import application.game.entities.GameObject;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Pickup extends GameObject implements Serializable {

    private int points;
    public Pickup(BufferedImage image, Point2D position, int objectWidth, int objectHeight, int points, boolean active) {
        super(image, position, objectWidth, objectHeight, active);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

}
