package application.game.entities.active_objects.ghosts;

import application.game.business.SpriteSheet;
import application.game.data.Game;
import application.game.data.pathfinding.Target;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Map;

public class Clyde extends Ghost implements Serializable {

    private Target scatterdTarget = Game.getInstance().getScatterCorners().get(2);
    public Clyde(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {
        super(image, deadImage, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed);
        target = scatterdTarget;
    }

    @Override
    public void setNextTarget() {
        if(chase)
            target = Game.getInstance().getPacMan().getTarget();
        else if(scatterd)
            target = scatterdTarget;

    }

    @Override
    public void update() {
        if(scatterd) {
            if(Game.getInstance().getPacMan().getPosition().distance(this.getPosition()) > 192) {
                chase = true;
                scatterd = false;
                setNextTarget();
            }
            else if(target.getPosition().distance(scatterdTarget.getPosition()) < 64){
                chase = true;
                scatterd = false;
                setNextTarget();
            }
        }
        else if(chase) {
            if(Game.getInstance().getPacMan().getPosition().distance(this.getPosition()) <= 192) {
                chase = false;
                scatterd = true;
                setNextTarget();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
