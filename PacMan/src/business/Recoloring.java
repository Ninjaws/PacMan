package business;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * @author Ian Vink
 */

public class Recoloring {

    public static BufferedImage colorImage(BufferedImage image, Color color) {
        BufferedImage copyImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = copyImage.createGraphics();
        g2d.drawImage(image,new AffineTransform(), null);
        g2d.dispose();

        int width = copyImage.getWidth();
        int height = copyImage.getHeight();
        WritableRaster raster = copyImage.getRaster();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = color.getRed();
                pixels[1] = color.getGreen();
                pixels[2] = color.getBlue();
                raster.setPixel(xx, yy, pixels);
            }
        }
        return copyImage;
    }
}