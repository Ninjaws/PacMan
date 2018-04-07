package presentation.frames;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author Jordy van Raalte
 */

public class StartUpScreen extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new StartUpScreen());
        frame.setSize(new Dimension(800,800));
        frame.setVisible(true);
    }

    private Font standardPacManFont;
    private Font standardPacManFont2;
    public StartUpScreen() {
        try {
            standardPacManFont = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getResource("/fonts/crackman front.ttf").toURI()));
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(standardPacManFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addTitel();
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
    }

    private void addTitel(){
        JLabel titel = new JLabel();
        titel.setFont(standardPacManFont.deriveFont(136f));
        titel.setText("Pac Man");
        titel.setForeground(Color.yellow);
        titel.setLocation(getWidth()/2, getHeight()/5);
        this.add(titel);
    }
}
