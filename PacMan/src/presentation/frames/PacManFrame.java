package presentation.frames;

import data.Game;
import presentation.frames.startingscreen.StartUpScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author Ian Vink
 * @author Jordy van Raalte
 */

public class PacManFrame extends JFrame {

    public PacManFrame() {
        super("PacMan");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buildPanel();

        //Update screen size variables upon changing size (used for the scaling of the tiles)
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent ev) {
                Game.getInstance().setScreenDimensions(getContentPane().getSize().width, getContentPane().getSize().height);
            }
        });

        setVisible(true);
    }

    public void buildPanel() {
        JPanel content = new JPanel(new BorderLayout());
        content.setPreferredSize(new Dimension(Game.getInstance().getScreenWidth(), Game.getInstance().getScreenHeight()));
        setMinimumSize(new Dimension(Game.getInstance().getMap().getMapWidth() * Game.getInstance().getMap().getTileWidth() / 2, Game.getInstance().getMap().getMapHeight() * Game.getInstance().getMap().getTileHeight() / 2));

        StartUpScreen startUpScreen = new StartUpScreen(this);
        content.add(startUpScreen, BorderLayout.CENTER);

        super.getContentPane().add(content);
        pack();
    }

    public void setNextPanel(JPanel panel) {

        panel.setPreferredSize(new Dimension(Game.getInstance().getScreenWidth(), Game.getInstance().getScreenHeight()));

        super.getContentPane().removeAll();
        super.getContentPane().add(panel);

        pack();
        revalidate();
        repaint();

        panel.requestFocusInWindow();
    }
}
