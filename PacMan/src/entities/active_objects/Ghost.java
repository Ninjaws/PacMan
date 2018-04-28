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
                 int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {

        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed);

        this.deadImage = deadImage;
        setDirection(new Point(0, 0));
        target = null;
    }

    @Override
    public void move(long deltaTime) {

        if (target == null)
            target = Game.getInstance().getPacMan().getTarget();//Game.getInstance().getScatterCorners().get(3);

        Point mapPos = Game.getInstance().getMap().getTileMapPos(getPosition());

        Point direction = target.getDistanceMap().getCells()[mapPos.y][mapPos.x].getVector();

        Point2D deltaPos = new Point2D.Double(direction.getX() * getMoveSpeed() * deltaTime, direction.getY() * getMoveSpeed() * deltaTime);


        setDirection(direction);
        setPosition(new Point2D.Double(getPosition().getX() + deltaPos.getX(), getPosition().getY() + deltaPos.getY()));


        getSpriteSheet().update();
        setImage(getSpriteSheet().getCurrentImage());
    }


}
