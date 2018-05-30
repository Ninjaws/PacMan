package application.game.entities.active_objects.ghosts;

import application.game.business.SpriteSheet;
import application.game.data.Game;
import application.game.data.pathfinding.Target;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Blinky extends Ghost {
    private Target scatterdTarget = Game.getInstance().getScatterCorners().get(0);

    public Blinky(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {
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
            if((double)(seconds) % scatterdLevels.get(levelIndex) == 0){
                seconds = 0;
                scatterd = false;
                chase = true;
                setNextTarget();
            }
            else if(scatterdTarget.getPosition().distance(this.getPosition()) < 128) {
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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        update();
    }
}
