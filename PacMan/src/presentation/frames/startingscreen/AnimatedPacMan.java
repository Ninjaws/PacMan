package presentation.frames.startingscreen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;

public class AnimatedPacMan {
    private Point2D position;
    private BufferedImage[] frames = new BufferedImage[16];
    private BufferedImage[] preFrames = new BufferedImage[16];
    private double index = 0;
    private int colorIndex = 0;
    private int indexMax;
    private int speed;

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
            speed = 0 - random.nextInt(10);
            indexMax = 7;
            index = 4;
        }
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);
        this.frames = randomColor(this.frames,r,g,b);
    }

    public BufferedImage[] randomColor(BufferedImage[] images, int r, int g, int b){
            int index = 0;
            BufferedImage[] preFrames = new BufferedImage[16];
            for(BufferedImage bufferedImage : images){
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                WritableRaster raster = bufferedImage.getRaster();
                for (int xx = 0; xx < width; xx++) {
                    for (int yy = 0; yy < height; yy++) {
                        int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                        pixels[0] = r;
                        pixels[1] = g;
                        pixels[2] = b;
                        raster.setPixel(xx, yy, pixels);
                    }
                }
                preFrames[index] = bufferedImage;
                index++;
            }
            return preFrames;
    }


    public void update() {
        position = new Point2D.Double(position.getX() + speed, position.getY());
        if (index < indexMax)
            index+= 0.3;
        else
            index = indexMax - 3;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX(), position.getY());
        g2d.drawImage(frames[(int)Math.round(index)], tx, null);
    }

    public Point2D getPosition() {
        return position;
    }
}
