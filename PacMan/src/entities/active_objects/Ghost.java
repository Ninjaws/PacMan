package entities.active_objects;

import business.SpriteSheet;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Ghost extends ActiveGameObject {

    private BufferedImage deadImage;
    private Point direction;

    public Ghost(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight,
                 int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {

        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed);

        this.deadImage = deadImage;
        this.direction = new Point(0,0);
    }

    @Override
    public void move(long deltaTime) {
        getSpriteSheet().update();
        setImage(getSpriteSheet().getCurrentImage());
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
