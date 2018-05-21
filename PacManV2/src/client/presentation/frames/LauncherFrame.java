package client.presentation.frames;

import client.presentation.LauncherPanel;

import javax.swing.*;

public class LauncherFrame extends JFrame {


    private static LauncherFrame instance;
    private LauncherPanel launcherPanel;

    public static LauncherFrame getInstance() {
        if (instance == null)
            instance = new LauncherFrame();


        return instance;
    }

    private LauncherFrame() {
        super("Launcher");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buildPanel();

        setSize(800, 600);
        setVisible(true);
    }


    public void buildPanel() {
        // JPanel content = new JPanel(new BorderLayout());


        launcherPanel = new LauncherPanel();
        // content.add(launcherPanel);

        setContentPane(launcherPanel);
    }


    public LauncherPanel getLauncherPanel() {
        return launcherPanel;
    }
}

