package entities.active_objects.ghosts;

import business.SpriteSheet;
import business.VecMath;
import data.Game;
import data.pathfinding.Target;
import entities.active_objects.ActiveGameObject;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ian Vink
 * @author Jordy van Raalte
 */

public abstract class Ghost extends ActiveGameObject implements ActionListener {

    private BufferedImage deadImage;

    protected Target target;
    protected HashMap<Integer, Double> scatterdLevels = new HashMap<>();
    protected HashMap<Integer, Double> chaseLevels = new HashMap<>();
    protected int seconds = 0;
    protected int levelIndex = 1;
    protected boolean chase = false;
    protected boolean scatterd = true;

    public Ghost(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight,
                 int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {

        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed, true);
        this.deadImage = deadImage;
        setDirection(new Point(0, 0));

        scatterdLevels.put(1,7.0);
        scatterdLevels.put(2,7.0);
        scatterdLevels.put(3,7.0);
        scatterdLevels.put(4,7.0);

        chaseLevels.put(1,20.0);
        chaseLevels.put(2,20.0);
        chaseLevels.put(3,20.0);
        chaseLevels.put(4,Double.POSITIVE_INFINITY);
        Timer timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void move(long deltaTime) {

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

    public abstract void setNextTarget();

    public abstract void update();

    public Target getTarget() {
        return target;
    }
}
