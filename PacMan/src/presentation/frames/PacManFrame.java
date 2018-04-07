package presentation.frames;

import data.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author Ian Vink
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

        GamePanel gamePanel = new GamePanel();
        content.add(gamePanel, BorderLayout.CENTER);

        super.setContentPane(content);
        pack();
    }
}
