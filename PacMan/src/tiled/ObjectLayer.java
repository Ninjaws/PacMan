package tiled;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.util.ArrayList;

public class ObjectLayer extends Layer {
    private Map map;
    private Point startPosPacMan;
    private ArrayList<Point> startPosGhosts = new ArrayList<>();
    private ArrayList<Point[]> loops = new ArrayList<>();

    protected ObjectLayer(JsonObject layer, Map map) {
        super(layer);
        this.map = map;

        JsonArray data = layer.getJsonArray("objects");
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.getJsonObject(i));

            JsonObject currentObject = data.getJsonObject(i);

            switch (currentObject.getString("type")) {
                case "Start PacMan":
                    startPosPacMan = new Point(currentObject.getInt("x"), currentObject.getInt("y"));
                    break;
                case "Start Ghost":
                    startPosGhosts.add(new Point(currentObject.getInt("x"), currentObject.getInt("y")));
                    break;
                case "Loop":


            }
        }
    }

    public Point getStartPosPacMan() {
        return startPosPacMan;
    }

    public ArrayList<Point> getStartPosGhosts() {
        return startPosGhosts;
    }

    @Override
    public void draw(Graphics2D g2d) {

    }
}
