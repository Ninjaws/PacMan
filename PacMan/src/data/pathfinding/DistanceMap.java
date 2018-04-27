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
                if (!map.getCollisionLayer()[neighbour.y][neighbour.x]) {
                    cells[neighbour.y][neighbour.x].setDistance(Integer.MAX_VALUE);
                    continue;
                }

                cells[neighbour.y][neighbour.x].setDistance(cells[p.y][p.x].getDistance() + 1);
                if (cells[neighbour.y][neighbour.x].getDistance() > maxDistance)
                    maxDistance = cells[neighbour.y][neighbour.x].getDistance();

                unvisited.offer(neighbour);

            }


        }
        calculateVectorField();

        /*
        //Return if the point is out of the map
        if (position.getX() <= 0 || position.getY() <= 0 ||
                position.getX() >= cells[0].length * map.getTileWidth() || position.getY() >= cells.length * map.getTileHeight())
            return;

        int x = (int) position.getX() / map.getTileWidth();
        int y = (int) position.getY() / map.getTileHeight();


        //Return if the point is on a wall
        if (map.getCollisionLayer()[y][x] == false)
            return;

        resetTiles();

        int maxDistance = 0;

        Queue<Point> unvisited = new LinkedList<>();

        unvisited.offer(new Point(x, y));
        cells[y][x].setDistance(0);


        while (!unvisited.isEmpty()) {
            Point p = unvisited.poll();

            for (int col = (p.x - 1); col <= (p.x + 1); col++) {
                for (int row = (p.y - 1); row <= (p.y + 1); row++) {

                    //Exclude points outside of the map
                    if (!isInsideMap(new Point(col, row)))
                        continue;

                    //Exclude diagonal cells
                    if (col < p.x && row < p.y || col > p.x && row < p.y ||
                            col < p.x && row > p.y || col > p.x && row > p.y)
                        continue;


                    //Has to be walkable and unedited
                    if (map.getCollisionLayer()[row][col] == true && cells[row][col].getDistance() == -1) {


                        cells[row][col].setDistance(cells[p.y][p.x].getDistance() + 1);

                        if (cells[row][col].getDistance() > maxDistance)
                            maxDistance = cells[row][col].getDistance();

                        unvisited.offer(new Point(col, row));
                    }

                }
            }
        }

        calculateHeatMap(maxDistance);
        calculateVectorField();
        */
    }


    private void calculateHeatMap(int maxDistance) {

        double relativeDistance = 1.0 / maxDistance;

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {

                if (cells[row][col].getDistance() == -1)
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
                if (currentCell.getDistance() == Integer.MAX_VALUE)
                    continue;
                if (currentCell.getDistance() == -1)
                    continue;


                Point left = new Point(x - 1, y);
                Point right = new Point(x + 1, y);
                Point up = new Point(x, y - 1);
                Point down = new Point(x, y + 1);

                int leftValue = 0;
                int rightValue = 0;
                int upValue = 0;
                int downValue = 0;


                if (map.isInsideMap(left))
                    leftValue = cells[left.y][left.x].getDistance();
                if (map.isInsideMap(right))
                    rightValue = cells[right.y][right.x].getDistance();
                if (map.isInsideMap(up))
                    upValue = cells[up.y][up.x].getDistance();
                if (map.isInsideMap(down))
                    downValue = cells[down.y][down.x].getDistance();


                if (leftValue == 0)
                    leftValue = cells[y][x].getDistance();
                if (rightValue == 0)
                    rightValue = cells[y][x].getDistance();
                if (upValue == 0)
                    upValue = cells[y][x].getDistance();
                if (downValue == 0)
                    downValue = cells[y][x].getDistance();

                System.out.println("Distance: " + cells[y][x].getDistance());
                System.out.println("Left: " + leftValue);
                System.out.println("Right: " + rightValue);

                Point2D tempVector = new Point2D.Double(leftValue - rightValue, upValue - downValue);

/*
                //Multiple optimal routes
                if (tempVector.getX() != 0 && tempVector.getY() != 0) {
                    System.out.println(currentCell.getDistance());
                    //Find a direction in which there is no wall, and take it
                    if (upValue == Integer.MAX_VALUE) {
                        if (downValue != Integer.MAX_VALUE)
                            tempVector = new Point2D.Double(0, 1);
                    }
                    if (downValue == Integer.MAX_VALUE) {
                        if (upValue != Integer.MAX_VALUE)
                            tempVector = new Point2D.Double(0, -1);
                    }
                    if (leftValue == Integer.MAX_VALUE) {
                        if (rightValue != Integer.MAX_VALUE)
                            tempVector = new Point2D.Double(1, 0);
                    }
                    if (rightValue == Integer.MAX_VALUE) {
                        if (leftValue != Integer.MAX_VALUE)
                            tempVector = new Point2D.Double(-1, 0);
                    }

                }
*/

                if (tempVector.getX() != 0 && tempVector.getY() != 0) {

                    if (leftValue == Integer.MAX_VALUE)
                        leftValue = rightValue;
                    if (rightValue == Integer.MAX_VALUE)
                        rightValue = leftValue;
                    if (upValue == Integer.MAX_VALUE)
                        upValue = downValue;
                    if (downValue == Integer.MAX_VALUE)
                        downValue = upValue;

                }

                Point2D vector = new Point2D.Double(leftValue - rightValue, upValue - downValue);
                if (vector.getX() > 1)
                    vector.setLocation(1, vector.getY());
                else if (vector.getX() < -1)
                    vector.setLocation(-1, vector.getY());

                if (vector.getY() > 1)
                    vector.setLocation(vector.getX(), 1);
                else if (vector.getY() < -1)
                    vector.setLocation(vector.getX(), -1);




                System.out.println("Vector: " + vector);

                //    Point2D normalizedVector = VecMath.getNormalized(vector);
                //   System.out.println("Normalized: " + normalizedVector);
                System.out.println("");
/*
                //Getting the direction of the vector
                Point direction = new Point();
                if (vector.getX() > 0)
                    direction.x = (int) (Math.ceil(normalizedVector.getX()));
                else
                    direction.x = (int) Math.floor(normalizedVector.getX());

                if (vector.getY() > 0)
                    direction.y = (int) (Math.ceil(normalizedVector.getY()));
                else
                    direction.y = (int) (Math.floor(normalizedVector.getY()));

                */

                //Checking whether a diagonal vector is pointing at a wall
                //If so, make it choose between up/down and left/right

                //The vector is pointing diagonally in a direction
                if (vector.getX() != 0 && vector.getY() != 0 && Math.abs(vector.getX()) == Math.abs(vector.getY())) {

                    Point2D normalizedVector = VecMath.getNormalized(vector);

                    //Getting the direction of the vector
                    Point direction = new Point();
                    if (vector.getX() > 0)
                        direction.x = (int) (Math.ceil(normalizedVector.getX()));
                    else
                        direction.x = (int) Math.floor(normalizedVector.getX());

                    if (vector.getY() > 0)
                        direction.y = (int) (Math.ceil(normalizedVector.getY()));
                    else
                        direction.y = (int) (Math.floor(normalizedVector.getY()));


                    //The vector is pointing at a wall
                    if (!map.getCollisionLayer()[y + direction.y][x + direction.x]) {
                        vector = new Point2D.Double(0, vector.getY());
                    }

                    //Walls on both sides in the back of the vector
                    else if (!map.getCollisionLayer()[y][x + direction.x * -1] && !map.getCollisionLayer()[y + direction.y * -1][x]) {
                        vector = new Point2D.Double(vector.getX(), 0);
                    }
                }











                currentCell.setVector(vector);

                //    currentCell.setVector(new Point2D.Double(left.getDistance() - right.getDistance(), up.getDistance() - down.getDistance()));


                    /*
                    //If it's outside the map
                    if (!map.isInsideMap(neighbour))
                        continue;
                */
            }

        }




