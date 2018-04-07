package entities;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    private BufferedImage image;

    public GameObject(BufferedImage image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, new AffineTransform(), null);
    }
}
