package presentation.frames.multiplayer;

import presentation.frames.multiplayer.serverlist.ServerPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MultiplayerPanel extends JPanel {

    public MultiplayerPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);
        add(new ServerPanel(), BorderLayout.CENTER);
    }

}
