package tiled;

import java.awt.image.BufferedImage;

/**
 * @author Ian Vink
 */

public class Tile {

private BufferedImage image;
private boolean walkable;
private boolean startArea;
private boolean loop;
private boolean coin;
private boolean powerup;

Tile(BufferedImage image){
    this.image = image;
    this.walkable = false;
    this.startArea = false;
    this.loop = false;
    this.coin = false;
    this.powerup = false;
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

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isCoin() {
        return coin;
    }

    public void setCoin(boolean coin) {
        this.coin = coin;
    }

    public boolean isPowerup() {
        return powerup;
    }

    public void setPowerup(boolean powerup) {
        this.powerup = powerup;
    }
}
