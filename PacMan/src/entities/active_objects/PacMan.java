package entities.active_objects;

import business.SoundPlayer;
import business.SpriteSheet;
import data.Game;
import data.Loop;
import data.pathfinding.Target;
import entities.pickups.Pickup;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ian Vink
 */

public class PacMan extends ActiveGameObject {

    private Target target;
    private SoundTimer timer;

    public PacMan(BufferedImage image, Point2D position, int objectWidth, int objectHeight,
                  int spriteWidth, int spriteHeight, Map<SpriteSheet.Animation, Integer> animations, int animationDelayMillis, double moveSpeed, boolean active) {

        super(image, position, objectWidth, objectHeight, spriteWidth, spriteHeight, animations, animationDelayMillis, moveSpeed, active);

        setDirection(new Point(-1, 0));
        getSpriteSheet().setCurrentAnimation(SpriteSheet.Animation.MOVE_LEFT);
        setImage(getSpriteSheet().getCurrentImage());
        target = new Target(Game.getInstance().getMap(), Game.getInstance().getMap().getTileMapPos(position));
        timer = new SoundTimer(0);
        timer.setActive(false);
    }

    @Override
    public void move(long deltaTime) {

        timer.update(deltaTime);

        Point2D oldPos = getPosition();

        Point2D deltaPos = new Point2D.Double(getDirection().getX() * getMoveSpeed() * deltaTime, getDirection().getY() * getMoveSpeed() * deltaTime);

        Point2D newPos = new Point2D.Double(oldPos.getX() + deltaPos.getX(), oldPos.getY() + deltaPos.getY());


        //Check all 4 corners of the character
        Point2D objEdges = new Point2D.Double(newPos.getX(), newPos.getY());
        boolean walkable = true;
        boolean foundLoop = false;
        List<Point2D> corners = new ArrayList<>();


        if (getDirection().x != 0) {
            int x = Math.max(0, getDirection().x);
            corners.add(new Point2D.Double(newPos.getX() + x * getObjectWidth(), newPos.getY() + 0 * getObjectHeight()));
            corners.add(new Point2D.Double(newPos.getX() + x * getObjectWidth(), newPos.getY() + 1 * getObjectHeight()));

        } else if (getDirection().y != 0) {
            int y = Math.max(0, getDirection().y);
            corners.add(new Point2D.Double(newPos.getX() + 0 * getObjectWidth(), newPos.getY() + y * getObjectHeight()));
            corners.add(new Point2D.Double(newPos.getX() + 1 * getObjectWidth(), newPos.getY() + y * getObjectHeight()));

        }

        for (Point2D corner : corners) {
            //Checking if the tile is walkable
            Point tileMapPos = Game.getInstance().getMap().getTileMapPos(corner);
            if (!Game.getInstance().getMap().getCollisionLayer()[tileMapPos.y][tileMapPos.x])
                walkable = false;


            //Check if you are looping
            if (Game.getInstance().getMap().getLoopLayer()[tileMapPos.y][tileMapPos.x]) {

                Loop loop = Game.getInstance().getLoop(tileMapPos);
                if (loop == null)
                    continue;
                foundLoop = true;


                //Used to make sure pacman spawns against the tile edge, and not inside of it when you spawn on the left of top
                Point2D correctionValue = new Point2D.Double(0, 0);

                if (getDirection().x == 1)
                    correctionValue.setLocation(Game.getInstance().getMap().getTileWidth() - getObjectWidth(), 0);
                else if (getDirection().y == 1)
                    correctionValue.setLocation(0, Game.getInstance().getMap().getTileHeight() - getObjectHeight());


                //Converting back to the actual position
                if (loop.getEntrance().equals(tileMapPos))
                    newPos = new Point2D.Double(loop.getExit().getX() * Game.getInstance().getMap().getTileWidth() + correctionValue.getX(), loop.getExit().getY() * Game.getInstance().getMap().getTileHeight() + correctionValue.getY());
                else
                    newPos = new Point2D.Double(loop.getEntrance().getX() * Game.getInstance().getMap().getTileWidth() + correctionValue.getX(), loop.getEntrance().getY() * Game.getInstance().getMap().getTileHeight() + correctionValue.getY());

            }

        }
        if (!walkable)
            if (!foundLoop) {
                Game.getInstance().getSoundPlayer().getClip(SoundPlayer.Sound.PACMAN_MOVEMENT).stop();
                return;
            }

        //Obtain pickups
        for (Point2D corner : corners) {
            Point tileMapPos = Game.getInstance().getMap().getTileMapPos(corner);

            Pickup p = Game.getInstance().getMap().getPickupLayer()[tileMapPos.y][tileMapPos.x];

            //Skip if it is not a pickup
            if (p == null)
                continue;

            //Check if the pickup has already been taken
            if (p.isActive()) {
                p.setActive(false);// = null;//.setActive(false);
                if (!timer.isActive()) {
                    Game.getInstance().getSoundPlayer().getClip(SoundPlayer.Sound.PACMAN_MOVEMENT).loop(Clip.LOOP_CONTINUOUSLY);
                    timer.setDuration(500);
                    timer.setActive(true);
                }
                Game.getInstance().setCurrentScore(Game.getInstance().getCurrentScore() + p.getPoints());
                System.out.println("Current score: " + Game.getInstance().getCurrentScore());
                if (Game.getInstance().getCurrentScore() == Game.getInstance().getMaxScore()) {
                    System.out.println("You win!");
                }
            } else {
                if (timer.durationFinished()) {
                    timer.resetTime();
                    timer.setActive(false);
                    Game.getInstance().getSoundPlayer().getClip(SoundPlayer.Sound.PACMAN_MOVEMENT).stop();//.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }

        }


        getSpriteSheet().update();
        setImage(getSpriteSheet().getCurrentImage());

        setPosition(newPos);
        target.getDistanceMap().calculateDistance(getPosition());

    }


    public Target getTarget() {
        return target;
    }


    private class SoundTimer {
        private int timePassed;
        private int duration;
        private boolean active;

        private SoundTimer(int duration) {
            this.duration = duration;
            this.timePassed = 0;

        }

        private void update(long deltaTime) {
            if (active)
                timePassed += deltaTime;
        }

        private void resetTime() {
            timePassed = 0;
        }

        private boolean durationFinished() {
            return timePassed >= duration;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
    }
}
