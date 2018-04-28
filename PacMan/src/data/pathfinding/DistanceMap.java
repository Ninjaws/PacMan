package data.pathfinding;

import business.VecMath;
import tiled.Map;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ian Vink
 */

public class DistanceMap {

    private Cell cells[][];
    private Map map;


    public DistanceMap(Map map) {
        this.map = map;

        this.cells = new Cell[this.map.getMapHeight()][this.map.getMapWidth()];

        addTiles();
    }

    private void addTiles() {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {

                cells[row][col] = new Cell();
                cells[row][col].setDistance(-1);
            }
        }
    }

    private void resetTiles() {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {

                cells[row][col].setDistance(-1);
            }
        }
    }


    public void calculateDistance(Point2D position) {

        int x = (int) position.getX() / map.getTileWidth();
        int y = (int) position.getY() / map.getTileHeight();

        resetTiles();

        int maxDistance = 0;

        Queue<Point> unvisited = new LinkedList<>();
        unvisited.offer(new Point(x, y));
        cells[y][x].setDistance(0);

        Point[] offsets = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1)};

        while (!unvisited.isEmpty()) {

            Point p = unvisited.poll();

            for (Point offset : offsets) {
                Point neighbour = new Point(p.x + offset.x, p.y + offset.y);


                //If it's outside the map
                if (!map.isInsideMap(neighbour))
                    continue;


                //If it's already been changed
                if (cells[neighbour.y][neighbour.x].getDistance() != -1)
                    continue;

                //If it's a wall
                if (!map.getCollisionLayer()[neighbour.y][neighbour.x] && !map.getStartArealayer()[neighbour.y][neighbour.x] && !map.getLooplayer()[neighbour.y][neighbour.x]) {
                    cells[neighbour.y][neighbour.x].setDistance(-2);
                    continue;
                }

                cells[neighbour.y][neighbour.x].setDistance(cells[p.y][p.x].getDistance() + 1);
                if (cells[neighbour.y][neighbour.x].getDistance() > maxDistance)
                    maxDistance = cells[neighbour.y][neighbour.x].getDistance();

                unvisited.offer(neighbour);

            }


        }
        calculateVectorField();
        calculateHeatMap(maxDistance);

    }


    private void calculateHeatMap(int maxDistance) {

        double relativeDistance = 1.0 / maxDistance;
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {

                if (cells[row][col].getDistance() == -1 || cells[row][col].getDistance() == -2)
                    continue;

                int colorValue = (int) ((cells[row][col].getDistance() * relativeDistance) * 255);
                cells[row][col].setColor(new Color(0, 0, colorValue));

            }
        }

    }

    private void calculateVectorField() {


        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {

                Cell currentCell = cells[y][x];

                //It's a wall
                if (currentCell.getDistance() == -2)
                    continue;
                //It's not changed
                if (currentCell.getDistance() == -1)
                    continue;

                //The target location has no vector
                if (currentCell.getDistance() == 0) {
                    currentCell.setVector(new Point2D.Double(0.0, 0.0));
                    continue;
                }


                Point left = new Point(x - 1, y);
                Point right = new Point(x + 1, y);
                Point up = new Point(x, y - 1);
                Point down = new Point(x, y + 1);

                Point[] neighbours = {left, right, up, down};
                int[] values = {0, 0, 0, 0};

                for (int i = 0; i < neighbours.length; i++) {

                    //Inside the map
                    if (map.isInsideMap(neighbours[i]))
                        values[i] = cells[neighbours[i].y][neighbours[i].x].getDistance();

                    //A wall
                    if (values[i] == -2)
                        values[i] = currentCell.getDistance() + 1;

                    //Outside the map
                    if (values[i] == 0)
                        values[i] = currentCell.getDistance();

                }


                Point2D vector = new Point2D.Double(values[0] - values[1], values[2] - values[3]);


                //Multiple optimal routes
                if (vector.getX() != 0 && vector.getY() != 0) {
                    //Discard vertical vector
                    vector.setLocation(vector.getX(), 0);
                }
                //All directions have the same value (are equally close to the target)
                else if (vector.getX() == 0 && vector.getY() == 0) {

                    // Walls on both horizontal sides (checking the actual value rather than the local value, because that one has been changed by the wall check above)
                    if (cells[neighbours[0].y][neighbours[0].x].getDistance() == -2 && cells[neighbours[1].y][neighbours[1].x].getDistance() == -2) {//values[0] == -2 && values[1] == -2) {

                        vector = new Point2D.Double(0, -1);
                    } else {

                        vector = new Point2D.Double(-1, 0);
                    }
                }


                Point2D normalizedVector = VecMath.getNormalized(vector);
                currentCell.setVector(normalizedVector);
            }
        }

    }


    public void drawHeatMap(Graphics2D g2d) {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {

                if ((map.getCollisionLayer()[row][col] || map.getStartArealayer()[row][col] || map.getLooplayer()[row][col]) && cells[row][col].getDistance() != -1) {

                    g2d.setColor(cells[row][col].getColor());
                    g2d.fill(new Rectangle2D.Double(col * map.getTileWidth(), row * map.getTileHeight(),
                            map.getTileWidth(), map.getTileHeight()));

                    g2d.setColor(Color.WHITE);
                    g2d.draw(new Rectangle2D.Double(col * map.getTileWidth(), row * map.getTileHeight(),
                            map.getTileWidth(), map.getTileHeight()));
                }
            }
        }
    }


    public void drawDistanceMap(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                //    if (map.getCollisionLayer()[row][col] == true && cells[row][col].getDistance() != -1)

                if (cells[row][col].getDistance() == -2)
                    g2d.drawString("X", col * map.getTileWidth() + map.getTileHeight() / 6,
                            row * map.getTileHeight() + map.getTileHeight() / 3);
                else
                    g2d.drawString("" + cells[row][col].getDistance(), col * map.getTileWidth() + map.getTileHeight() / 6,
                            row * map.getTileHeight() + map.getTileHeight() / 3);
            }
        }
    }


    public void drawVectorField(Graphics2D g2d) {

        int tileSize = map.getTileWidth();

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                if ((map.getCollisionLayer()[row][col] || map.getStartArealayer()[row][col] || map.getLooplayer()[row][col]) && cells[row][col].getDistance() != -1) {
                    int centerX = col * tileSize + tileSize / 2;
                    int centerY = row * tileSize + tileSize / 2;

                    g2d.setColor(Color.WHITE);
                    g2d.draw(new Line2D.Double(centerX, centerY, centerX + (cells[row][col].getVector().getX() * tileSize / 2), centerY + (cells[row][col].getVector().getY() * tileSize / 2)));

                    g2d.setColor(Color.RED);
                    double radius = tileSize / 10;
                    g2d.fill(new Ellipse2D.Double(centerX - radius / 2, centerY - radius / 2, radius, radius));

                }
            }
        }
    }


    /**
     * Checks if the tile is inside the map and not a wall
     *
     * @param p The point (tile) you're checking
     * @return Whether the tile is walkable and not outside the map
     */

    private boolean isTileAvailable(Point p) {
        return (isInsideMap(p) && map.getCollisionLayer()[p.y][p.x] == true);//!Simulator.getInstance().getTiledMap().isAWall(p));
    }


    public boolean isInsideMap(Point p) {
        return !(p.x <= 0 || p.x >= cells[0].length || p.y <= 0 || p.y >= cells.length);
    }


    public boolean isNotInitialized(Point p) {
        return cells[p.y][p.x].getDistance() == -1;
    }


    public Cell[][] getCells() {
        return cells;
    }
}
