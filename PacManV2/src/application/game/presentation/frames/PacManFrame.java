package application.game.presentation.frames;

import application.game.data.Game;
import application.game.presentation.frames.startingscreen.StartUpScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author Ian Vink
 * @author Jordy van Raalte
 * The PacManFrame class is the frame where the game will be played. This class is a singleton which can be called from any class.
 */
public class PacManFrame extends JFrame {

    /**
     * The instance attribute is an instance of the PacManFrame. This frame will have the same object reference in the memory.
     */
    private static PacManFrame instance;

    /**
     * The PacManFrame constructor creates the frame which content is set the the StartUpScreen(also known as the titel screen).
     */
    private PacManFrame() {
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

    /**
     * The buildPanel method creates the content which the frame will be holding.
     */
    private void buildPanel() {
        JPanel content = new JPanel(new BorderLayout());
        content.setPreferredSize(new Dimension(Game.getInstance().getScreenWidth(), Game.getInstance().getScreenHeight()));
        setMinimumSize(new Dimension(Game.getInstance().getMap().getMapWidth() * Game.getInstance().getMap().getTileWidth() / 2, Game.getInstance().getMap().getMapHeight() * Game.getInstance().getMap().getTileHeight() / 2));

        StartUpScreen startUpScreen = new StartUpScreen();
        content.add(startUpScreen, BorderLayout.CENTER);

        super.getContentPane().add(content);
        pack();
    }

    /**
     * The setNextPanel sets the next panel of the Frame.
     * @param panel is the next panel of the frame.
     */
    public static void setNextPanel(JPanel panel) {
        if(instance != null){
            instance.setContentPane(panel);
            instance.revalidate();
            panel.requestFocus();
        }
    }

    /**
     * The getInstance method creates an instance of this class if the instance doesn't exist.
     * @return return an instance of the PacManFrame
     */
    public static PacManFrame getInstance() {
        if(instance == null)
            instance = new PacManFrame();

        return instance;
    }

    /**
     * The getFrameSize method gets the size of the frame
     * @return size of the frame.
     */
    public static Dimension getFrameSize() {
        if(instance == null)
            return new Dimension(0,0);
        else {
            return instance.getSize();
        }
    }
}
