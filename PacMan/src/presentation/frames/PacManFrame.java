package presentation.frames;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ian Vink
 */

public class PacManFrame extends JFrame {
    public PacManFrame() {
        super("PacMan");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buildPanel();

        setSize(800, 800);
        setVisible(true);
    }

    public void buildPanel() {
        JPanel content = new JPanel(new BorderLayout());

        GamePanel gamePanel = new GamePanel();

        content.add(gamePanel, BorderLayout.CENTER);

        super.setContentPane(content);

    }
}
