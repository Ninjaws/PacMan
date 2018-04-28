package tiled;

import data.Loop;
import data.pathfinding.Target;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectLayer extends Layer {
    private Map map;
    private Point startPosPacMan;
    private ArrayList<Point> startPosGhosts = new ArrayList<>();
    private ArrayList<Loop> loops = new ArrayList<Loop>();
    private ArrayList<Target> scatterCorners = new ArrayList<>();

    private String name;

    protected ObjectLayer(JsonObject layer, Map map) {
        super(layer);
        this.map = map;

        name = layer.getString("name");

        JsonArray data = layer.getJsonArray("objects");
        for (int i = 0; i < data.size(); i++) {
           // System.out.println(data.getJsonObject(i));

            JsonObject currentObject = data.getJsonObject(i);

            switch (currentObject.getString("type")) {
                case "Start PacMan":
                    startPosPacMan = new Point(currentObject.getInt("x"), currentObject.getInt("y"));
                    break;
                case "Start Ghost":
                    startPosGhosts.add(new Point(currentObject.getInt("x"), currentObject.getInt("y")));
                    break;
                case "Scatter Corner":
                    scatterCorners.add(new Target(map,new Point(currentObject.getInt("x"),currentObject.getInt("y"))));
                    break;
                case "Loop":
                    if (loops.stream()
                            .anyMatch(loop -> loop.getName().equals(currentObject.getString("name").substring(0, 6))))
                        continue;

                    //Get the exit that matches with the entrance
                    JsonObject obj = data.stream()
                            .map(object -> (JsonObject) object)
                            .filter(object -> object.getString("type").equals("Loop"))
                            .filter(object -> !object.getString("name").equals(currentObject.getString("name")))
                            .filter(object -> object.getString("name").charAt(5) == currentObject.getString("name").charAt(5))
                            .findFirst()
                            .get();

                    loops.add(new Loop(currentObject.getString("name").substring(0, 6),
                            new Point(currentObject.getInt("x") / map.getTileWidth(), currentObject.getInt("y") / map.getTileHeight()),
                            new Point(obj.getInt("x") / map.getTileWidth(), obj.getInt("y") / map.getTileHeight())));

                  //  System.out.println("Current: " + currentObject);
                  //  System.out.println("Match: " + obj);

                    break;
            }
        }
    }

    public Point getStartPosPacMan() {
        return startPosPacMan;
    }

    public ArrayList<Point> getStartPosGhosts() {
        return startPosGhosts;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Loop> getLoops() {
        return loops;
    }

    public ArrayList<Target> getScatterCorners() {
        return scatterCorners;
    }

    @Override
    public void draw(Graphics2D g2d) {

    }
}
