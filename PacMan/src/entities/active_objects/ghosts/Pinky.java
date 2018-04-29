package entities.active_objects.ghosts;

import business.SpriteSheet;
import data.Game;
import data.pathfinding.Target;
import entities.active_objects.Ghost;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Pinky extends Ghost {
    private boolean chase = false;
    private boolean scatterd = true;
    private Target scatterdTarget = Game.getInstance().getScatterCorners().get(0);

    public Pinky(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {
        super(image, deadImage, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed);
        target = scatterdTarget;
    }

    @Override
    public void update() {
        if(scatterd) {
            if((double)(seconds) % scatterdLevels.get(levelIndex) == 0){
                scatterd = false;
                chase = true;
                seconds = 0;
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
    public void setNextTarget() {
        if(chase)
            target = Game.getInstance().getPacMan().getTarget();

        else if(scatterd)
            target = scatterdTarget;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        update();
    }
}
