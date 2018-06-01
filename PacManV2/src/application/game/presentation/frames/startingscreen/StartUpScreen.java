package application.game.presentation.frames.startingscreen;


import application.game.business.SoundPlayer;
import application.game.data.Game;
import application.game.presentation.frames.GamePanel;
import application.game.presentation.frames.PacManFrame;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    private long startTime = System.currentTimeMillis();
    private int nextTime = 1;
    private double currentTime = System.currentTimeMillis();


    private boolean isGameLoading = false;

    /**
     * The constructor of the StartUpScreen loads the standard pacman font. Also it loads the the spritesheet of the pacman.
     */
    public StartUpScreen() {
        //loads needed files
        try {
            standardPacManFont = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getResource("/game/fonts/crackmanfront.ttf").toURI()));
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(standardPacManFont);
            BufferedImage spritesheet = ImageIO.read(new File(getClass().getResource("/game/textures/pacman.png").toURI()));
            for(int i = 0; i < 16; i++){
                frames[i] = spritesheet.getSubimage(52 * (i % 4),52 * (i / 4), 52,52);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
       }

        //sets graphical options
        setBackground(Color.BLACK);

        //adds menu items.
        menuTexts.add(new MenuText("Pac man", standardPacManFont.deriveFont(136f),
                1));


        addMouseListener(this);

        //starts timer
        Timer timer = new Timer(1000/60, this);
        timer.start();

    }

    /**
     * Deletes AnimatedPacMans from the list animatedPacMans if the pacman is passed the screenborder.
     */
    private void ifPassedBorder(){
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

    public void countDown(int n){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(n == 0){
                PacManFrame.setNextPanel(new GamePanel());
                animatedPacMans.clear();
        }
        else if(currentTime/1000 > nextTime){
            nextTime++;
            currentTime = System.currentTimeMillis() - startTime;
            countDown(n - 1);
        }
        else{
            currentTime = System.currentTimeMillis() - startTime;
            countDown(n);
        }
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

        if(!isGameLoading){
            isGameLoading = true;
            new Thread(() -> {
                countDown(10);
            }).start();
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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


