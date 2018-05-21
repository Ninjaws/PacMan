package business;

import java.awt.geom.Point2D;

/**
 * @author Ian Vink
 */

public class VecMath {


    public static double getMagnitude(Point2D vector) {
        return Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
    }

    public static Point2D getNormalized(Point2D vector) {
        double magnitude = getMagnitude(vector);
        return new Point2D.Double(vector.getX() / magnitude, vector.getY() / magnitude);
    }


}