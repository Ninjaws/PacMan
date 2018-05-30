package application.game.tiled;

import javax.json.JsonObject;
import java.awt.*;

/**
 * @author Ian Vink
 */

public abstract class Layer {
  private boolean visible;
    protected Layer(JsonObject layer){
    visible = layer.getBoolean("visible");
    }

    public boolean isVisible() {
        return visible;
    }

    public abstract void draw(Graphics2D g2d);

}
