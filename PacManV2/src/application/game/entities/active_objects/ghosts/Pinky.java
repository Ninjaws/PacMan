package application.game.entities.active_objects.ghosts;

import application.game.business.SpriteSheet;
import application.game.data.Game;
import application.game.data.pathfinding.Target;
import application.game.entities.active_objects.PacMan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Pinky extends Ghost {
    private Target scatterdTarget = Game.getInstance().getScatterCorners().get(1);

    public Pinky(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {
        super(image, deadImage, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed);
        target = scatterdTarget;
    }

    @Override
    public void update() {
        if(scatterd) {
            if((double)(seconds) % scatterdLevels.get(levelIndex) == 0){
                seconds = 0;
                scatterd = false;
                chase = true;
                setNextTarget();
            }
            else if(scatterdTarget.getPosition().distance(this.getPosition()) < 64) {
                seconds = 0;
                scatterd = false;
                chase = true;
                setNextTarget();
            }
        }
        else if(chase) {
            if((double)(seconds) % chaseLevels.get(levelIndex) == 0){
                seconds = 0;
                scatterd = true;
                chase = false;
                levelIndex++;
                setNextTarget();
            }
            else if(target.getPosition().distance(this.getPosition()) < 64) {
                setNextTarget();
            }
        }

    }

    @Override
    public void setNextTarget() {
        if(chase) {
            Target preTarget = target;
            int index = 4;
            try {
                while(true) {
                    PacMan pacMan = Game.getInstance().getPacMan();
                    Point newTargetPosition = new Point(
                            (int)pacMan.getPosition().getX() + pacMan.getDirection().x * 32 *index,
                            (int)pacMan.getPosition().getY() + pacMan.getDirection().y * 32 * index - 16);
                    Point2D tile = Game.getInstance().getMap().getTileMapPos(newTargetPosition);
                    if(Game.getInstance().getMap().getCollisionLayer()[(int)tile.getY()][(int)tile.getX()]) {
                        target = new Target(Game.getInstance().getMap(), newTargetPosition);
                        break;
                    }
                    else {
                        index--;
                    }
                }
                if(target.getPosition().distance(preTarget.getPosition()) < 16 && !target.equals(Game.getInstance().getPacMan().getTarget())) {
                    target = Game.getInstance().getPacMan().getTarget();
                }
            }
            catch (Exception e) {
                target = Game.getInstance().getPacMan().getTarget();
                //e.printStackTrace();
            }
        }
        else if(scatterd)
            target = scatterdTarget;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        if(chase && seconds % 5 == 0)
            setNextTarget();

        else if(target.getPosition().distance(Game.getInstance().getPacMan().getPosition()) > 92 && chase)
            setNextTarget();
        update();
    }
}
