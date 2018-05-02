package entities.active_objects;

import business.SpriteSheet;
import business.VecMath;
import data.Game;
import data.pathfinding.Target;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @author Ian Vink
 */

public class Ghost extends ActiveGameObject {

    private BufferedImage deadImage;

    private Target target;

    public Ghost(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight,
                 int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed, boolean active) {

        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed, active);

        this.deadImage = deadImage;
        setDirection(new Point(0, 0));
        getSpriteSheet().update();
        setImage(getSpriteSheet().getCurrentImage());
        target = null;
    }

    @Override
    public void move(long deltaTime) {

        if (target == null)
            target = Game.getInstance().getPacMan().getTarget();//Game.getInstance().getScatterCorners().get(3);


        //Checking if all 4 corners are inside 1 tile (to make sure they don't walk over the walls)
        int distance = -5;
        boolean insideTile = true;
        for (Point2D corner : getCorners()) {
            Point tileCorner = Game.getInstance().getMap().getTileMapPos(corner);

            //The first element becomes the starting distance
            if (distance == -5)
                distance = target.getDistanceMap().getCells()[tileCorner.y][tileCorner.x].getDistance();
            else {
                if (distance != target.getDistanceMap().getCells()[tileCorner.y][tileCorner.x].getDistance()) {
                    insideTile = false;
                    break;
                }
            }
        }

        if (insideTile){
            Point mapPos = Game.getInstance().getMap().getTileMapPos(getPosition());

            setDirection(target.getDistanceMap().getCells()[mapPos.y][mapPos.x].getVector());
        }


        Point2D deltaPos = new Point2D.Double(getDirection().getX() * getMoveSpeed() * deltaTime, getDirection().getY() * getMoveSpeed() * deltaTime);


       // setDirection(direction);
        setPosition(new Point2D.Double(getPosition().getX() + deltaPos.getX(), getPosition().getY() + deltaPos.getY()));


        getSpriteSheet().update();
        setImage(getSpriteSheet().getCurrentImage());
    }


}
