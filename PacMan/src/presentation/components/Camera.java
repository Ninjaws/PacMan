package presentation.components;

import data.Game;

import javax.swing.*;
import java.awt.geom.AffineTransform;

/**
 * @author Ian Vink
 */

public class Camera {


    public Camera() {
    }

    public AffineTransform getTransform() {
        AffineTransform at = new AffineTransform();
        at.scale((double) Game.getInstance().getScreenWidth() / (Game.getInstance().getMap().getMapWidth() * Game.getInstance().getMap().getTileWidth()),
                (double) Game.getInstance().getScreenHeight() / (Game.getInstance().getMap().getMapHeight() * Game.getInstance().getMap().getTileHeight()));

        return at;
    }
}