/*
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {


                //Skip if the tile is a wall
                if (!map.getCollisionLayer()[y][x])
                    continue;


                Cell currentTile = cells[y][x];

                if (currentTile.getDistance() == 0) {
                    currentTile.setVector(new Point(0, 0));
                    continue;
                }


                Point right = new Point(x + 1, y);
                if (!isTileAvailable(right))
                    cells[right.y][right.x].setDistance(cells[y][x].getDistance());

                Point left = new Point(x - 1, y);
                if (!isTileAvailable(left))
                    cells[left.y][left.x].setDistance(cells[y][x].getDistance());

                Point up = new Point(x, y - 1);
                if (!isTileAvailable(up))
                    cells[up.y][up.x].setDistance(cells[y][x].getDistance());

                Point down = new Point(x, y + 1);
                if (!isTileAvailable(down))
                    cells[down.y][down.x].setDistance(cells[y][x].getDistance());


                //Raising values when the vector wil point at the wall, to prevent this

                if (!map.getCollisionLayer()[right.y][right.x]) {
                    if (cells[left.y][left.x].getDistance() > currentTile.getDistance()) {
                        cells[right.y][right.x].setDistance(cells[right.y][right.x].getDistance() + 1);
                    }
                }
                if (!map.getCollisionLayer()[left.y][left.x]) {
                    if (cells[right.y][right.x].getDistance() > currentTile.getDistance()) {
                        cells[left.y][left.x].setDistance(cells[left.y][left.x].getDistance() + 1);
                    }
                }
                if (!map.getCollisionLayer()[up.y][up.x]) {
                    if (cells[down.y][down.x].getDistance() > currentTile.getDistance()) {
                        cells[up.y][up.x].setDistance(cells[up.y][up.x].getDistance() + 1);
                    }
                }
                if (!map.getCollisionLayer()[down.y][down.x]) {
                    if (cells[up.y][up.x].getDistance() > currentTile.getDistance()) {
                        cells[down.y][down.x].setDistance(cells[down.y][down.x].getDistance() + 1);
                    }
                }

                Point2D vector = new Point2D.Double(cells[left.y][left.x].getDistance() - cells[right.y][right.x].getDistance(),
                        cells[up.y][up.x].getDistance() - cells[down.y][down.x].getDistance());

                //Local Optima problem
                if (vector.getX() == 0 && vector.getY() == 0) {

                    // Walls on both horizontal sides
                    if (!map.getCollisionLayer()[left.y][left.x] || !map.getCollisionLayer()[right.y][right.x]) {
                        vector = new Point2D.Double(0, -1);
                    } else {

                        vector = new Point2D.Double(-1, 0);
                    }
                }


                //Checking whether a diagonal vector is pointing at a wall
                //If so, make it choose between up/down and left/right

                //The vector is pointing diagonally in a direction
                if (vector.getX() != 0 && vector.getY() != 0 && Math.abs(vector.getX()) == Math.abs(vector.getY())) {

                    Point2D normalizedVector = VecMath.getNormalized(vector);

                    //Getting the direction of the vector
                    Point direction = new Point();
                    if (vector.getX() > 0)
                        direction.x = (int) (Math.ceil(normalizedVector.getX()));
                    else
                        direction.x = (int) Math.floor(normalizedVector.getX());

                    if (vector.getY() > 0)
                        direction.y = (int) (Math.ceil(normalizedVector.getY()));
                    else
                        direction.y = (int) (Math.floor(normalizedVector.getY()));


                    //The vector is pointing at a wall
                    if (!map.getCollisionLayer()[y + direction.y][x + direction.x]) {
                        vector = new Point2D.Double(0, vector.getY());
                    }

                    //Walls on both sides in the back of the vector
                    else if (!map.getCollisionLayer()[y][x + direction.x * -1] && !map.getCollisionLayer()[y + direction.y * -1][x]) {
                        vector = new Point2D.Double(vector.getX(), 0);
                    }
                }

                double magnitude = Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
                currentTile.setVector(new Point2D.Double(vector.getX() / magnitude, vector.getY() / magnitude));

            }
        }
*/

    }


    public void drawHeatMap(Graphics2D g2d) {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {

                if (map.getCollisionLayer()[row][col] == true && cells[row][col].getDistance() != -1) {

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

                if (cells[row][col].getDistance() == Integer.MAX_VALUE)
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
                if (map.getCollisionLayer()[row][col] == true && cells[row][col].getDistance() != -1) {// && cells[row][col].getDistance() != 0) {
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
