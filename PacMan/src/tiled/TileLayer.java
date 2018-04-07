package tiled;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * @author Ian Vink
 */

public class TileLayer extends Layer {

    private Map map;

    private int layerHeight;
    private int layerWidth;

    private int[][] tiles;

    private BufferedImage image;

    public TileLayer(JsonObject layer, Map map) {
        super(layer);

        layerHeight = layer.getInt("height");
        layerWidth = layer.getInt("width");

        this.map = map;

        tiles = new int[layerHeight][layerWidth];


        int i = 0;
        JsonArray data = layer.getJsonArray("data");
        for (int y = 0; y < layerHeight; y++) {
            for (int x = 0; x < layerWidth; x++) {
                int index = data.getInt(i);
                tiles[y][x] = index;

                if (index > 0) {
                    Tile tile = map.getTiles().get(index);
                    if (tile.isWalkable() || tile.isStartArea()) {
                        map.getCollisionlayer()[y][x] = true;
                    }
                }
                i++;
            }
        }
        image = createImage();
    }

    private BufferedImage createImage() {
        BufferedImage img = new BufferedImage(map.getTileWidth() * layerWidth, map.getTileHeight() * layerHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        for (int y = 0; y < layerHeight; y++) {
            for (int x = 0; x < layerWidth; x++) {
                int index = tiles[y][x];

                if (index > 0) {
                    AffineTransform tx = AffineTransform.getTranslateInstance(x * map.getTileWidth(), y * map.getTileHeight());
                    g2d.drawImage(map.getTiles().get(index).getImage(), tx, null);
                }
            }
        }
        return img;
    }


    @Override
    public void draw(Graphics2D g2d) {
      //  if (isVisible()) {
            g2d.drawImage(image, new AffineTransform(), null);
     //   }
    }
}
