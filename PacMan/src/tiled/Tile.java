package tiled;

import java.awt.image.BufferedImage;

/**
 * @author Ian Vink
 */

public class Tile {

private BufferedImage image;
private boolean walkable;
private boolean startArea;

Tile(BufferedImage image){
    this.image = image;
    this.walkable = false;
    this.startArea = false;
}

    public BufferedImage getImage() {
        return image;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public boolean isStartArea() {
        return startArea;
    }

    public void setStartArea(boolean startArea) {
        this.startArea = startArea;
    }
}
