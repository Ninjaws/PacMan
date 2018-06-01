package application.testgame.presentation.frames;

import application.game.data.Game;
import application.game.presentation.frames.startingscreen.StartUpScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameFrame extends JFrame {
    public GameFrame() {
        super("PacMan");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        buildPanel();

        setSize(800, 800);
        setVisible(true);
    }

    private void buildPanel() {
        JPanel content = new JPanel(new BorderLayout());

        GamePanel gamePanel = new GamePanel();
        content.add(gamePanel, BorderLayout.CENTER);

        super.getContentPane().add(content);
        pack();
    }
}
