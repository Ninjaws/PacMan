package application.testgame.data;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class GameObject {

    private BufferedImage image;
    private Point2D position;
    private Point2D objectSize;

    public GameObject(BufferedImage image, Point2D objectSize, Point2D position) {
        this.image = image;
        this.objectSize = objectSize;
        this.position = position;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.scale(objectSize.getX() / image.getWidth(), objectSize.getY() / image.getHeight());
        g2d.drawImage(image, at, null);
    }
}
