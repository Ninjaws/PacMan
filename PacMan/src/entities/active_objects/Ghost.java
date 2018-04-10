package entities.active_objects;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Ghost extends ActiveGameObject {

    private BufferedImage deadImage;

    public Ghost(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, double moveSpeed) {
        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, moveSpeed);

        this.deadImage = deadImage;
    }

    @Override
    public void move(long deltaTime) {

    }
}
