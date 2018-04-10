package entities.active_objects;

import business.SpriteSheet;
import entities.GameObject;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @author Ian Vink
 */

public abstract class ActiveGameObject extends GameObject {

    private BufferedImage spriteSheetImage;
    private int spriteWidth;
    private int spriteHeight;

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

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
