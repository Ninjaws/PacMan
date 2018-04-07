package entities;

import data.Game;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class PacMan extends GameObject {

    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;

    public PacMan(BufferedImage image, int spriteWidth, int spriteHeight, Point2D position) {
        super(image);
        this.spriteSheet = image;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        setImage(spriteSheet.getSubimage(0, 0, spriteHeight, spriteWidth));

        setPosition(position);
    }

}
