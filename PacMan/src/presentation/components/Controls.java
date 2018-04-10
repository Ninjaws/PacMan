package presentation.components;

import data.Game;

import java.awt.*;

public class Controls {
    private Key currentKey;

    public Controls() {
        currentKey = Key.NONE;
    }

    public void update() {
        if (currentKey.equals(Key.NONE))
            return;

        Point direction = null;
        if (currentKey.equals(Key.UP)) {
            direction = new Point(0, -1);
        } else if (currentKey.equals(Key.LEFT)) {
            direction = new Point(-1, 0);
        } else if (currentKey.equals(Key.DOWN)) {
            direction = new Point(0, 1);
        } else {// if (currentKey.equals(Key.RIGHT)) {
            direction = new Point(1, 0);
        }

        final Point testDirection = direction;

        //Check if any of the 4 corners is not inside the map or on walkable terrain after adding the direction
        if (Game.getInstance().getPacMan().getCorners().stream()
                .map(corner -> corner = Game.getInstance().getMap().getTileMapPos(corner))
                .anyMatch(corner -> !Game.getInstance().getMap().isInsideMap(new Point((int) corner.getX() + testDirection.x, (int) corner.getY() + testDirection.y)) ||
                        !Game.getInstance().getMap().getCollisionlayer()[(int) corner.getY() + testDirection.y][(int) corner.getX() + testDirection.x]))
            return;

        Game.getInstance().getPacMan().setDirection(direction);
    }


    public Key getCurrentKey() {
        return currentKey;
    }

    public void setCurrentKey(Key currentKey) {
        this.currentKey = currentKey;
    }

    public enum Key {
        NONE, UP, LEFT, DOWN, RIGHT
    }
}

