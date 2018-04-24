package presentation.frames.multiplayer;

import javax.swing.*;
import java.awt.*;

public class ServerPanel extends JPanel {

    public ServerPanel() {
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
    }
}
