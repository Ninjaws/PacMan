package entities;

import entities.GameObject;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Ghost extends ActiveGameObject {

    /**
     * This constructor also contains de sprite width and height, used for spritecycling
     *
     * @param image        The spritesheet
     * @param position     The position of the object
     * @param spriteWidth  The width of the individual sprites
     * @param spriteHeight The height of the individual sprites
     */
    public Ghost(BufferedImage image, Point2D position, int spriteWidth, int spriteHeight) {
        super(image, position, spriteWidth, spriteHeight);
    }
}
