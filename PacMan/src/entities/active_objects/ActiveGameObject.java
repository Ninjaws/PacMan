package entities.active_objects;

import entities.GameObject;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * @author Ian Vink
 */

public abstract class ActiveGameObject extends GameObject {

    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;

    private double moveSpeed;

    public ActiveGameObject(BufferedImage image, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, double moveSpeed) {
        super(image, position, objectWidth, objectHeight);
        this.spriteSheet = image;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        this.moveSpeed = moveSpeed;

        setImage(spriteSheet.getSubimage(0, 0, spriteHeight, spriteWidth));

    }

    public abstract void move(long deltaTime);

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
