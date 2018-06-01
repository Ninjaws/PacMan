package application.testgame.presentation.frames;


import application.networking.client.data.Storage;
import application.networking.packets.game.player.PacketPlayerUpdate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener, KeyListener {


    public GamePanel() {


        new Timer(1000/60,this).start();

        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        application.networking.client.data.Storage.getInstance().getAppDataTest().getGameObjects().forEach(gameObject -> gameObject.draw(g2d));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("key pressed");
        try {
            Storage.getInstance().getObjectToServer().writeObject(new PacketPlayerUpdate(Storage.getInstance().getUsername(), new Point2D.Double(Storage.getInstance().getAppDataTest().getGameObjects().get(0).getPosition().getX(), Storage.getInstance().getAppDataTest().getGameObjects().get(0).getPosition().getY()-5)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
