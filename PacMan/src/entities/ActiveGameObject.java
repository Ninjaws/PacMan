package entities;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * @author Ian Vink
 */

public abstract class ActiveGameObject extends GameObject {

    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;

    /**
     * This constructor also contains de sprite width and height, used for spritecycling
     * @param image The spritesheet
     * @param position The position of the object
     * @param spriteWidth The width of the individual sprites
     * @param spriteHeight The height of the individual sprites
     */
    public ActiveGameObject(BufferedImage image, Point2D position, int spriteWidth, int spriteHeight) {
        super(image, position);
        this.spriteSheet = image;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        setImage(spriteSheet.getSubimage(0, 0, spriteHeight, spriteWidth));

    }
}
