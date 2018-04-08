package presentation.frames;

import data.Game;
import entities.GameObject;
import javafx.scene.input.KeyCode;
import presentation.components.Camera;
import presentation.components.DebugDraw;
import sun.awt.SunHints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

/**
 * @author Ian Vink
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Game game;
    private Camera camera;
    private DebugDraw debugDraw;

    private long startTime;
    private long endTime;
    private long deltaTime;


    public GamePanel() {
        game = Game.getInstance();
        camera = new Camera();
        debugDraw = new DebugDraw(this);


        setBackground(Color.BLACK);

        addKeyListener(this);
        setFocusable(true);
        new Timer(1000 / 60, this).start();
        startTime = System.currentTimeMillis();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setTransform(camera.getTransform());

        game.getMap().draw(g2d);
        debugDraw.draw(g2d);

        for (GameObject gameObject : game.getGameObjects()) {
            gameObject.draw(g2d);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        endTime = System.currentTimeMillis();
        deltaTime = endTime - startTime;
        startTime = System.currentTimeMillis();

        game.getPacMan().move(deltaTime);
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());
        if (key.equals("W"))
            game.getPacMan().setDirection(new Point(0, -1));
        else if (key.equals("A"))
            game.getPacMan().setDirection(new Point(-1, 0));
        else if (key.equals("S"))
            game.getPacMan().setDirection(new Point(0, 1));
        else if (key.equals("D"))
            game.getPacMan().setDirection(new Point(1, 0));

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
