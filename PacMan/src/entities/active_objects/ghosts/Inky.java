package entities.active_objects.ghosts;

import business.SpriteSheet;
import data.Game;
import data.pathfinding.Target;
import entities.GameObject;
import entities.active_objects.PacMan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

public class Inky extends Ghost{

    public Inky(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {
        super(image, deadImage, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed);
        target = Game.getInstance().getPacMan().getTarget();
    }

    @Override
    public void setNextTarget() {
            if(Game.getInstance().getPacMan().getPosition().distance(this.getPosition()) < 160) {
                this.setMoveSpeed(0.125);
                target = Game.getInstance().getPacMan().getTarget();
            }
            else {
                this.setMoveSpeed(0.1);
                Random random = new Random();
                int index = 0;
                for(GameObject gameObject : Game.getInstance().getGameObjects()){
                    if(gameObject instanceof Ghost) {
                        int i = random.nextInt(4);
                        if(i == 2) {
                            target = ((Ghost) gameObject).getTarget();
                            break;
                        }
                        else if(index == 3) {
                            target = ((Ghost) gameObject).getTarget();
                            break;
                        }
                        index++;
                    }
                }
            }
    }

    @Override
    public void update() {
        setNextTarget();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
