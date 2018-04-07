package entities;

import data.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    private BufferedImage image;
    private Point2D position;

    public GameObject(BufferedImage image) {
        this.image = image;
        position = new Point2D.Double(0, 0);
    }

    public void draw(Graphics2D g2d) {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.scale((double)Game.getInstance().getMap().getTileWidth()/image.getWidth(),(double)Game.getInstance().getMap().getTileHeight()/image.getHeight());
       /*
        at.scale((double) Game.getInstance().getScreenWidth() / (Game.getInstance().getMap().getMapWidth() * Game.getInstance().getMap().getTileWidth()),
                (double) Game.getInstance().getScreenHeight() / (Game.getInstance().getMap().getMapHeight() * Game.getInstance().getMap().getTileHeight()));
     */
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
