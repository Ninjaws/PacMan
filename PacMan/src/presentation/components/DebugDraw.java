package presentation.components;

import data.Game;
import presentation.frames.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * @author Ian Vink
 */

public class DebugDraw {

    private Game game;
    private GamePanel panel;

    public DebugDraw(GamePanel panel) {
        this.game = Game.getInstance();
        this.panel = panel;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform at = new AffineTransform();
        g2d.drawImage(game.getDataLayer().getImage(), at, null);
    }
}
