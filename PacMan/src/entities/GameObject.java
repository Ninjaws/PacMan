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

    public GameObject(BufferedImage image, Point2D position) {
        this.image = image;
        this.position = position;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.scale((double) Game.getInstance().getMap().getTileWidth() / image.getWidth(), (double) Game.getInstance().getMap().getTileHeight() / image.getHeight());
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
}
