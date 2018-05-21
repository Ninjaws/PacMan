package presentation.frames;

import business.SoundPlayer;
import data.Game;
import entities.GameObject;
import javafx.scene.input.KeyCode;
import presentation.components.Camera;
import presentation.components.Controls;
import presentation.components.DebugDraw;
import sun.awt.SunHints;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.security.Key;

/**
 * @author Ian Vink
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Game game;
    private Camera camera;
    private DebugDraw debugDraw;
    private Controls controls;

    private long startTime;
    private long endTime;
    private long deltaTime;


    public GamePanel() {
        game = Game.getInstance();
        camera = new Camera();
        debugDraw = new DebugDraw(this);
        controls = new Controls();


        setBackground(Color.BLACK);


        addKeyListener(this);

        new Timer(1000 / 60, this).start();
        startTime = System.currentTimeMillis();

        Game.getInstance().getSoundPlayer().getClip(SoundPlayer.Sound.GAME_MUSIC).start();
    }

    @Override
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

        if (game.isPaused()) {
            deltaTime = System.currentTimeMillis() - startTime;
            //3 Seconds  before you can start
            if (deltaTime >= 3000) {
                game.setPaused(false);
                startTime = System.currentTimeMillis();
            }
            return;
        }

        endTime = System.currentTimeMillis();
        deltaTime = endTime - startTime;
        startTime = System.currentTimeMillis();

        controls.update();

        game.getPacMan().move(deltaTime);
        game.getGhosts().forEach(ghost -> ghost.move(deltaTime));

        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());

        if (key.equals("W") || key.equals("Up"))
            controls.setCurrentKey(Controls.Key.UP);
        else if (key.equals("A") || key.equals("Left"))
            controls.setCurrentKey(Controls.Key.LEFT);
        else if (key.equals("S") || key.equals("Down"))
            controls.setCurrentKey(Controls.Key.DOWN);
        else if (key.equals("D") || key.equals("Right"))
            controls.setCurrentKey(Controls.Key.RIGHT);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
