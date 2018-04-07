package tiled;
import javax.json.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Ian Vink
 */

public class Map {


    private int mapHeight;
    private int mapWidth;
    private int tileHeight;
    private int tileWidth;

    private ArrayList<Layer> layers = new ArrayList();


    public Map(String filename){
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(filename));
        JsonObject map = (JsonObject) reader.read();

        mapHeight = map.getInt("height");
        mapWidth = map.getInt("width");
        tileHeight = map.getInt("tileheight");
        tileWidth = map.getInt("tilewidth");


        System.out.println(mapHeight);

    }



    public void draw(Graphics2D g2d){
        for(Layer layer : layers){
           // layer.draw(g2d);
        }
    }

}
