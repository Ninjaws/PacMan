package entities.active_objects.ghosts;

import business.SpriteSheet;
import data.Game;
import data.pathfinding.Target;
import entities.GameObject;
import entities.active_objects.PacMan;
import tiled.ObjectLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Inky extends Ghost{

    private Target scatterdTarget = Game.getInstance().getScatterCorners().get(3);
    private int index = 3;
    private ArrayList<Target> targets;
    public Inky(BufferedImage image, BufferedImage deadImage, Point2D position, int objectWidth, int objectHeight, int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed) {
        super(image, deadImage, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed);
        target = Game.getInstance().getPacMan().getTarget();
        targets = (ArrayList)Game.getInstance().getScatterCorners();
        ObjectLayer objLayer = (ObjectLayer) Game.getInstance().getMap().getLayers().stream()
                .filter(layer -> layer instanceof ObjectLayer)
                .findFirst()
                .get();
        targets.add(new Target(Game.getInstance().getMap(), objLayer.getStartPosPacMan()));
        targets.add(new Target(Game.getInstance().getMap(), objLayer.getStartPosGhosts().get(0)));
    }

    @Override
    public void setNextTarget() {
            if(chase) {
                target = Game.getInstance().getPacMan().getTarget();
            }
            else if(scatterd) {
                if(this.getPosition().distance(target.getPosition()) < 64 && scatterd) {
                    while (true){
                        target = targets.get(index);
                        Random random = new Random();
                        index = random.nextInt(5);
                        if(!target.equals(targets.get(index)))
                            break;
                    }
                }
            }
    }


    @Override
    public void update() {
        if(Game.getInstance().getPacMan().getPosition().distance(this.getPosition()) >= 160 && chase) {
            chase = false;
            scatterd = true;
            target = targets.get(index);
            setNextTarget();
        }
        else if((Game.getInstance().getPacMan().getPosition().distance(this.getPosition()) < 160 && scatterd)) {
            scatterd = false;
            chase = true;
            setNextTarget();
        }

        setNextTarget();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
