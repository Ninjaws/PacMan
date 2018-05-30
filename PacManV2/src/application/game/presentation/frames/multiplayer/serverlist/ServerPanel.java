package application.game.presentation.frames.multiplayer.serverlist;

import application.game.presentation.frames.Button;
import application.game.presentation.frames.PacManFrame;
import application.game.presentation.frames.startingscreen.StartUpScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class ServerPanel extends JPanel implements ActionListener, MouseListener {

    private int width = 0;
    private int height = 0;

    private Button refresh;
    private Button join;
    private Button back;
    private Button create;
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<OnlineGame> onlineGames = new ArrayList<>();
    private OnlineGame currentOnlineGameClicked;

    public ServerPanel() {
        this.setBackground(Color.BLACK);
        Dimension pacmanFrameSize = PacManFrame.getFrameSize();
        width = pacmanFrameSize.width/4 * 3;
        height = pacmanFrameSize.height/4 * 3;
        //test values.
        onlineGames.add(new OnlineGame("Pacman", width));
        onlineGames.add(new OnlineGame("Pacman2", width));
        onlineGames.add(new OnlineGame("Pacman3", width));

        refresh = new Button(new Point2D.Double(width + 32, 10),128,32,"Refresh") {

            public void action() {

            }
        };

        create = new Button(new Point2D.Double(width + 32,  74),128,32,"Create") {

            public void action() {

            }
        };

        join = new Button(new Point2D.Double(width + 32,  138),128,32,"Join") {

            public void action() {

            }
        };

        back = new Button(new Point2D.Double(width + 32, 202),128,32,"Back") {

            public void action() {
                PacManFrame.setNextPanel(new StartUpScreen());
            }
        };

        buttons.add(refresh);
        buttons.add(create);
        buttons.add(join);
        buttons.add(back);

        addMouseListener(this);


        Timer timer = new Timer(1000/60, this);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(10,10,width, height,20,20);

        //draws the rectangle of the serverPamel
        g2d.setColor(Color.getHSBColor((float)240/360,1f,0.5f));
        g2d.fill(roundRectangle2D);

        for(OnlineGame onlineGame : onlineGames)
            onlineGame.draw(g2d);

        //draws the borders of the serverpanel
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.getHSBColor((float)240/360,1f,0.60f));
        g2d.draw(roundRectangle2D);

        refresh.draw(g2d);
        join.draw(g2d);
        back.draw(g2d);
        create.draw(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(OnlineGame onlineGame : onlineGames)
            onlineGame.update(width,onlineGames);

        Dimension pacmanFrameSize = PacManFrame.getFrameSize();
        width = pacmanFrameSize.width/4 * 3;
        height = pacmanFrameSize.height/4 * 3;

        buttons.clear();
        refresh.update(new Point2D.Double(width + 32, 10));
        create.update(new Point2D.Double(width + 32, 74));
        join.update(new Point2D.Double(width + 32,  138));
        back.update(new Point2D.Double(width + 32, 202));

        buttons.add(refresh);
        buttons.add(create);
        buttons.add(join);
        buttons.add(back);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(Button button : buttons) {
            if(button.getRoundRectangle2D().contains(e.getPoint())){
                button.action();
            }
        }

        for(OnlineGame onlineGame : onlineGames) {
            if(onlineGame.getBounds().contains(e.getPoint())) {
                onlineGame.setClicked(true);

                if(currentOnlineGameClicked != null)
                    currentOnlineGameClicked.setClicked(false);

                currentOnlineGameClicked = onlineGame;
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
