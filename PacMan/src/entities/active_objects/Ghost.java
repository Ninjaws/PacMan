package entities.active_objects;

import business.SpriteSheet;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Ghost extends ActiveGameObject {

    private BufferedImage deadImage;

    public Ghost(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight,
                 int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {

        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed);

        this.deadImage = deadImage;
    }

    @Override
    public void move(long deltaTime) {
        getSpriteSheet().update();
        setImage(getSpriteSheet().getCurrentImage());
    }
}
