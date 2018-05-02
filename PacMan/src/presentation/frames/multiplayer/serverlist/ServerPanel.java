package presentation.frames.multiplayer.serverlist;

import presentation.frames.PacManFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

/**
 * @author Jordy van Raalte
 */
public class ServerPanel extends JPanel implements ActionListener {

    private int width = 0;
    private int height = 0;
    private ArrayList<OnlineGame> onlineGames = new ArrayList<>();
    public ServerPanel() {
        this.setBackground(Color.BLACK);
        Dimension pacmanFrameSize = PacManFrame.getFrameSize();
        width = pacmanFrameSize.width/4 * 3;
        height = pacmanFrameSize.height/4 * 3;
        onlineGames.add(new OnlineGame("Pacman", width,height));
        onlineGames.add(new OnlineGame("Pacman2", width,height));
        onlineGames.add(new OnlineGame("Pacman3", width,height));
        Timer timer = new Timer(1000/60, this);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(10,10,width, height,20,20);

        //draws the rectangle of the serverPamel
        g2d.setColor(Color.getHSBColor((float)240/360,1f,0.5f));
        g2d.fill(roundRectangle2D);

        for(OnlineGame onlineGame : onlineGames)
            onlineGame.draw(g2d);

        //draws the borders of the serverpanel
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.getHSBColor((float)240/360,1f,0.60f));
        g2d.draw(roundRectangle2D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(OnlineGame onlineGame : onlineGames)
            onlineGame.update(width,onlineGames);

        Dimension pacmanFrameSize = PacManFrame.getFrameSize();
        width = pacmanFrameSize.width/4 * 3;
        height = pacmanFrameSize.height/4 * 3;

        repaint();
    }
}
