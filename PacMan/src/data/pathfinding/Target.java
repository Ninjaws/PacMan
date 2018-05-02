package data.pathfinding;

import tiled.Map;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Target {

    DistanceMap distanceMap;
    Point position;

    public Target(Map map, Point position){
        this.distanceMap = new DistanceMap(map);
        this.position = position;

        distanceMap.calculateDistance(position);
    }

    public void debugDraw(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.draw(new Rectangle2D.Double(position.getX(), position.getY(), 32, 32));
    }

    public DistanceMap getDistanceMap() {
        return distanceMap;
    }

    public Point getPosition() {
        return position;
    }
}
