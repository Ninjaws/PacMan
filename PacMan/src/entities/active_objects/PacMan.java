package entities.active_objects;

import data.Game;
import data.Loop;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


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
        boolean walkable = true;
        boolean foundLoop = false;
        List<Point2D> corners = new ArrayList<>();


        if (direction.x != 0) {
            int x = Math.max(0, direction.x);
            corners.add(new Point2D.Double(newPos.getX() + x * getObjectWidth(), newPos.getY() + 0 * getObjectHeight()));
            corners.add(new Point2D.Double(newPos.getX() + x * getObjectWidth(), newPos.getY() + 1 * getObjectHeight()));

        } else if (direction.y != 0) {
            int y = Math.max(0, direction.y);
            corners.add(new Point2D.Double(newPos.getX() + 0 * getObjectWidth(), newPos.getY() + y * getObjectHeight()));
            corners.add(new Point2D.Double(newPos.getX() + 1 * getObjectWidth(), newPos.getY() + y * getObjectHeight()));

        }

        for (Point2D corner : corners) {
            //Checking if the tile is walkable
            Point tileMapPos = Game.getInstance().getMap().getTileMapPos(corner);
            if (!Game.getInstance().getMap().getCollisionlayer()[tileMapPos.y][tileMapPos.x])
                walkable = false;
            //Check if you are looping
            if (Game.getInstance().getMap().getLooplayer()[tileMapPos.y][tileMapPos.x]) {

                Loop loop = Game.getInstance().getLoop(tileMapPos);
                if (loop == null)
                    continue;
                foundLoop = true;


                //Used to make sure pacman spawns against the tile edge, and not inside of it when you spawn on the left of top
                Point2D correctionValue = new Point2D.Double(0, 0);

                if (direction.x == 1)
                    correctionValue.setLocation(Game.getInstance().getMap().getTileWidth() - getObjectWidth(), 0);
                else if (direction.y == 1)
                    correctionValue.setLocation(0, Game.getInstance().getMap().getTileHeight() - getObjectHeight());


                //Converting back to the actual position
                if (loop.getEntrance().equals(tileMapPos))
                    newPos = new Point2D.Double(loop.getExit().getX() * Game.getInstance().getMap().getTileWidth() + correctionValue.getX(), loop.getExit().getY() * Game.getInstance().getMap().getTileHeight() + correctionValue.getY());
                else
                    newPos = new Point2D.Double(loop.getEntrance().getX() * Game.getInstance().getMap().getTileWidth() + correctionValue.getX(), loop.getEntrance().getY() * Game.getInstance().getMap().getTileHeight() + correctionValue.getY());

            }

        }
        if (!walkable)
            if (!foundLoop)
                return;

        setPosition(newPos);
    }


    public void setDirection(Point direction) {
        this.direction = direction;
    }
}
