package tiled;

import java.awt.*;

/**
 * @author Ian Vink
 */

public abstract class Layer {
    private String name;
    private String type;
    private int x;
    private int y;

    public abstract void draw(Graphics2D g2d);

}
