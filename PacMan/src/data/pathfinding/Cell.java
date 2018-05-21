package data.pathfinding;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * @author Ian Vink
 */

public class Cell {
    private int distance;
    private Point vector;

    //Only for heatmap
    private Color color;


    public Cell() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Point getVector() {
        return vector;
    }

    public void setVector(Point vector) {
        this.vector = vector;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}