package entities.pickups;

import entities.GameObject;

import java.awt.image.BufferedImage;

public abstract class Pickup extends GameObject {
    public Pickup(BufferedImage image) {
        super(image);
    }
}
