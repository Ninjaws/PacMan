package application.game.data.pathfinding;

import application.game.tiled.Map;

import java.awt.*;

public class Target {

    DistanceMap distanceMap;
    Point position;

    public Target(Map map, Point position){
        this.distanceMap = new DistanceMap(map);
        this.position = position;

        distanceMap.calculateDistance(position);
    }

    public DistanceMap getDistanceMap() {
        return distanceMap;
    }

    public Point getPosition() {
        return position;
    }
}
