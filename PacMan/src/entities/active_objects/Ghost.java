package entities.active_objects;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Ghost extends ActiveGameObject {


    public Ghost(BufferedImage image, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, double moveSpeed) {
        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, moveSpeed);
    }

    @Override
    public void move(long deltaTime) {

    }
}
