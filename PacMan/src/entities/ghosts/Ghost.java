package entities.ghosts;

import entities.GameObject;

import java.awt.image.BufferedImage;

public abstract class Ghost extends GameObject {

    public Ghost(BufferedImage image) {
        super(image);
    }
}
