package entities.active_objects;

import business.SpriteSheet;
import entities.GameObject;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ian Vink
 */

public abstract class ActiveGameObject extends GameObject {

    private BufferedImage spriteSheetImage;
    private int spriteWidth;
    private int spriteHeight;

    private Point direction;

    private SpriteSheet spriteSheet;

    private double moveSpeed;

    public ActiveGameObject(BufferedImage image, Point2D position, int objectWidth, int objectHeight,
                            int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations,int animationDelayMillis, double moveSpeed) {

        super(image, position, objectWidth, objectHeight);
        this.spriteSheetImage = image;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        this.spriteSheet = new SpriteSheet(image, animations, spriteWidth, spriteHeight, animationDelayMillis);
     //   spriteSheet.setCurrentAnimation(SpriteSheet.Animation.NONE);
     //   setImage(spriteSheet.getCurrentImage());

        this.moveSpeed = moveSpeed;

    }

    public abstract void move(long deltaTime);

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public Point getDirection() {
        return direction;
    }

    public java.util.List<Point2D> getCorners() {
        List<Point2D> corners = new ArrayList<>();
        corners.add(new Point2D.Double(getPosition().getX(), getPosition().getY()));
        corners.add(new Point2D.Double(getPosition().getX() + getObjectWidth(), getPosition().getY()));
        corners.add(new Point2D.Double(getPosition().getX(), getPosition().getY() + getObjectHeight()));
        corners.add(new Point2D.Double(getPosition().getX() + getObjectWidth(), getPosition().getY() + getObjectHeight()));

        return corners;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
        if (direction.equals(new Point(0, -1)))
            getSpriteSheet().setCurrentAnimation(SpriteSheet.Animation.MOVE_UP);
        else if (direction.equals(new Point(-1, 0)))
            getSpriteSheet().setCurrentAnimation(SpriteSheet.Animation.MOVE_LEFT);
        else if (direction.equals(new Point(0, 1)))
            getSpriteSheet().setCurrentAnimation(SpriteSheet.Animation.MOVE_DOWN);
        else // new Point(1,0)
            getSpriteSheet().setCurrentAnimation(SpriteSheet.Animation.MOVE_RIGHT);
    }
}
