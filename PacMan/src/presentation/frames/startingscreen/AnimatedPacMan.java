package presentation.frames.startingscreen;

import business.Recoloring;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;

/**
 * The AnimatedPacMan class regulates a spawned pacman on the titelscreen.
 */
public class AnimatedPacMan {
    /**
     * The position attribute represents the position of the AnimatedPacMan.
     */
    private Point2D position;

    /**
     * The frames attribute represents all frames
     */
    private BufferedImage[] frames = new BufferedImage[16];

    /**
     * The index attribute regulates which frame is represented.
     */
    private double index = 0;

    /**
     * The indexMax attribute is the maximum value the index can take.
     */
    private int indexMax;

    /**
     * The speed attribute represents the speed of the pacman
     */
    private int speed;

    /**
     * The AnimatedPacMan constructor calculates a random speed and color. It represents a pacman that will walk on the titelscreen.
     * @param position the starting position of the pacman.
     * @param frames is the full spritesheet of the pacman.png.
     */
    public AnimatedPacMan(Point2D position, BufferedImage[] frames) {
        this.position = position;
        this.frames = frames;

        Random random = new Random();
        if(position.getX() <= 0){
            speed = random.nextInt(10);
            indexMax = 3;
            index = 0;
        }
        else{
            speed =  -random.nextInt(10);
            indexMax = 7;
            index = 4;
        }

        if(speed == 0)
            speed = 1;

        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);
        Color color = new Color(r,g,b);
        BufferedImage[] preFrames = new BufferedImage[16];
        int colorIndex = 0;
        for(BufferedImage image : frames){
            preFrames[colorIndex] = Recoloring.colorImage(image,color);
            colorIndex++;
        }
        this.frames = preFrames;
    }


    /**
     * Updates the position and index of the pacman.
     */
    public void update() {
        position = new Point2D.Double(position.getX() + speed, position.getY());
        if (index < indexMax)
            index+= 0.3;
        else
            index = indexMax - 3;
    }

    /**
     * draws a pacman on the titelscreen.
     * @param g2d is gotten from the paintcomponent.
     */
    public void draw(Graphics2D g2d) {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX(), position.getY());
        g2d.drawImage(frames[(int)Math.round(index)], tx, null);
    }

    /**
     * Geta the position of the pacman
     * @return the position of the pacman.
     */
    public Point2D getPosition() {
        return position;
    }
}
