package entities.active_objects;

import data.Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;


public class PacMan extends ActiveGameObject {


    private Point direction;

    public PacMan(BufferedImage image, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, double moveSpeed) {
        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, moveSpeed);

        this.direction = new Point(-1, 0);
    }

    @Override
    public void move(long deltaTime) {

        Point2D oldPos = getPosition();

        Point2D deltaPos = new Point2D.Double(direction.getX() * getMoveSpeed() * deltaTime, direction.getY() * getMoveSpeed() * deltaTime);

        Point2D newPos = new Point2D.Double(oldPos.getX() + deltaPos.getX(), oldPos.getY() + deltaPos.getY());


        //Check all 4 corners of the character
        Point2D objEdges = new Point2D.Double(newPos.getX(), newPos.getY());
        for (int x = 0; x <= 1; x++) {
            for (int y = 0; y <= 1; y++) {
                objEdges.setLocation(newPos.getX() + x * getObjectWidth(), newPos.getY() + y * getObjectHeight());
                //Checking if the tile is walkable
                Point tileMapPos = Game.getInstance().getMap().getTileMapPos(objEdges);
                if (!Game.getInstance().getMap().getCollisionlayer()[tileMapPos.y][tileMapPos.x])
                    return;
            }
        }


        setPosition(newPos);
    }


    public void setDirection(Point direction) {
        this.direction = direction;
    }
}
