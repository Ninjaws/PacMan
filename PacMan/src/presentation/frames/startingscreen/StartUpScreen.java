package presentation.frames.startingscreen;
import business.SoundPlayer;
import data.Game;
import presentation.frames.GamePanel;
import presentation.frames.PacManFrame;
import presentation.frames.multiplayer.serverlist.ServerPanel;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Jordy van Raalte
 * The StartUpScreen class represents the titel screen if the game.
 */
public class StartUpScreen extends JPanel implements ActionListener, MouseListener {
    /**
     * The standardPacManFont attribute is a font which will be drawn on the titel screen.
     */
    private Font standardPacManFont;

    /**
     * The menuTexts list is a list which contains MenuText objects which will be drawn on the titel screen.
     */
    private ArrayList<MenuText> menuTexts = new ArrayList<>();

    /**
     * The animatedPacMans list contains AnimatedPacMan object which will be spawn randomly on the screen.
     */
    private ArrayList<AnimatedPacMan> animatedPacMans = new ArrayList<>();

    /**
     * The frames attribute contains each PacMan sprite from the pacman.png spritesheet.
     */
    private BufferedImage[] frames = new BufferedImage[16];

    /**
     * The constructor of the StartUpScreen loads the standard pacman font. Also it loads the the spritesheet of the pacman.
     */
    public StartUpScreen() {
        //loads needed files
        try {
            standardPacManFont = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getResource("/fonts/crackmanfront.ttf").toURI()));
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(standardPacManFont);
            BufferedImage spritesheet = ImageIO.read(new File("resources/textures/pacman.png"));
            for(int i = 0; i < 16; i++){
                frames[i] = spritesheet.getSubimage(52 * (i % 4),52 * (i / 4), 52,52);
            }
            Game.getInstance().getSoundPlayer().getClip(SoundPlayer.Sound.MAIN_MENU).loop(Clip.LOOP_CONTINUOUSLY);

        }
        catch (Exception e) {
            e.printStackTrace();
       }

        //sets graphical options
        setBackground(Color.BLACK);

        //adds menu items.
        menuTexts.add(new MenuText("Pac man", standardPacManFont.deriveFont(136f),
                1));
        menuTexts.add(new MenuText("Singleplayer", standardPacManFont.deriveFont(76f),
                2));
        menuTexts.add(new MenuText("Multiplayer", standardPacManFont.deriveFont(76f),
                3));
        menuTexts.add(new MenuText("Options", standardPacManFont.deriveFont(76f),
                4));


        addMouseListener(this);

        //starts timer
        Timer timer = new Timer(1000/60, this);
        timer.start();

    }

    /**
     * Deletes AnimatedPacMans from the list animatedPacMans if the pacman is passed the screenborder.
     */
    private void ifPassedBorder(){
        if(animatedPacMans.size() != 0){
            Iterator<AnimatedPacMan> animatedPacManIterator = animatedPacMans.iterator();
            while(animatedPacManIterator.hasNext()){
                AnimatedPacMan animatedPacMan = animatedPacManIterator.next();
                if(animatedPacMan.getPosition().getX() < -frames[0].getWidth() || animatedPacMan.getPosition().getX() > getWidth() + frames[0].getWidth()){
                    animatedPacManIterator.remove();
                }
            }
        }
    }

    /**
     * Randomly spawns pacmans of a random position.
     */
    private void randomlySpawn() {
        Random random = new Random();
        int spawnX = random.nextInt(100);
        int spawnY = random.nextInt(Game.getInstance().getScreenWidth());

        if(spawnX >= 50)
            spawnX = -1 * frames[0].getWidth();
        else
            spawnX = getWidth() + frames[0].getWidth();

        if(spawnY > getHeight() - frames[0].getWidth())
            spawnY = spawnY + -frames[0].getWidth();


        if(random.nextInt(1000) >= 995)
            animatedPacMans.add(new AnimatedPacMan(new Point2D.Double(spawnX,spawnY),frames));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        for(AnimatedPacMan animatedPacMan : animatedPacMans)
            animatedPacMan.draw(g2d);

        for(MenuText menuText : menuTexts)
            menuText.draw(g2d);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(MenuText menuText : menuTexts)
            menuText.update(getWidth(), getHeight(), 5);

        for(AnimatedPacMan animatedPacMan : animatedPacMans)
            animatedPacMan.update();

        ifPassedBorder();
        randomlySpawn();

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(MenuText menuText : menuTexts) {
            if(menuText.getBounds().contains(e.getPoint())) {
                if(menuText.getText().equals("Singleplayer")) {
                    Game.getInstance().getSoundPlayer().getClip(SoundPlayer.Sound.MAIN_MENU).stop();
                    PacManFrame.setNextPanel(new GamePanel());
                    animatedPacMans.clear();
                }
                else if(menuText.getText().equals("Multiplayer")) {
                    Game.getInstance().getSoundPlayer().getClip(SoundPlayer.Sound.MAIN_MENU).stop();
                    PacManFrame.setNextPanel(new ServerPanel());
                    animatedPacMans.clear();
                }
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


