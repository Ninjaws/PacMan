package presentation.frames;

import data.Game;
import entities.GameObject;
import presentation.components.Camera;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ian Vink
 */

public class GamePanel extends JPanel {

    Game game;
    Camera camera;


    public GamePanel() {
        game = Game.getInstance();
        camera = new Camera();


        setBackground(Color.BLACK);

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setTransform(camera.getTransform());

        game.getMap().draw(g2d);

        for (GameObject gameObject : game.getGameObjects()) {
            gameObject.draw(g2d);
        }

    }
}
