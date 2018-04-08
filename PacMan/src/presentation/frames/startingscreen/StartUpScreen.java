package presentation.frames.startingscreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;

public class StartUpScreen extends JPanel implements ActionListener, MouseListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,800));
        frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - (frame.getWidth() / 2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - (frame.getHeight() / 2));
        frame.setContentPane(new StartUpScreen());
        frame.setVisible(true);
    }

    private Font standardPacManFont;
    private ArrayList<MenuText> menuTexts = new ArrayList<>();
    public StartUpScreen() {
        try {
            standardPacManFont = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getResource("/Fonts/crackmanfront.ttf").toURI()));
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(standardPacManFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setBackground(Color.BLACK);
        this.addMouseListener(this);


        menuTexts.add(new MenuText("Pac man", standardPacManFont.deriveFont(136f),
                1, new Point2D.Double(0,0)));
        menuTexts.add(new MenuText("Singleplayer", standardPacManFont.deriveFont(76f),2,new Point2D.Double(0,0)));
        menuTexts.add(new MenuText("Multiplayer", standardPacManFont.deriveFont(76f),3,new Point2D.Double(0,0)));
        menuTexts.add(new MenuText("Options", standardPacManFont.deriveFont(76f),4,new Point2D.Double(0,0)));

        Timer timer = new Timer(1000/60, this);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        for(MenuText menuText : menuTexts)
            menuText.debugDraw(g2d);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(MenuText menuText : menuTexts)
            menuText.update(getWidth(), getHeight(), 5);

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(MenuText menuText : menuTexts){
            if(menuText.getBounds().contains(e.getPoint())){
                System.out.println(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


