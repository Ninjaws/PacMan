package entities;

import data.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * @author Ian Vink
 */

public abstract class GameObject {
    private BufferedImage image;
    private Point2D position;

    private int objectWidth;
    private int objectHeight;

    private boolean active;


    /**
     * Constructs the object
     * @param image The image of the object
     * @param position The position of the object
     * @param objectWidth The width of the object
     * @param objectHeight The height of the object
     */
    public GameObject(BufferedImage image, Point2D position, int objectWidth, int objectHeight, boolean active) {
        this.image = image;
        this.position = position;

        this.objectWidth = objectWidth;
        this.objectHeight = objectHeight;

        this.active = active;
    }

    public void draw(Graphics2D g2d) {
        if(!active)
        return;

        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.scale((double) objectWidth / image.getWidth(), (double) objectHeight / image.getHeight());
        g2d.drawImage(image, at, null);
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getPosition() {
        return position;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
