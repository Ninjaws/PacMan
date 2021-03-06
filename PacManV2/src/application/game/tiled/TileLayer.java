package application.game.tiled;

import application.game.business.Recoloring;
import application.game.entities.pickups.Coin;
import application.game.entities.pickups.Powerup;

import javax.imageio.ImageIO;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Ian Vink
 */

public class TileLayer extends Layer {

    private Map map;

    private int layerHeight;
    private int layerWidth;

    private int[][] tiles;

    private BufferedImage image;

    private String name;

    public TileLayer(JsonObject layer, Map map) {
        super(layer);

        layerHeight = layer.getInt("height");
        layerWidth = layer.getInt("width");

        this.map = map;

        tiles = new int[layerHeight][layerWidth];

        name = layer.getString("name");

        int i = 0;
        JsonArray data = layer.getJsonArray("data");
        for (int y = 0; y < layerHeight; y++) {
            for (int x = 0; x < layerWidth; x++) {
                int index = data.getInt(i);
                tiles[y][x] = index;

                if (index > 0) {
                    Tile tile = map.getTiles().get(index);
                    if (tile.isWalkable()) {
                        map.getCollisionLayer()[y][x] = true;
                    } else if (tile.isStartArea()) {
                        map.getStartAreaLayer()[y][x] = true;
                    } else if (tile.isLoop()) {
                        map.getLoopLayer()[y][x] = true;
                    }

                    if (tile.isCoin()) {
                        BufferedImage image = null;
                        try {
                            image = ImageIO.read(getClass().getResource("/game/textures/ghostUnder.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        map.getPickupLayer()[y][x] = new Coin(image, new Point2D.Double(x*map.getTileWidth(),y*map.getTileHeight()),32,32,10, true);
                    } else if (tile.isPowerup()) {
                        BufferedImage image = null;
                        try {
                            image = ImageIO.read(getClass().getResource("/game/textures/donerSprite.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        map.getPickupLayer()[y][x] = new Powerup(image, new Point2D.Double(x*map.getTileWidth(),y*map.getTileHeight()),32,32,50, true);
                    }
                }
                i++;
            }
        }
/*
        for(int row = 0; row < map.getLoopLayer().length;row++){
            for(int col= 0; col < map.getLoopLayer()[0].length; col++){
                System.out.print(map.getLoopLayer()[row][col]);
            }
            System.out.println("");
        }
        */

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

        if (isVisible())
            img = Recoloring.colorImage(img, Color.BLUE);

        return img;
    }


    @Override
    public void draw(Graphics2D g2d) {
        if (isVisible()) {
            AffineTransform at = new AffineTransform();
            g2d.drawImage(image, at, null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
