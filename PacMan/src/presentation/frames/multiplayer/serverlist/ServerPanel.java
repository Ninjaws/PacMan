package presentation.frames.multiplayer.serverlist;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ServerPanel extends JPanel{

    public ServerPanel() {
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(10,10,getWidth()/1.35, getHeight()/1.35,20,20);

        //draws the rectangle of the serverlobby
        g2d.setColor(Color.getHSBColor((float)240/360,1f,0.5f));
        g2d.fill(roundRectangle2D);

        //draws the borders of the serverpanel
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.getHSBColor((float)240/360,1f,0.60f));
        g2d.draw(roundRectangle2D);
    }
}
